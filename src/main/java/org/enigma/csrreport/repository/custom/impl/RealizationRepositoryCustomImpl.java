package org.enigma.csrreport.repository.custom.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.enigma.csrreport.entity.Realization;
import org.enigma.csrreport.repository.custom.RealizationRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public class RealizationRepositoryCustomImpl implements RealizationRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Realization saveAndFlushWithNative(Realization realization) {
        if (realization.getId() == null) {
            realization.setId(UUID.randomUUID().toString());
        }
        String sql = "INSERT INTO expense (id, program_id, expense_date, amount, expense_type, quantity, description) " +
                     "VALUES (:id, :programId, :expenseDate, :amount, :expenseType, :quantity, :description) " +
                     "RETURNING *";

        return (Realization) entityManager.createNativeQuery(sql, Realization.class)
                .setParameter("id", realization.getId())
                .setParameter("programId", realization.getProgram().getId())
                .setParameter("expenseDate", realization.getExpenseDate())
                .setParameter("amount", realization.getAmount())
                .setParameter("expenseType", realization.getExpenseType().name())
                .setParameter("quantity", realization.getQuantity())
                .setParameter("description", realization.getDescription())
                .getSingleResult();
    }

}
