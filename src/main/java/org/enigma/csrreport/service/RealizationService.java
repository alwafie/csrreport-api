package org.enigma.csrreport.service;

import org.enigma.csrreport.dto.request.RealizationRequest;
import org.enigma.csrreport.dto.response.RealizationResponse;
import org.enigma.csrreport.entity.Realization;

public interface RealizationService {
    RealizationResponse create(RealizationRequest request);
    RealizationResponse getById(String id);
    Realization getOne(String id);
    RealizationResponse updateById(String id, RealizationRequest request);
    void updateOne(Realization realization);
    void deleteById(String id);
    Long calculateTotalRealizationByProgramId(String programId);
}
