package com.encuesta.encuesta.repository;

import com.encuesta.encuesta.entity.RespuestaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaRepository extends CrudRepository<RespuestaEntity, Long> {



}
