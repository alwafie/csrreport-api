package org.enigma.csrreport.repository.custom;

import org.enigma.csrreport.entity.Realization;

public interface RealizationRepositoryCustom {
    Realization saveAndFlushWithNative(Realization realization);
}
