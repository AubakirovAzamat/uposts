package com.mycompany.uposts.domain.api.search.searchTags;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.mycompany.uposts.domain.constant.RegExp;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchTagsReq {
    @NotBlank(message = "partTag должен быть заполнен")
    @Pattern(regexp = RegExp.tag, message = "Некорректный partTag")
    private String partTag;
}
