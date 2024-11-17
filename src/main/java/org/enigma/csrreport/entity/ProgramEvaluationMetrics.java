package org.enigma.csrreport.entity;

import jakarta.persistence.*;
import lombok.*;
import org.enigma.csrreport.constant.Constant;

@Entity
@Table(name = Constant.PROGRAM_EVALUATION_METRICS)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProgramEvaluationMetrics {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @Column(name = "participant_passed")
    private Double participantsPassed;

    @Column(name = "total_participants")
    private Double totalParticipants;

    @Column(name = "income_before")
    private Double incomeBefore;

    @Column(name = "income_after")
    private Double incomeAfter;

    @Column(name = "trees_planted")
    private Double treesPlanted;

    @Column(name = "tree_target")
    private Double treeTarget;

    @Column(name = "carbon_reduced")
    private Double carbonReduced;

    @Column(name = "carbon_reduction_target")
    private Double carbonReductionTarget;
}
