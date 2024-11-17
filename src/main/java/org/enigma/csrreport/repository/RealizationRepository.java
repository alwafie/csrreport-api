package org.enigma.csrreport.repository;

import org.enigma.csrreport.entity.Realization;
import org.enigma.csrreport.repository.custom.RealizationRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface RealizationRepository extends JpaRepository<Realization, String>, RealizationRepositoryCustom {
    @Query(value = "SELECT * FROM expense WHERE id = :id", nativeQuery = true)
    Optional<Realization> findByIdNative(@Param("id") String id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE expense SET  expense_date = :#{#realization.expenseDate}, " +
                   "amount = :#{#realization.amount}, expense_type = :#{#realization.expenseType.name()}, quantity = :#{#realization.quantity}, " +
                   "description = :#{#realization.description} WHERE id = :#{#realization.id}",
            nativeQuery = true)
    void updateByIdNative(@Param("realization") Realization realization);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM expense WHERE id = :id", nativeQuery = true)
    void deleteByIdNative(@Param("id") String id);

    @Query(value = "SELECT SUM(amount) FROM realization WHERE program_id = :programId", nativeQuery = true)
    Long calculateTotalRealizationByProgramId(@Param("programId") String programId);
}
