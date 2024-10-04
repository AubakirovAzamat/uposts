package com.mycompany.uposts.domen.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.mycompany.uposts.domen.constant.RegExp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicPostReq {
    @NotBlank(message = "text должен быть заполнен")
    @Pattern(regexp = RegExp.post, message = "Некорректный text")
    private String text;
    @Size(max = 5, message = "Количество тегов не должно превышать 5")
    private List<
    @NotBlank(message = "tag должен быть заполнен") 
    @Pattern(regexp = RegExp.tag, message = "Некорректный tag") String> tags;
}