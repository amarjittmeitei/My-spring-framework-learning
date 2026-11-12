package com.yawolart.minimalBanking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionRespDto {
    private int statusCode;
    private String path;
    private LocalDateTime timeStamp;
    private String message;
    private String exceptionMessage;
}
