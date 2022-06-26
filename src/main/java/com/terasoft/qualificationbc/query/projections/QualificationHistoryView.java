package com.terasoft.qualificationbc.query.projections;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class QualificationHistoryView {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long qualificationHistoryId;

    @Column(length = 36)
    @Getter
    @Setter
    private String qualificationId;

    @Column(length = 36)
    @Getter
    @Setter
    private String comment;

    @Column
    @Getter
    @Setter
    private Integer score;

    @Column(length = 36)
    @Getter
    @Setter
    private String lawyerId;

    @Column(length = 36)
    @Getter
    @Setter
    private String customerId;

    @Column(length = 36)
    @Getter
    @Setter
    private String customLegalCaseId;

    @Column(length = 36)
    @Getter
    @Setter
    private String legalAdviceId;

    @Getter
    @Setter
    private Instant createdAt;

    public QualificationHistoryView() {}

    public QualificationHistoryView(String qualificationId, String comment, Integer score, String lawyerId, String customerId, String customLegalCaseId, String legalAdviceId, Instant createdAt) {
        this.qualificationId = qualificationId;
        this.comment = comment;
        this.score = score;
        this.lawyerId = lawyerId;
        this.customerId = customerId;
        this.customLegalCaseId = customLegalCaseId;
        this.legalAdviceId = legalAdviceId;
        this.createdAt = createdAt;
    }

    public QualificationHistoryView(QualificationHistoryView qualificationHistoryView) {
        this.qualificationId = qualificationHistoryView.getQualificationId();
        this.comment = qualificationHistoryView.getComment();
        this.score = qualificationHistoryView.getScore();
    }
}
