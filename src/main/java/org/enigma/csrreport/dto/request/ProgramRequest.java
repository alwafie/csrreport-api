package org.enigma.csrreport.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProgramRequest {
    private String name;
    private String description;
    private String programType;
    private String implementationDate;
    private String completionDate;
    private List<BudgetRequest> budgetRequestList;
}
