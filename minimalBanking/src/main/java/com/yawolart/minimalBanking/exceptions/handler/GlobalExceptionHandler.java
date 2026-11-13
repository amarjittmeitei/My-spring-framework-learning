package com.yawolart.minimalBanking.exceptions.handler;

import com.yawolart.minimalBanking.dto.ExceptionRespDto;
import com.yawolart.minimalBanking.exceptions.FindAccountException;
import com.yawolart.minimalBanking.exceptions.InsufficientBalance;
import com.yawolart.minimalBanking.exceptions.InvalidAccountTypeException;
import com.yawolart.minimalBanking.exceptions.NoTransactionFound;
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
                .status(HttpStatus.NOT_FOUND)
                .body(respDto);
    }

    @ExceptionHandler(InsufficientBalance.class)
    public ResponseEntity<ExceptionRespDto> insufficientBalanceHandler(
            HttpServletRequest req,
            InsufficientBalance ex
    ){
        ExceptionRespDto respDto = new ExceptionRespDto(
                HttpStatus.PAYMENT_REQUIRED.value(),
                req.getRequestURI(),
                LocalDateTime.now(),
                "Insufficient balance",
                ex.getMessage()
        );
        return ResponseEntity
                .status(HttpStatus.PAYMENT_REQUIRED)
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
                .status(HttpStatus.BAD_REQUEST)
                .body(respDto);
    }

    @ExceptionHandler(NoTransactionFound.class)
    public ResponseEntity<ExceptionRespDto> NoTransactionFoundHandler(
            HttpServletRequest req,
            NoTransactionFound ex
    ){
        ExceptionRespDto respDto = new ExceptionRespDto(
                HttpStatus.NOT_FOUND.value(),
                req.getRequestURI(),
                LocalDateTime.now(),
                "No transaction found",
                ex.getMessage()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(respDto);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionRespDto> genericRuntimeExceptionHandler(
            HttpServletRequest req,
            RuntimeException ex
    ){
        ExceptionRespDto respDto = new ExceptionRespDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                req.getRequestURI(),
                LocalDateTime.now(),
                "Generic runtime exception",
                ex.getMessage()
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(respDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionRespDto> genericExceptionHandler(
            HttpServletRequest req,
            Exception ex
    ){
        ExceptionRespDto respDto = new ExceptionRespDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                req.getRequestURI(),
                LocalDateTime.now(),
                "Generic exception",
                ex.getMessage()
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(respDto);
    }
}
