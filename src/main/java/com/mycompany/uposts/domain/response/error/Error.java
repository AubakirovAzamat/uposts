package com.mycompany.uposts.domain.response.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mycompany.uposts.domain.constant.Code;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    private Code code;
    private String userMessage;
    private String techMessage;
}