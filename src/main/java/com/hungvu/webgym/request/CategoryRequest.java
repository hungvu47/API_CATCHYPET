package com.hungvu.webgym.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CategoryRequest {

    private Long categoryId;
    private String categoryName;
    private String categoryDes;
    private String categoryImage;
}
