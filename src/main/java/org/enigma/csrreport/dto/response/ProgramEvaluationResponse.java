package org.enigma.csrreport.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProgramEvaluationResponse {
    private String id;
    private String programId;
    private Long totalBudget;
    private Long totalRealization;
    private Long budgetDifference;
    private Double evaluationIndex;
}
