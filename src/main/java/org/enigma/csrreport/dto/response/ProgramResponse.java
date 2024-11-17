package org.enigma.csrreport.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProgramResponse {
    private String id;
    private String name;
    private String description;
    private String programType;
    private String approvedBy;
    private String approvalStatus;
    private String implementationDate;
    private String completionDate;
    private List<BudgetResponse> budgetResponseList;
}
