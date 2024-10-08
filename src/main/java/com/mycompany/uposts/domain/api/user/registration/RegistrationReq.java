package com.mycompany.uposts.domain.api.user.registration;

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
public class RegistrationReq {

    @NotNull(message = "authorization должен быть заполнен")
    private AuthorizationReq authorization;
}
