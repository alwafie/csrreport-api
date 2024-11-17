package org.enigma.csrreport.repository.custom.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.enigma.csrreport.entity.Budget;
import org.enigma.csrreport.repository.custom.BudgetRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public class BudgetRepositoryCustomImpl implements BudgetRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Budget saveAndFlushWithNative(Budget budget) {
        if (budget.getId() == null) {
            budget.setId(UUID.randomUUID().toString());
        }

        String sql = "INSERT INTO budget (id, program_id, description, amount, expense_type, quantity) " +
                     "VALUES (:id, :programId, :description, :amount, :expenseType, :quantity) " +
                     "RETURNING *";

        return (Budget) entityManager.createNativeQuery(sql, Budget.class)
                .setParameter("id", budget.getId())
                .setParameter("programId", budget.getProgram() != null ? budget.getProgram().getId() : null)
                .setParameter("description", budget.getDescription())
                .setParameter("amount", budget.getAmount())
                .setParameter("expenseType", budget.getExpenseType().name())
                .setParameter("quantity", budget.getQuantity())
                .getSingleResult();
    }
}
