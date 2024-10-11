package com.mycompany.uposts.domain.api.search.searchPostsByPartWord;

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
public class SearchPostsByPartWordReq {
    @NotBlank(message = "partWord должен быть заполнен")
    @Pattern(regexp = RegExp.partWord, message = "Некорректный partWord")
    private String partWord;
    @NotNull(message = "sort должен быть заполнен")
    private Sort sort;
}
