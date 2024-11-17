package org.enigma.csrreport.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RealizationRequest {
    private String programId;
    private String expenseDate;
    private String description;
    private Long amount;
    private String expenseType;
    private Integer quantity;
}
