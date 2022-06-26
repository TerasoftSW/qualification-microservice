package com.terasoft.qualificationbc.command.application.dtos.request;

import lombok.Value;

@Value
public class RegisterQualificationLegalAdviceRequest {
    private String comment;
    private Integer score;
    private String lawyerId;
    private String customerId;
    private String legalAdviceId;
}
