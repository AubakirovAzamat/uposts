package com.mycompany.uposts.domain.api.user.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

import com.mycompany.uposts.domain.api.user.common.AuthorizationReq;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginReq {

    @NotNull(message = "authorization должен быть заполнен")
    private AuthorizationReq authorization;
}