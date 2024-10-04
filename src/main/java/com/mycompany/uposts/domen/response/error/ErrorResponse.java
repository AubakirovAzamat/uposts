package com.mycompany.uposts.domen.response.error;

import com.mycompany.uposts.domen.response.Response;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ErrorResponse implements Response {

    private Error error;
}
