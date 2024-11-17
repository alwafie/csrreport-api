package org.enigma.csrreport.service;

import org.enigma.csrreport.dto.request.ProgramRequest;
import org.enigma.csrreport.dto.response.ProgramResponse;
import org.enigma.csrreport.entity.Program;

public interface ProgramService {
    ProgramResponse create(ProgramRequest request);
    ProgramResponse getById(String id);
    Program getOne(String id);
    ProgramResponse updateById(String id, ProgramRequest request);
    void updateOne(Program program);
    void deleteById(String id);
}
