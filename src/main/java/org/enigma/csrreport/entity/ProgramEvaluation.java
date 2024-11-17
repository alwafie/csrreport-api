package org.enigma.csrreport.entity;

import jakarta.persistence.*;
import lombok.*;
import org.enigma.csrreport.constant.Constant;

@Entity
@Table(name = Constant.PROGRAM_EVALUATION_TABLE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProgramEvaluation {
    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @Column(name = "total_budget")
    private Long totalBudget;

    @Column(name = "total_realization")
    private Long totalRealization;

    @Column(name = "budget_difference")
    private Long budgetDifference;

    @Column(name = "evaluation_index")
    private Double evaluationIndex;
}
