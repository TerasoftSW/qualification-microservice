package com.terasoft.qualificationbc.command.application.dtos.response;

import lombok.Value;

@Value
public class EditQualificationLegalAdviceResponse {
    private String qualificationId;
    private String comment;
    private Integer score;
}
