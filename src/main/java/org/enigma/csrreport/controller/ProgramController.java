package org.enigma.csrreport.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.enigma.csrreport.constant.Constant;
import org.enigma.csrreport.dto.request.ProgramRequest;
import org.enigma.csrreport.dto.response.ProgramResponse;
import org.enigma.csrreport.service.ProgramService;
import org.enigma.csrreport.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = Constant.PROGRAM_API)
public class ProgramController {
    private final ProgramService programService;

    @PostMapping
    public ResponseEntity<?> createNewProgram(@RequestBody ProgramRequest request) {
        ProgramResponse programResponse = programService.create(request);
        return ResponseUtil.buildResponse(HttpStatus.CREATED, Constant.SUCCESS_CREATE_PROGRAM, programResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getProgramById(@PathVariable String id) {
        ProgramResponse programResponse = programService.getById(id);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_GET_PROGRAM_BY_ID, programResponse);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateProgramById(@PathVariable String id, @RequestBody ProgramRequest request) {
        ProgramResponse programResponse = programService.updateById(id, request);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_UPDATE_PROGRAM_BY_ID, programResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteProgramByid(@PathVariable String id) {
        programService.deleteById(id);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_DELETE_PROGRAM_BY_ID, null);
    }
}
