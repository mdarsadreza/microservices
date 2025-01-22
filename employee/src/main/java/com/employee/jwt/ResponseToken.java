package com.employee.jwt;

public class ResponseToken {
    private String message;
    private String token;
    private String refreshToken;

    public ResponseToken(String message, String token, String refreshToken) {
        this.message = message;
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public String getMessage() {
        return message;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ResponseToken(String message, String token) {
        this.message = message;
        this.token = token;
    }
}
