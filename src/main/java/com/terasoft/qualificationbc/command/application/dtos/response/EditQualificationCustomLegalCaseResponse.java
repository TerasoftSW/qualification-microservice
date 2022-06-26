package com.terasoft.qualificationbc.command.application.dtos.response;


import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
public class EditQualificationCustomLegalCaseResponse {
    private String qualificationId;
    private String comment;
    private Integer score;
}
