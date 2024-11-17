package org.enigma.csrreport.service.impl;

import lombok.RequiredArgsConstructor;
import org.enigma.csrreport.constant.ApprovalStatus;
import org.enigma.csrreport.constant.ProgramType;
import org.enigma.csrreport.dto.request.ProgramRequest;
import org.enigma.csrreport.dto.response.BudgetResponse;
import org.enigma.csrreport.dto.response.ProgramResponse;
import org.enigma.csrreport.entity.Program;
import org.enigma.csrreport.repository.ProgramRepository;
import org.enigma.csrreport.service.BudgetService;
import org.enigma.csrreport.service.ProgramEvaluationService;
import org.enigma.csrreport.service.ProgramService;
import org.enigma.csrreport.util.DateUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepository programRepository;
    private final BudgetService budgetService;
    private final ProgramEvaluationService programEvaluationService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ProgramResponse create(ProgramRequest request) {
        Program program = Program.builder()
                .name(request.getName())
                .description(request.getDescription())
                .programType(ProgramType.findByName(request.getProgramType()))
                .approvalStatus(ApprovalStatus.PENDING)
                .implementationDate(DateUtil.stringToLocalDateTime(request.getImplementationDate()))
                .completionDate(DateUtil.stringToLocalDateTime(request.getCompletionDate()))
                .build();

        programRepository.saveAndFlushWithNative(program);
        programEvaluationService.create(program);

        List<BudgetResponse> budgetResponseList = request.getBudgetRequestList().stream()
                .map(budgetRequest -> budgetService.createOne(budgetRequest, program))
                .collect(Collectors.toList());

        return toResponseCreate(program, budgetResponseList);
    }


    @Transactional(readOnly = true)
    @Override
    public ProgramResponse getById(String id) {
        Program program = getOne(id);
        return toResponse(program);
    }

    @Transactional(readOnly = true)
    @Override
    public Program getOne(String id) {
        Optional<Program> optionalProgram = programRepository.findByIdNative(id);
        if (optionalProgram.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return optionalProgram.get();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateOne(Program program) {
        programRepository.updateByIdNative(program);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ProgramResponse updateById(String id, ProgramRequest request) {
        Program program = getOne(id);
        program.setName(request.getName());
        program.setDescription(request.getDescription());
        program.setProgramType(ProgramType.findByName(request.getProgramType()));
        program.setImplementationDate(DateUtil.stringToLocalDateTime(request.getImplementationDate()));
        program.setCompletionDate(DateUtil.stringToLocalDateTime(request.getCompletionDate()));
        updateOne(program);
        return toResponse(program);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(String id) {
        Program program = getOne(id);
        programRepository.deleteByIdNative(program.getId());
    }

    private ProgramResponse toResponseCreate(Program program, List<BudgetResponse> budgetResponseList) {
        ProgramResponse programResponse = toResponse(program);
        programResponse.setBudgetResponseList(budgetResponseList);
        return programResponse;
    }
    private ProgramResponse toResponse(Program program) {
        return ProgramResponse.builder()
                .id(program.getId())
                .name(program.getName() != null ? program.getName() : "N/A")
                .description(program.getDescription() != null ? program.getDescription() : "No description provided")
                .programType(program.getProgramType() != null ? program.getProgramType().getName() : "Unknown")
                .approvedBy(program.getUserAccount() != null && program.getUserAccount().getFullName() != null ? program.getUserAccount().getFullName() : "Unknown")
                .approvalStatus(program.getApprovalStatus() != null ? program.getApprovalStatus().name() : "Unknown")
                .implementationDate(program.getImplementationDate() != null ? DateUtil.localDateTimeToString(program.getImplementationDate()) : "TBD")
                .completionDate(program.getCompletionDate() != null ? DateUtil.localDateTimeToString(program.getCompletionDate()) : "TBD")
                .build();
    }

}
