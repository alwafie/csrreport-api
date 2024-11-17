package org.enigma.csrreport.controller;

import lombok.RequiredArgsConstructor;
import org.enigma.csrreport.constant.Constant;
import org.enigma.csrreport.dto.request.BudgetRequest;
import org.enigma.csrreport.dto.response.BudgetResponse;
import org.enigma.csrreport.service.BudgetService;
import org.enigma.csrreport.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = Constant.BUDGET_API)
public class BudgetController {
    private final BudgetService budgetService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getBudgetById(@PathVariable String id) {
        BudgetResponse budgetResponse = budgetService.getById(id);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_GET_BUDGET_BY_ID, budgetResponse);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateBudgetById(@PathVariable String id, @RequestBody BudgetRequest request) {
        BudgetResponse budgetResponse = budgetService.updateById(id, request);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_UPDATE_BUDGET_BY_ID, budgetResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteBudgetByid(@PathVariable String id) {
        budgetService.deleteById(id);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_DELETE_BUDGET_BY_ID, null);
    }
}
