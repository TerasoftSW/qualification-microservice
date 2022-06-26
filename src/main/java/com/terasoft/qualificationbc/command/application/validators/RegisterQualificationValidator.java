package com.terasoft.qualificationbc.command.application.validators;

import com.terasoft.common.application.Notification;
import com.terasoft.qualificationbc.command.application.dtos.request.RegisterQualificationCustomLegalCaseRequest;
import com.terasoft.qualificationbc.command.application.dtos.request.RegisterQualificationLegalAdviceRequest;
import org.springframework.stereotype.Component;

@Component
public class RegisterQualificationValidator {

    public RegisterQualificationValidator() {}

    public Notification validateQualificationCLC(RegisterQualificationCustomLegalCaseRequest registerQualificationCustomLegalCaseRequest) {
        Notification notification = new Notification();
        String comment = registerQualificationCustomLegalCaseRequest.getComment();
        if (comment.isEmpty()) {
            notification.addError("Qualification comment is required");
        }
        String score = registerQualificationCustomLegalCaseRequest.getScore().toString();
        if (score.isEmpty()) {
            notification.addError("Qualification score is required");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        return notification;
    }

    public Notification validateQualificationLA(RegisterQualificationLegalAdviceRequest registerQualificationLegalAdviceRequest) {
        Notification notification = new Notification();
        String comment = registerQualificationLegalAdviceRequest.getComment();
        if (comment.isEmpty()) {
            notification.addError("Qualification comment is required");
        }
        String score = registerQualificationLegalAdviceRequest.getScore().toString();
        if (score.isEmpty()) {
            notification.addError("Qualification score is required");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        return notification;
    }
}
