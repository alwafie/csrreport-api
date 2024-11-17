package org.enigma.csrreport.entity;

import jakarta.persistence.*;
import lombok.*;
import org.enigma.csrreport.constant.Constant;
import org.enigma.csrreport.constant.ExpenseType;

import java.time.LocalDateTime;

@Entity
@Table(name = Constant.EXPENSE_TABLE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Realization {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expense_date")
    private LocalDateTime expenseDate;

    @Column(name = "amount")
    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "expense_type")
    private ExpenseType expenseType;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "description")
    private String description;
}
