package org.enigma.csrreport.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExpenseType {
    OPERATIONAL("Operational"),
    ADMINISTRATIVE("Administrative"),
    COMMUNITY_SUPPORT("Community Support"),
    TRAINING_AND_CAPACITY_BUILDING("Trainin and Capacity Building"),
    ENVIRONMENTAL_INITIATIVES("Environmental Initiatives"),
    MARKETING_AND_AWARENESS("Marketing and Awareness"),
    MONITORING_AND_EVALUATION("Monitoring and Evaluation"),
    PARTNERSHIP_AND_COLLABORATION("Partnership and Collaboration");

    private final String name;

    public static ExpenseType findByName(String name) {
        for (ExpenseType value : values()) {
            if (value.name.equalsIgnoreCase(name)) {
                return value;
            }
        }
        return null;
    }
}
