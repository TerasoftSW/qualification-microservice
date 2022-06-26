package com.terasoft.qualificationbc.command.application.dtos.request;

import lombok.Getter;
import lombok.Setter;

public class EditQualificationLegalAdviceRequest {
    private @Getter @Setter String qualificationId;
    private @Getter String comment;
    private @Getter Integer score;
}
