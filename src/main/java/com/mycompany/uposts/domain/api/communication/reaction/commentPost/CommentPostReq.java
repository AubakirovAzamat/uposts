package com.mycompany.uposts.domain.api.communication.reaction.commentPost;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.mycompany.uposts.domain.constant.RegExp;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentPostReq {
    @DecimalMin(value = "1", message = "Значение postId должно быть больше 0")
    private long postId;
    @NotBlank(message = "text должен быть заполнен")
    @Pattern(regexp = RegExp.post, message = "Некорректный text")
    private String text;
}
