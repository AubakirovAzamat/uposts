package com.mycompany.uposts.domain.api.search.searchPostsByTag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.mycompany.uposts.domain.constant.Sort;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchPostsByTagReq {
    @DecimalMin(value = "1", message = "Значение tagId должно быть больше 0")
    private long tagId;
    @NotNull(message = "sort должен быть заполнен")
    private Sort sort;
}