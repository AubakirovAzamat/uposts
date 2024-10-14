package com.mycompany.uposts.domain.api.search.searchUsersByPartNickname;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.mycompany.uposts.domain.constant.RegExp;
import com.mycompany.uposts.domain.constant.Sort;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchUsersByPartNicknameReq {
    @NotBlank(message = "partNickname должен быть заполнен")
    @Pattern(regexp = RegExp.partNickname, message = "Некорректный partNickname")
    private String partNickname;
}