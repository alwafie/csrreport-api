package org.enigma.csrreport.repository;

import org.enigma.csrreport.entity.Budget;
import org.enigma.csrreport.repository.custom.BudgetRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, String>, BudgetRepositoryCustom {
    @Query(value = "SELECT * FROM budget WHERE id = :id", nativeQuery = true)
    Optional<Budget> findByIdNative(@Param("id") String id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM budget WHERE id = :id", nativeQuery = true)
    void deleteByIdNative(@Param("id") String id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE budget SET description = :#{#budget.description}, amount = :#{#budget.amount}, " +
                   "expense_type = :#{#budget.expenseType.name()}, quantity = :#{#budget.quantity} " +
                   "WHERE id = :#{#budget.id}", nativeQuery = true)
    void updateByIdNative(@Param("budget") Budget budget);

    @Query(value = "SELECT SUM(amount) FROM budget WHERE program_id = :programId", nativeQuery = true)
    Long calculateTotalBudgetByProgramId(@Param("programId") String programId);
}
