package com.terasoft.qualificationbc.command.application.services;

import com.terasoft.common.application.Notification;
import com.terasoft.common.application.Result;
import com.terasoft.common.application.ResultType;
import com.terasoft.qualificationbc.command.application.dtos.request.EditQualificationCustomLegalCaseRequest;
import com.terasoft.qualificationbc.command.application.dtos.request.EditQualificationLegalAdviceRequest;
import com.terasoft.qualificationbc.command.application.dtos.request.RegisterQualificationCustomLegalCaseRequest;
import com.terasoft.qualificationbc.command.application.dtos.request.RegisterQualificationLegalAdviceRequest;
import com.terasoft.qualificationbc.command.application.dtos.response.EditQualificationCustomLegalCaseResponse;
import com.terasoft.qualificationbc.command.application.dtos.response.EditQualificationLegalAdviceResponse;
import com.terasoft.qualificationbc.command.application.dtos.response.RegisterQualificationCustomLegalCaseResponse;
import com.terasoft.qualificationbc.command.application.dtos.response.RegisterQualificationLegalAdviceResponse;
import com.terasoft.qualificationbc.command.application.validators.EditQualificationValidator;
import com.terasoft.qualificationbc.command.application.validators.RegisterQualificationValidator;
import com.terasoft.qualificationsbccontracts.commands.EditQualificationCLC;
import com.terasoft.qualificationsbccontracts.commands.EditQualificationLA;
import com.terasoft.qualificationsbccontracts.commands.RegisterQualificationCLC;
import com.terasoft.qualificationsbccontracts.commands.RegisterQualificationLA;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class QualificationApplicationService {
    private final RegisterQualificationValidator registerQualificationValidator;
    private final EditQualificationValidator editQualificationValidator;
    private final CommandGateway commandGateway;

    public QualificationApplicationService(RegisterQualificationValidator registerQualificationValidator,EditQualificationValidator editQualificationValidator, CommandGateway commandGateway) {
        this.registerQualificationValidator = registerQualificationValidator;
        this.editQualificationValidator = editQualificationValidator;
        this.commandGateway = commandGateway;
    }

    public Result<RegisterQualificationCustomLegalCaseResponse, Notification> registerQualificationCLC(RegisterQualificationCustomLegalCaseRequest request) throws Exception {
        Notification notification = this.registerQualificationValidator.validateQualificationCLC(request);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        String qualificationId = UUID.randomUUID().toString();
        RegisterQualificationCLC registerQualificationCLC = new RegisterQualificationCLC(
                qualificationId,
                request.getComment(),
                request.getScore(),
                request.getCustomerId(),
                request.getLawyerId(),
                request.getCustomLegalCaseId()
        );
        CompletableFuture<Object> future = commandGateway.send(registerQualificationCLC);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        RegisterQualificationCustomLegalCaseResponse registerQualificationCustomLegalCaseResponse = new RegisterQualificationCustomLegalCaseResponse(
                registerQualificationCLC.getQualificationId(),
                registerQualificationCLC.getComment(),
                registerQualificationCLC.getScore(),
                registerQualificationCLC.getCustomerId(),
                registerQualificationCLC.getLawyerId(),
                registerQualificationCLC.getCustomLegalCaseId()
        );
        return Result.success(registerQualificationCustomLegalCaseResponse);
    }

    public Result<RegisterQualificationLegalAdviceResponse, Notification> registerQualificationLA(RegisterQualificationLegalAdviceRequest request) throws Exception {
        Notification notification = this.registerQualificationValidator.validateQualificationLA(request);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        String qualificationId = UUID.randomUUID().toString();
        RegisterQualificationLA registerQualificationLA = new RegisterQualificationLA(
                qualificationId,
                request.getComment(),
                request.getScore(),
                request.getCustomerId(),
                request.getLawyerId(),
                request.getLegalAdviceId()
        );
        CompletableFuture<Object> future = commandGateway.send(registerQualificationLA);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        RegisterQualificationLegalAdviceResponse registerQualificationLegalAdviceResponse = new RegisterQualificationLegalAdviceResponse(
                registerQualificationLA.getQualificationId(),
                registerQualificationLA.getComment(),
                registerQualificationLA.getScore(),
                registerQualificationLA.getCustomerId(),
                registerQualificationLA.getLawyerId(),
                registerQualificationLA.getLegalAdviceId()
        );
        return Result.success(registerQualificationLegalAdviceResponse);
    }

    public Result<EditQualificationCustomLegalCaseResponse, Notification> editQualificationCLC(EditQualificationCustomLegalCaseRequest request) throws Exception{
        Notification notification = this.editQualificationValidator.validateQualificationCLC(request);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        EditQualificationCLC editQualificationCLC = new EditQualificationCLC(
                request.getQualificationId().trim(),
                request.getComment(),
                request.getScore()
        );
        CompletableFuture<Object> future = commandGateway.send(editQualificationCLC);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        EditQualificationCustomLegalCaseResponse editQualificationCustomLegalCaseResponse = new EditQualificationCustomLegalCaseResponse(
                editQualificationCLC.getQualificationId(),
                editQualificationCLC.getComment(),
                editQualificationCLC.getScore()
        );
        return Result.success(editQualificationCustomLegalCaseResponse);
    }

    public Result<EditQualificationLegalAdviceResponse, Notification> editQualificationLA(EditQualificationLegalAdviceRequest request) throws Exception{
        Notification notification = this.editQualificationValidator.validateQualificationLA(request);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        EditQualificationLA editQualificationLA = new EditQualificationLA(
                request.getQualificationId().trim(),
                request.getComment(),
                request.getScore()
        );
        CompletableFuture<Object> future = commandGateway.send(editQualificationLA);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        EditQualificationLegalAdviceResponse editQualificationLegalAdviceResponse = new EditQualificationLegalAdviceResponse(
                editQualificationLA.getQualificationId(),
                editQualificationLA.getComment(),
                editQualificationLA.getScore()
        );
        return Result.success(editQualificationLegalAdviceResponse);
    }
}
