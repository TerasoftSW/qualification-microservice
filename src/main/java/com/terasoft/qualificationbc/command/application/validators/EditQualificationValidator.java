package com.terasoft.qualificationbc.command.application.validators;

import com.terasoft.common.application.Notification;
import com.terasoft.qualificationbc.command.application.dtos.request.EditQualificationCustomLegalCaseRequest;
import com.terasoft.qualificationbc.command.application.dtos.request.EditQualificationLegalAdviceRequest;
import org.springframework.stereotype.Component;

@Component
public class EditQualificationValidator {
    public EditQualificationValidator() {}

    public Notification validateQualificationCLC(EditQualificationCustomLegalCaseRequest editQualificationCustomLegalCaseRequest) {
        Notification notification = new Notification();
        String qualificationId = editQualificationCustomLegalCaseRequest.getQualificationId().trim();
        if (qualificationId.isEmpty()) {
            notification.addError("Qualification id is required");
        }
        String comment = editQualificationCustomLegalCaseRequest.getComment();
        if (comment.isEmpty()) {
            notification.addError("Qualification comment is required");
        }
        String score = editQualificationCustomLegalCaseRequest.getScore().toString();
        if (score.isEmpty()) {
            notification.addError("Qualification score is required");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        return notification;
    }

    public Notification validateQualificationLA(EditQualificationLegalAdviceRequest editQualificationLegalAdviceRequest) {
        Notification notification = new Notification();
        String qualificationId = editQualificationLegalAdviceRequest.getQualificationId().trim();
        if (qualificationId.isEmpty()) {
            notification.addError("Qualification id is required");
        }
        String comment = editQualificationLegalAdviceRequest.getComment();
        if (comment.isEmpty()) {
            notification.addError("Qualification comment is required");
        }
        String score = editQualificationLegalAdviceRequest.getScore().toString();
        if (score.isEmpty()) {
            notification.addError("Qualification score is required");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        return notification;
    }
}
