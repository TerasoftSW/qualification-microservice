package com.terasoft.qualificationbc.query.projections;


import com.terasoft.qualificationsbccontracts.events.QualificationCLCEdited;
import com.terasoft.qualificationsbccontracts.events.QualificationCLCRegistered;
import com.terasoft.qualificationsbccontracts.events.QualificationLAEdited;
import com.terasoft.qualificationsbccontracts.events.QualificationLARegistered;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class QualificationHistoryViewProjection {
    private final QualificationHistoryViewRepository qualificationHistoryViewRepository;

    public QualificationHistoryViewProjection(QualificationHistoryViewRepository qualificationHistoryViewRepository) {
        this.qualificationHistoryViewRepository = qualificationHistoryViewRepository;
    }

    @EventHandler
    public void on(QualificationCLCRegistered event, @Timestamp Instant timestamp) {
        QualificationHistoryView qualificationHistoryView = new QualificationHistoryView(event.getQualificationId(), event.getComment(), event.getScore(), event.getLawyerId(), event.getCustomerId(), event.getCustomLegalCaseId(), "" ,  event.getOccurredOn());
        qualificationHistoryViewRepository.save(qualificationHistoryView);
    }

    @EventHandler
    public void on(QualificationLARegistered event, @Timestamp Instant timestamp) {
        QualificationHistoryView qualificationHistoryView = new QualificationHistoryView(event.getQualificationId(), event.getComment(), event.getScore(), event.getLawyerId(), event.getCustomerId(), "", event.getLegalAdviceId(),  event.getOccurredOn());
        qualificationHistoryViewRepository.save(qualificationHistoryView);
    }

    @EventHandler
    public void on(QualificationCLCEdited event, @Timestamp Instant timestamp) {
        Optional<QualificationHistoryView> qualificationViewOptional = qualificationHistoryViewRepository.getLastByCustomQualificationId(event.getQualificationId().toString());
        if (qualificationViewOptional.isPresent()) {
            QualificationHistoryView qualificationHistoryView = qualificationViewOptional.get();
            qualificationHistoryView = new QualificationHistoryView(qualificationHistoryView);
            qualificationHistoryView.setComment(event.getComment());
            qualificationHistoryView.setScore(event.getScore());
            qualificationHistoryViewRepository.save(qualificationHistoryView);
        }
    }

    @EventHandler
    public void on(QualificationLAEdited event, @Timestamp Instant timestamp) {
        Optional<QualificationHistoryView> qualificationViewOptional = qualificationHistoryViewRepository.getLastByCustomQualificationId(event.getQualificationId().toString());
        if (qualificationViewOptional.isPresent()) {
            QualificationHistoryView qualificationHistoryView = qualificationViewOptional.get();
            qualificationHistoryView = new QualificationHistoryView(qualificationHistoryView);
            qualificationHistoryView.setComment(event.getComment());
            qualificationHistoryView.setScore(event.getScore());
            qualificationHistoryViewRepository.save(qualificationHistoryView);
        }
    }
}
