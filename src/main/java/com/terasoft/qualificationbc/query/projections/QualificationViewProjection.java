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
public class QualificationViewProjection {
    private final QualificationViewRepository qualificationViewRepository;

    public QualificationViewProjection(QualificationViewRepository qualificationViewRepository) {
        this.qualificationViewRepository = qualificationViewRepository;
    }

    @EventHandler
    public void on(QualificationCLCRegistered event, @Timestamp Instant timestamp) {
        QualificationView qualificationView = new QualificationView(event.getQualificationId(), event.getComment(), event.getScore(), event.getLawyerId(), event.getCustomerId(), event.getCustomLegalCaseId(), "" ,  event.getOccurredOn());
        qualificationViewRepository.save(qualificationView);
    }

    @EventHandler
    public void on(QualificationLARegistered event, @Timestamp Instant timestamp) {
        QualificationView qualificationView = new QualificationView(event.getQualificationId(), event.getComment(), event.getScore(), event.getLawyerId(), event.getCustomerId(), "" , event.getLegalAdviceId(), event.getOccurredOn());
        qualificationViewRepository.save(qualificationView);
    }

    @EventHandler
    public void on(QualificationCLCEdited event, @Timestamp Instant timestamp) {
        Optional<QualificationView> qualificationViewOptional = qualificationViewRepository.findById(event.getQualificationId().toString());
        if (qualificationViewOptional.isPresent()) {
            QualificationView qualificationView = qualificationViewOptional.get();
            qualificationView.setComment(event.getComment());
            qualificationView.setScore(event.getScore());
            qualificationViewRepository.save(qualificationView);
        }
    }

    @EventHandler
    public void on(QualificationLAEdited event, @Timestamp Instant timestamp) {
        Optional<QualificationView> qualificationViewOptional = qualificationViewRepository.findById(event.getQualificationId().toString());
        if (qualificationViewOptional.isPresent()) {
            QualificationView qualificationView = qualificationViewOptional.get();
            qualificationView.setComment(event.getComment());
            qualificationView.setScore(event.getScore());
            qualificationViewRepository.save(qualificationView);
        }
    }
}
