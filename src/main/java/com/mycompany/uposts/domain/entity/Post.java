package com.mycompany.uposts.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private long id;
    private long userId;
    private String text;
    private String timeInsert;
}
