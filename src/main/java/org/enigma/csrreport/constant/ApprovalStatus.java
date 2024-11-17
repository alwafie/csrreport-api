package org.enigma.csrreport.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApprovalStatus {
    PENDING,
    APPROVED,
    REJECTED,
}
