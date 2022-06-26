package com.terasoft.qualificationbc.command.domain.entities;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;


import com.terasoft.qualificationsbccontracts.commands.EditQualificationCLC;
import com.terasoft.qualificationsbccontracts.commands.EditQualificationLA;
import com.terasoft.qualificationsbccontracts.commands.RegisterQualificationCLC;
import com.terasoft.qualificationsbccontracts.commands.RegisterQualificationLA;
import com.terasoft.qualificationsbccontracts.events.QualificationCLCEdited;
import com.terasoft.qualificationsbccontracts.events.QualificationCLCRegistered;
import com.terasoft.qualificationsbccontracts.events.QualificationLAEdited;
import com.terasoft.qualificationsbccontracts.events.QualificationLARegistered;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;

@Aggregate
public class Qualification {

    @AggregateIdentifier
    private String qualificationId;

    private String comment;

    private Integer score;

    private String lawyerId;

    private String customerId;

    private String customLegalCaseId;

    private String legalAdviceId;

    public Qualification() {}

    @CommandHandler
    public Qualification(RegisterQualificationCLC command) {
        apply(
                new QualificationCLCRegistered(
                        command.getQualificationId(),
                        command.getComment(),
                        command.getScore(),
                        command.getLawyerId(),
                        command.getCustomerId(),
                        command.getCustomLegalCaseId(),
                        Instant.now()
                )
        );
    }

    @CommandHandler
    public Qualification(RegisterQualificationLA command) {
        apply(
                new QualificationLARegistered(
                        command.getQualificationId(),
                        command.getComment(),
                        command.getScore(),
                        command.getLawyerId(),
                        command.getCustomerId(),
                        command.getLegalAdviceId(),
                        Instant.now()
                )
        );
    }

    @CommandHandler
    public void handle(EditQualificationCLC command ) {
        apply(
                new QualificationCLCEdited(
                        command.getQualificationId(),
                        command.getComment(),
                        command.getScore()
                )
        );
    }

    @CommandHandler
    public void handle(EditQualificationLA command) {
        apply(
                new QualificationLAEdited(
                        command.getQualificationId(),
                        command.getComment(),
                        command.getScore()
                )
        );
    }

    @EventSourcingHandler
    protected void on(QualificationCLCRegistered event) {
        this.qualificationId = event.getQualificationId();
        this.comment = event.getComment();
        this.score = event.getScore();
        this.lawyerId = event.getLawyerId();
        this.customerId = event.getCustomerId();
        this.customLegalCaseId = event.getCustomLegalCaseId();
    }

    @EventSourcingHandler
    protected void on(QualificationLARegistered event) {
        this.qualificationId = event.getQualificationId();
        this.comment = event.getComment();
        this.score = event.getScore();
        this.lawyerId = event.getLawyerId();
        this.customerId = event.getCustomerId();
        this.legalAdviceId = event.getLegalAdviceId();
    }

    @EventSourcingHandler
    protected void on(QualificationCLCEdited event) {
        this.qualificationId = event.getQualificationId();
        this.comment = event.getComment();
        this.score = event.getScore();
    }

    @EventSourcingHandler
    protected void on(QualificationLAEdited event) {
        this.qualificationId = event.getQualificationId();
        this.comment = event.getComment();
        this.score = event.getScore();
    }
}
