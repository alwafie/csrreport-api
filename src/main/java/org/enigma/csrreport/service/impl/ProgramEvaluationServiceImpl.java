package org.enigma.csrreport.service.impl;

import lombok.RequiredArgsConstructor;
import org.enigma.csrreport.dto.response.ProgramEvaluationResponse;
import org.enigma.csrreport.entity.Program;
import org.enigma.csrreport.entity.ProgramEvaluation;
import org.enigma.csrreport.repository.ProgramEvaluationRepository;
import org.enigma.csrreport.service.ProgramEvaluationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProgramEvaluationServiceImpl implements ProgramEvaluationService {
    private final ProgramEvaluationRepository programEvaluationRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Program program) {
        ProgramEvaluation programEvaluation = ProgramEvaluation.builder()
                .program(program)
                .build();
        programEvaluationRepository.saveAndFlushWithNative(programEvaluation);
//        return toResponse(programEvaluation);
    }

    @Transactional(readOnly = true)
    @Override
    public ProgramEvaluation getOneByProgramId(String programId) {
        Optional<ProgramEvaluation> optionalProgramEvaluation = programEvaluationRepository.findByProgramId(programId);
        if (optionalProgramEvaluation.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return optionalProgramEvaluation.get();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateTotalBudgetAfterSaveOrUpdate(String programId, Long totalBudget) {
        ProgramEvaluation programEvaluation = getOneByProgramId(programId);
        Long totalRealization = programEvaluation.getTotalRealization();
        Long budgetDifference = totalBudget - totalRealization;
        programEvaluationRepository.updateTotalBudgetByProgramId(totalBudget, budgetDifference, programId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateTotalRealizationAfterSaveOrUpdate(String programId, Long totalRealization) {
        ProgramEvaluation programEvaluation = getOneByProgramId(programId);
        Long totalBudget = programEvaluation.getTotalBudget();
        Long budgetDifference = totalBudget - totalRealization;
        programEvaluationRepository.updateTotalRealizationByProgramId(totalRealization, budgetDifference, programId);
    }

    private ProgramEvaluationResponse toResponse(ProgramEvaluation programEvaluation) {
        return ProgramEvaluationResponse.builder()
                .id(programEvaluation.getId())
                .programId(programEvaluation.getProgram().getId())
                .totalBudget(programEvaluation.getTotalBudget())
                .totalRealization(programEvaluation.getTotalRealization())
                .budgetDifference(programEvaluation.getBudgetDifference())
                .evaluationIndex(programEvaluation.getEvaluationIndex() != null ? programEvaluation.getEvaluationIndex() : 0.0)
                .build();
    }
}
