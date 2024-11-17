package org.enigma.csrreport.service.impl;

import lombok.RequiredArgsConstructor;
import org.enigma.csrreport.constant.ExpenseType;
import org.enigma.csrreport.entity.Program;
import org.enigma.csrreport.repository.BudgetRepository;
import org.enigma.csrreport.dto.request.BudgetRequest;
import org.enigma.csrreport.dto.response.BudgetResponse;
import org.enigma.csrreport.entity.Budget;
import org.enigma.csrreport.service.BudgetService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {
    private final BudgetRepository budgetRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BudgetResponse createOne(BudgetRequest request, Program program) {
        Budget budget = Budget.builder()
                .program(program)
                .description(request.getDescription())
                .amount(request.getAmount())
                .expenseType(ExpenseType.findByName(request.getExpenseType()))
                .quantity(request.getQuantity())
                .build();
        budgetRepository.saveAndFlushWithNative(budget);
        return toResponse(budget);
    }

    @Transactional(readOnly = true)
    @Override
    public BudgetResponse getById(String id) {
        Budget budget = getOne(id);
        return toResponse(budget);
    }

    @Transactional(readOnly = true)
    @Override
    public Budget getOne(String id) {
        Optional<Budget> optionalBudget = budgetRepository.findByIdNative(id);
        if (optionalBudget.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return optionalBudget.get();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BudgetResponse updateById(String id, BudgetRequest request) {
        Budget budget = getOne(id);
        budget.setDescription(request.getDescription());
        budget.setAmount(request.getAmount());
        budget.setExpenseType(ExpenseType.findByName(request.getExpenseType()));
        budget.setQuantity(request.getQuantity());
        updateOne(budget);
        return toResponse(budget);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateOne(Budget budget) {
        budgetRepository.updateByIdNative(budget);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(String id) {
        Budget budget = getOne(id);
        budgetRepository.deleteByIdNative(budget.getId());
    }

    @Transactional(readOnly = true)
    @Override
    public Long calculateTotalBudgetByProgramId(String programId) {
        return budgetRepository.calculateTotalBudgetByProgramId(programId);
    }

    private BudgetResponse toResponse(Budget budget) {
        return BudgetResponse.builder()
                .id(budget.getId())
                .programId(budget.getProgram().getId())
                .description(budget.getDescription())
                .amount(budget.getAmount())
                .expenseType(budget.getExpenseType().getName())
                .quantity(budget.getQuantity())
                .build();
    }
}
