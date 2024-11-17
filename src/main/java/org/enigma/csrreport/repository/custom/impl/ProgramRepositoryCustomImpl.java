package org.enigma.csrreport.repository.custom.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.enigma.csrreport.repository.custom.ProgramRepositoryCustom;
import org.enigma.csrreport.entity.Program;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public class ProgramRepositoryCustomImpl implements ProgramRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Program saveAndFlushWithNative(Program program) {
        if (program.getId() == null) {
            program.setId(UUID.randomUUID().toString());
        }
        String sql = "INSERT INTO program (id, name, description, program_type, implementation_date, completion_date) " +
                     "VALUES (:id, :name, :description, :programType, :implementationDate, :completionDate) " +
                     "RETURNING *";

        return (Program) entityManager.createNativeQuery(sql, Program.class)
                .setParameter("id", program.getId())
                .setParameter("name", program.getName())
                .setParameter("description", program.getDescription())
                .setParameter("programType", program.getProgramType().name())
                .setParameter("implementationDate", program.getImplementationDate())
                .setParameter("completionDate", program.getCompletionDate())
                .getSingleResult();
    }
}
