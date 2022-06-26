package com.terasoft.qualificationbc.query.api;

import com.terasoft.qualificationbc.query.projections.QualificationHistoryViewRepository;
import com.terasoft.qualificationbc.query.projections.QualificationView;
import com.terasoft.qualificationbc.query.projections.QualificationViewRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/qualifications")
public class QualificationQueryController {
    private final QualificationViewRepository qualificationViewRepository;
    private final QualificationHistoryViewRepository qualificationHistoryViewRepository;

    public QualificationQueryController(QualificationViewRepository qualificationViewRepository, QualificationHistoryViewRepository qualificationHistoryViewRepository) {
        this.qualificationViewRepository = qualificationViewRepository;
        this.qualificationHistoryViewRepository = qualificationHistoryViewRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<QualificationView>> getAll() {
        try {
            return new ResponseEntity<List<QualificationView>>(qualificationViewRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QualificationView> getById(@PathVariable("id") String id) {
        try {
            Optional<QualificationView> qualificationViewOptional = qualificationViewRepository.findById(id);
            if (qualificationViewOptional.isPresent()) {
                return new ResponseEntity<QualificationView>(qualificationViewOptional.get(), HttpStatus.OK);
            }
            return new ResponseEntity("NOT FOUND", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}