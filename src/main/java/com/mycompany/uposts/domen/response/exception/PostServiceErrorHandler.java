package com.mycompany.uposts.domen.response.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.mycompany.uposts.domen.constant.Code;
import com.mycompany.uposts.domen.response.error.Error;
import com.mycompany.uposts.domen.response.error.ErrorResponse;

import java.util.Optional;

@Slf4j
@ControllerAdvice
public class PostServiceErrorHandler {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorResponse> handleCommonException(CommonException ex) {
        log.error("common error: {}", ex.toString());
        return new ResponseEntity<>(ErrorResponse.builder().error(Error.builder().code(ex.getCode()).message(ex.getMessage()).build()).build(), ex.getHttpStatus());
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedErrorException(Exception ex) {
        log.error("internal server error: {}", ex.toString());
        return new ResponseEntity<>(ErrorResponse.builder().error(Error.builder().code(Code.INTERNAL_SERVER_ERROR).message("Внутренняя ошибка сервиса").build()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}