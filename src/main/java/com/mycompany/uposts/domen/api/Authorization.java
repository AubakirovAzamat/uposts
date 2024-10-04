package com.mycompany.uposts.domen.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.mycompany.uposts.domen.constant.RegExp;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Authorization {
    @NotBlank(message = "nickname должен быть заполнен")
    @Pattern(regexp = RegExp.nickname, message = "Некорректный nickname")
    private String nickname;
    @NotBlank(message = "password должен быть заполнен")
    @Pattern(regexp = RegExp.password, message = "Некорректный password")
    private String password;
}