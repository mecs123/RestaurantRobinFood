package com.encuesta.encuesta.repository;

import com.encuesta.encuesta.entity.PreguntaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreguntaRepository extends PagingAndSortingRepository<PreguntaEntity, Long> {
    @Query(value = "SELECT * FROM pregunta p WHERE p.fk_id_encu=:id",nativeQuery = true)
    List<PreguntaEntity> getPreguntaId(Long id);

    PreguntaEntity findByIdPregunta(Long idPregunta);
}
