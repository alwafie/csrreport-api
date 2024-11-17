package org.enigma.csrreport.service;

import org.enigma.csrreport.dto.request.BudgetRequest;
import org.enigma.csrreport.dto.response.BudgetResponse;
import org.enigma.csrreport.entity.Budget;
import org.enigma.csrreport.entity.Program;

public interface BudgetService {
    BudgetResponse createOne(BudgetRequest request, Program program);
    BudgetResponse getById(String id);
    Budget getOne(String id);
    BudgetResponse updateById(String id, BudgetRequest request);
    void updateOne(Budget budget);
    void deleteById(String id);
    Long calculateTotalBudgetByProgramId(String programId);
}
