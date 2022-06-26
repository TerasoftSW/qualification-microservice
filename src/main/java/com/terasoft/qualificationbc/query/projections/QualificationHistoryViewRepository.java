package com.terasoft.qualificationbc.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QualificationHistoryViewRepository extends JpaRepository<QualificationHistoryView, String> {

    @Query(value = "SELECT * FROM qualification_history_view WHERE qualification_history_id = (SELECT MAX(qualification_history_id) FROM qualification_history_view WHERE qualification_id = :customQualificationId)", nativeQuery = true)
    Optional<QualificationHistoryView> getLastByCustomQualificationId(String customQualificationId);

    @Query(value = "SELECT * FROM qualification_history_view WHERE qualification_id = :customQualificationId", nativeQuery = true)
    List<QualificationHistoryView> getHistoryByQualificationId(String customQualificationId);
}
