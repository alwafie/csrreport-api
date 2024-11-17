package org.enigma.csrreport.controller;

import lombok.RequiredArgsConstructor;
import org.enigma.csrreport.constant.Constant;
import org.enigma.csrreport.dto.request.RealizationRequest;
import org.enigma.csrreport.dto.response.RealizationResponse;
import org.enigma.csrreport.service.RealizationService;
import org.enigma.csrreport.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = Constant.REALIZATION_API)
public class RealizationController {
    private final RealizationService realizationService;

    @PostMapping
    public ResponseEntity<?> createNewRealization(@RequestBody RealizationRequest request) {
        RealizationResponse realizationResponse = realizationService.create(request);
        return ResponseUtil.buildResponse(HttpStatus.CREATED, Constant.SUCCESS_CREATE_REALIZATION, realizationResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getRealizationById(@PathVariable String id) {
        RealizationResponse realizationResponse = realizationService.getById(id);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_GET_REALIZATION_BY_ID, realizationResponse);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateRealizationById(@PathVariable String id, @RequestBody RealizationRequest request) {
        RealizationResponse realizationResponse = realizationService.updateById(id, request);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_UPDATE_REALIZATION_BY_ID, realizationResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteRealizationByid(@PathVariable String id) {
        realizationService.deleteById(id);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_DELETE_REALIZATION_BY_ID, null);
    }
}
