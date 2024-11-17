package org.enigma.csrreport.entity;

import jakarta.persistence.*;
import lombok.*;
import org.enigma.csrreport.constant.Constant;
import org.enigma.csrreport.constant.ExpenseType;

@Entity
@Table(name = Constant.BUDGET_TABLE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Budget {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "expense_type")
    private ExpenseType expenseType;

    @Column(name = "quantity")
    private Integer quantity;
}
