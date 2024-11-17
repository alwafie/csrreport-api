package org.enigma.csrreport.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RealizationResponse {
    private String id;
    private String programId;
    private String expenseDate;
    private String description;
    private Long amount;
    private String expenseType;
    private Integer quantity;
}
