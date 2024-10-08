package com.mycompany.uposts.domain.response.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import com.mycompany.uposts.domain.constant.Code;

@Data
@Builder
public class CommonException extends RuntimeException {

    private final Code code;
    private final String userMessage;
    private final String techMessage;
    private final HttpStatus httpStatus;
}
