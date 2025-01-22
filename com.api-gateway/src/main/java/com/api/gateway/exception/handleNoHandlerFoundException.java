//package com.api.gateway.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.NoHandlerFoundException;
//
//public class handleNoHandlerFoundException {
//
//    @ExceptionHandler(NoHandlerFoundException.class)
//    public ResponseEntity<String> handleNoHandlerFoundException(NoHandlerFoundException ex) {
////        log.error("404 Error", ex);
//        return new ResponseEntity<>("Error 404 - Page Not Found", HttpStatus.NOT_FOUND);
//    }
//}
