package com.terasoft.qualificationbc.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificationViewRepository extends JpaRepository<QualificationView, String> {
}
