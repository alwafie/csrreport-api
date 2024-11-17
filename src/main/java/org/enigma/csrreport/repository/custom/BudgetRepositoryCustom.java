package org.enigma.csrreport.repository.custom;

import org.enigma.csrreport.entity.Budget;

public interface BudgetRepositoryCustom {
    Budget saveAndFlushWithNative(Budget budget);
}
