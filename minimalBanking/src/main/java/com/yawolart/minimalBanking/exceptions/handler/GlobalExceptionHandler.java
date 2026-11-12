package com.yawolart.minimalBanking.exceptions.handler;

import com.yawolart.minimalBanking.dto.ExceptionRespDto;
import com.yawolart.minimalBanking.exceptions.FindAccountException;
import com.yawolart.minimalBanking.exceptions.InvalidAccountTypeException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FindAccountException.class)
    public ResponseEntity<ExceptionRespDto> findAccountExceptionHandler(
            HttpServletRequest req,
            FindAccountException ex
    ){
        ExceptionRespDto respDto = new ExceptionRespDto(
                HttpStatus.NOT_FOUND.value(),
                req.getRequestURI(),
                LocalDateTime.now(),
                "Cannot find the account",
                ex.getMessage()
        );

        return ResponseEntity
                .status(404)
                .body(respDto);
    }

    @ExceptionHandler(InvalidAccountTypeException.class)
    public ResponseEntity<ExceptionRespDto> invalidAccTypeExceptionHandler(
            HttpServletRequest req,
            InvalidAccountTypeException ex
    ){
        ExceptionRespDto respDto = new ExceptionRespDto(
                HttpStatus.BAD_REQUEST.value(),
                req.getRequestURI(),
                LocalDateTime.now(),
                "Invalid account type",
                ex.getMessage()
        );
        System.out.println("[GlobalExceptionHandler]");
        return ResponseEntity
                .status(400)
                .body(respDto);
    }
}
