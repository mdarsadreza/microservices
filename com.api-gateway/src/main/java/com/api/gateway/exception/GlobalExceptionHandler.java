//package com.api.gateway.exception;
//
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.NoHandlerFoundException;
//
//import javax.servlet.http.HttpServletRequest;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(NoHandlerFoundException.class)
//    public ModelAndView handleError404(HttpServletRequest request, Exception e) {
//        ModelAndView mav = new ModelAndView("404error");
//        mav.addObject("exception", e);
//        return mav;
//    }
//}
