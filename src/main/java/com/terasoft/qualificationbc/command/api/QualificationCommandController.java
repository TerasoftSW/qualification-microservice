package com.terasoft.qualificationbc.command.api;

import com.terasoft.common.api.ApiController;
import com.terasoft.common.application.Notification;
import com.terasoft.common.application.Result;
import com.terasoft.qualificationbc.command.application.dtos.request.EditQualificationCustomLegalCaseRequest;
import com.terasoft.qualificationbc.command.application.dtos.request.EditQualificationLegalAdviceRequest;
import com.terasoft.qualificationbc.command.application.dtos.request.RegisterQualificationCustomLegalCaseRequest;
import com.terasoft.qualificationbc.command.application.dtos.request.RegisterQualificationLegalAdviceRequest;
import com.terasoft.qualificationbc.command.application.dtos.response.EditQualificationCustomLegalCaseResponse;
import com.terasoft.qualificationbc.command.application.dtos.response.EditQualificationLegalAdviceResponse;
import com.terasoft.qualificationbc.command.application.dtos.response.RegisterQualificationCustomLegalCaseResponse;
import com.terasoft.qualificationbc.command.application.dtos.response.RegisterQualificationLegalAdviceResponse;
import com.terasoft.qualificationbc.command.application.services.QualificationApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qualifications")
@Tag(name= "Qualifications")
public class QualificationCommandController {

    private final QualificationApplicationService qualificationApplicationService;

    public QualificationCommandController(QualificationApplicationService qualificationApplicationService) {
        this.qualificationApplicationService = qualificationApplicationService;
    }

    @PostMapping(path = "/customLegalCase", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerQualificationCLC(@RequestBody RegisterQualificationCustomLegalCaseRequest registerQualificationRequest) {
        try {
            Result<RegisterQualificationCustomLegalCaseResponse, Notification> result = qualificationApplicationService.registerQualificationCLC(registerQualificationRequest);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

    @PostMapping(path = "/legalAdvice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerQualificationLA(@RequestBody RegisterQualificationLegalAdviceRequest registerQualificationRequest) {
        try {
            Result<RegisterQualificationLegalAdviceResponse, Notification> result = qualificationApplicationService.registerQualificationLA(registerQualificationRequest);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

    @PutMapping(path = "/customLegalCase/{qualificationId}")
    public ResponseEntity<Object> editQualificationCLC(@PathVariable("qualificationId") String qualificationId, @RequestBody EditQualificationCustomLegalCaseRequest editRequest) {
        try {
            editRequest.setQualificationId(qualificationId);
            Result<EditQualificationCustomLegalCaseResponse, Notification> result = qualificationApplicationService.editQualificationCLC(editRequest);
            if (result.isSuccess()) {
                return ApiController.ok(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (AggregateNotFoundException e) {
            return ApiController.notFound();
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

    @PutMapping(path = "/legalAdvice/{qualificationId}")
    public ResponseEntity<Object> editQualificationLA(@PathVariable("qualificationId") String qualificationId, @RequestBody EditQualificationLegalAdviceRequest editRequest) {
        try {
            editRequest.setQualificationId(qualificationId);
            Result<EditQualificationLegalAdviceResponse, Notification> result = qualificationApplicationService.editQualificationLA(editRequest);
            if (result.isSuccess()) {
                return ApiController.ok(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (AggregateNotFoundException e) {
            return ApiController.notFound();
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }
}
