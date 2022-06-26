package com.terasoft.qualificationbc.command.application.dtos.response;

import lombok.Value;

@Value
public class RegisterQualificationLegalAdviceResponse {
    private String qualificationId;
    private String comment;
    private Integer score;
    private String lawyerId;
    private String customerId;
    private String legalAdviceId;
}
