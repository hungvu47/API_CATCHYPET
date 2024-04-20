package com.hungvu.webgym.request;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class AppointmentRequest {

    private String customerName;

    private String customerPhone;

    private String customerEmail;

    private double totalAmount;

    private Long petServiceId;

//    private Map<String, String> classificationValues;

    private List<ClassificationPetRequest> classifications;

}
