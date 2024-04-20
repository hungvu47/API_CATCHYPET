package com.hungvu.webgym.request;

import lombok.Data;

import java.util.List;

@Data
public class ClassificationPetRequest {

    private String classificationName;

    private List<String> values;
}
