package org.enigma.csrreport.repository;

import org.enigma.csrreport.entity.ProgramEvaluation;
import org.enigma.csrreport.repository.custom.ProgramEvaluationRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProgramEvaluationRepository extends JpaRepository<ProgramEvaluation, String>, ProgramEvaluationRepositoryCustom {

    @Query(value = "SELECT * FROM evaluation WHERE program_id = :programId", nativeQuery = true)
    Optional<ProgramEvaluation> findByProgramId(@Param("programId") String programId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE program_evaluation SET total_budget = :totalBudget " +
                   "budget_difference = :budgetDifference, " +
                   "WHERE program_id = :programId", nativeQuery = true)
    void updateTotalBudgetByProgramId(@Param("totalBudget") Long totalBudget,
                                      @Param("budgetDifference") Long budgetDifference,
                                      @Param("programId") String programId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE program_evaluation SET total_realization = :totalRealization " +
                   "budget_difference = :budgetDifference, " +
                   "WHERE program_id = :programId", nativeQuery = true)
    void updateTotalRealizationByProgramId(@Param("totalRealization") Long totalRealization,
                                           @Param("budgetDifference") Long budgetDifference,
                                           @Param("programId") String programId);


}
