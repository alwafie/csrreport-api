package org.enigma.csrreport.entity;

import jakarta.persistence.*;
import lombok.*;
import org.enigma.csrreport.constant.ApprovalStatus;
import org.enigma.csrreport.constant.Constant;
import org.enigma.csrreport.constant.ProgramType;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = Constant.PROGRAM_TABLE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Program {
    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "program_type", nullable = false)
    private ProgramType programType;

    @OneToOne
    @JoinColumn(name = "approvedBy")
    private UserAccount userAccount;

    @Column(name = "approval_status")
    private ApprovalStatus approvalStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "implementation_date")
    private LocalDateTime implementationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "completion_date")
    private LocalDateTime completionDate;

    @OneToMany(mappedBy = "program")
    private List<Budget> budgets;

    @OneToMany(mappedBy = "program")
    private List<Realization> realizations;

}
