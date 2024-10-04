package com.mycompany.uposts.domen.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginReq {

    @NotNull(message = "authorization должен быть заполнен")
    private Authorization authorization;
}