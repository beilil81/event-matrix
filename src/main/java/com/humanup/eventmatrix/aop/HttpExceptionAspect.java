package com.humanup.eventmatrix.aop;

import com.humanup.eventmatrix.aop.dto.HttpException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HttpExceptionAspect {
  private static final Logger LOGGER = LogManager.getLogger(HttpExceptionAspect.class);

  @Around("execution(* com.humanup.eventmatrix.controllers..*(..)))")
  public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    try {
      Object result = proceedingJoinPoint.proceed();
      return result;
    } catch (HttpException ex) {
      LOGGER.error("****HttpException " + ex.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
  }
}
