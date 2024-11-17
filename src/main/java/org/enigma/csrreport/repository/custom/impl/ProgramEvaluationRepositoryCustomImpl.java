package org.enigma.csrreport.repository.custom.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.enigma.csrreport.entity.ProgramEvaluation;
import org.enigma.csrreport.repository.custom.ProgramEvaluationRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public class ProgramEvaluationRepositoryCustomImpl implements ProgramEvaluationRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public ProgramEvaluation saveAndFlushWithNative(ProgramEvaluation programEvaluation) {
        if (programEvaluation.getId() == null) {
            programEvaluation.setId(UUID.randomUUID().toString());
        }
        String sql = "INSERT INTO program_evaluation (id, program_id) " +
                     "VALUES (:id, :programId) " +
                     "RETURNING id";

        return (ProgramEvaluation) entityManager.createNativeQuery(sql)
                .setParameter("id", programEvaluation.getId())
                .setParameter("programId", programEvaluation.getProgram().getId())
                .getSingleResult();
    }

}
