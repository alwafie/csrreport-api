package org.enigma.csrreport.service.impl;

import lombok.RequiredArgsConstructor;
import org.enigma.csrreport.constant.ExpenseType;
import org.enigma.csrreport.dto.request.RealizationRequest;
import org.enigma.csrreport.dto.response.RealizationResponse;
import org.enigma.csrreport.entity.Program;
import org.enigma.csrreport.entity.Realization;
import org.enigma.csrreport.repository.RealizationRepository;
import org.enigma.csrreport.service.ProgramService;
import org.enigma.csrreport.service.RealizationService;
import org.enigma.csrreport.util.DateUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RealizationServiceImpl implements RealizationService {
    private final RealizationRepository realizationRepository;
    private final ProgramService programService;
    @Override
    public RealizationResponse create(RealizationRequest request) {
        Program program = programService.getOne(request.getProgramId());
        Realization realization = Realization.builder()
                .program(program)
                .description(request.getDescription())
                .expenseDate(DateUtil.stringToLocalDateTime(request.getExpenseDate()))
                .amount(request.getAmount())
                .expenseType(ExpenseType.findByName(request.getExpenseType()))
                .quantity(request.getQuantity())
                .build();
        realizationRepository.saveAndFlushWithNative(realization);
        return toResponse(realization);
    }

    @Override
    public RealizationResponse getById(String id) {
        Realization realization = getOne(id);
        return toResponse(realization);
    }

    @Override
    public Realization getOne(String id) {
        Optional<Realization> optionalRealization = realizationRepository.findByIdNative(id);
        if (optionalRealization.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return optionalRealization.get();
    }

    @Override
    public RealizationResponse updateById(String id, RealizationRequest request) {
        Realization realization = getOne(id);
        realization.setDescription(request.getDescription());
        realization.setAmount(request.getAmount());
        realization.setExpenseDate(DateUtil.stringToLocalDateTime(request.getExpenseDate()));
        realization.setExpenseType(ExpenseType.findByName(request.getExpenseType()));
        realization.setQuantity(request.getQuantity());
        updateOne(realization);
        return toResponse(realization);
    }

    @Override
    public void updateOne(Realization realization) {
        realizationRepository.updateByIdNative(realization);
    }

    @Override
    public void deleteById(String id) {
        Realization realization = getOne(id);
        realizationRepository.deleteByIdNative(realization.getId());
    }

    @Transactional(readOnly = true)
    @Override
    public Long calculateTotalRealizationByProgramId(String programId) {
        return realizationRepository.calculateTotalRealizationByProgramId(programId);
    }

    private RealizationResponse toResponse(Realization realization) {
        return RealizationResponse.builder()
                .id(realization.getId())
                .programId(realization.getProgram().getId())
                .description(realization.getDescription())
                .amount(realization.getAmount())
                .expenseType(realization.getExpenseType().getName())
                .quantity(realization.getQuantity())
                .build();
    }
}
