package com.mycompany.uposts.domain.response.error;

import com.mycompany.uposts.domain.response.Response;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ErrorResponse implements Response {

    private Error error;
}
