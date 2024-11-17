package org.enigma.csrreport.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProgramType {

    FINANCIAL_LITERACY("Program Literasi Keuangan", "FINANCIAL LITERACY IMPROVEMENT INDEX"),
    POVERTY_ALLEVIATION("Program Pengentasan Kemiskinan", "POVERTY ALLEVIATION INDEX"),
    ENVIRONMENTAL_INITIATIVE("Inisiatif Lingkungan", "GREEN IMPACT INDEX"),
    CARBON_REDUCTION("Program Pengurangan Karbon", "CARBON REDUCTION INDEX");

    private final String name;
    private final String metricType;

    public static ProgramType findByName(String name) {
        for (ProgramType value : values()) {
            if (value.name.equalsIgnoreCase(name)){
                return value;
            }
        }
        return null;
    }
}


