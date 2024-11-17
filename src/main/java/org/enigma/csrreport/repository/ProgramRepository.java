package org.enigma.csrreport.repository;

import org.enigma.csrreport.repository.custom.ProgramRepositoryCustom;
import org.enigma.csrreport.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProgramRepository extends JpaRepository<Program, String>, ProgramRepositoryCustom {

    @Query(value = "SELECT * FROM program WHERE id = :id", nativeQuery = true)
    Optional<Program> findByIdNative(@Param("id") String id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE program SET name = :#{#program.name}, description = :#{#program.description}, " +
                   "program_type = :#{#program.programType.name()}, " +
                   "implementation_date = :#{#program.implementationDate}, " +
                   "completion_date = :#{#program.completionDate} WHERE id = :#{#program.id}",
            nativeQuery = true)
    void updateByIdNative(@Param("program") Program program);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM program WHERE id = :id", nativeQuery = true)
    void deleteByIdNative(@Param("id") String id);
}
