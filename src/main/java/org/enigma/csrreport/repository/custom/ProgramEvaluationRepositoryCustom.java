package org.enigma.csrreport.repository.custom;

import org.enigma.csrreport.entity.ProgramEvaluation;

public interface ProgramEvaluationRepositoryCustom {
    ProgramEvaluation saveAndFlushWithNative(ProgramEvaluation programEvaluation);
}
