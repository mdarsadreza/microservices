package com.employee.jwt;

import com.employee.config.PasswordEncryptionService;
import com.employee.exception.BadRequestException;
import com.employee.exception.DepartmentNotFoundException;
import com.employee.model.Employee;
import com.employee.service.EmployeeService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Value("${jwt.refreshExpiration}")
    private long refreshTokenExpiration;

    private static final String SECRET_KEY = "yourFixedSecretKeyForHS512ReplaceThisWith64ByteKey";

    private final Key secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    @Autowired
    private PasswordEncryptionService passwordEncryptionService;

    @Autowired
    private EmployeeService empService;

    public ResponseToken authenticateAndGenerateToken(String username, String password, Employee employee) throws Exception {
        String combinedPassword = username + ":" + password;
        String encryptedPass = passwordEncryptionService.encryptPassword(combinedPassword);

        if (employee.getUsername().equals(username) && employee.getPassword().equals(encryptedPass)) {
            String token = generateAccessToken(employee);
            String refreshToken = generateRefreshToken(employee);
            return new ResponseToken("Login Successful", token, refreshToken);
        }
        throw new BadRequestException("Invalid Credentials");
    }

    private String generateAccessToken(Employee employee) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", employee.getUsername());
        claims.put("role", employee.getRole().getName());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(employee.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(secretKey)
                .compact();
    }

    private String generateRefreshToken(Employee employee) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", employee.getUsername());
        claims.put("role", employee.getRole().getName());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(employee.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .signWith(secretKey)
                .compact();
    }

    public ResponseToken refreshAccessToken(String refreshToken) {
        if (isValidRefreshToken(refreshToken)) {
            String username = extractUsername(refreshToken);
            Employee employee = getEmployeeByUsername(username);
            String newAccessToken = generateAccessToken(employee);
            return new ResponseToken("Refresh Successful", newAccessToken, refreshToken);
        } else {
            throw new BadRequestException("Invalid or Expired Refresh Token");
        }
    }

    // Extract username from refresh token
    private String extractUsername(String refreshToken) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(refreshToken)
                .getBody()
                .getSubject();
    }

    private boolean isValidRefreshToken(String refreshToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(refreshToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Employee getEmployeeByUsername(String username) {
        Employee employee = empService.getEmployeeByEmail(username);
        if (employee == null) {
            throw new DepartmentNotFoundException("Employee with username " + username + " not found");
        }
        return employee;
    }
}
