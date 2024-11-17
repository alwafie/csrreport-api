package org.enigma.csrreport.service;

import org.enigma.csrreport.dto.response.ProgramEvaluationResponse;
import org.enigma.csrreport.entity.Program;
import org.enigma.csrreport.entity.ProgramEvaluation;

public interface ProgramEvaluationService {
    void create (Program program);
    ProgramEvaluation getOneByProgramId(String programId);

    void updateTotalBudgetAfterSaveOrUpdate(String programId, Long totalBudget);

    void updateTotalRealizationAfterSaveOrUpdate(String programId, Long totalRealization);
}
