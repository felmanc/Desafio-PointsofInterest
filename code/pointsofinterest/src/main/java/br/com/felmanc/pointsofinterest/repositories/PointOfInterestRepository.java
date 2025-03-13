package br.com.felmanc.pointsofinterest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.felmanc.pointsofinterest.entities.PointOfInterestEntity;

@Repository
public interface PointOfInterestRepository extends JpaRepository<PointOfInterestEntity, Long> {

    @Query(value = "SELECT * FROM locations p WHERE SQRT(POW(p.x - :xReferencia, 2) + POW(p.y - :yReferencia, 2)) <= :dMax", nativeQuery = true)
    List<PointOfInterestEntity> findWithinDistance(@Param("xReferencia") Long xReferencia,
                                                   @Param("yReferencia") Long yReferencia,
                                                   @Param("dMax") Double dMax);
}

