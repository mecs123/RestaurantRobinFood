package com.encuesta.encuesta.repository;

import com.encuesta.encuesta.entity.EncuestaEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncuestaRepository extends PagingAndSortingRepository<EncuestaEntity, Long> {

}
