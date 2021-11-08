package com.encuesta.encuesta.repository;

import com.encuesta.encuesta.entity.PreguntaEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreguntaRepository extends PagingAndSortingRepository<PreguntaEntity, Long> {

}
