package org.enigma.csrreport.repository.custom;

import org.enigma.csrreport.entity.Program;

public interface ProgramRepositoryCustom {
    Program saveAndFlushWithNative(Program program);
}
