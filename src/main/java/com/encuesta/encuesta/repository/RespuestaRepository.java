package com.encuesta.encuesta.repository;

import com.encuesta.encuesta.entity.RespuestaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespuestaRepository extends PagingAndSortingRepository<RespuestaEntity, Long> {

    @Query(value = "SELECT * FROM respuesta e WHERE e.fk_id_pre=:id",nativeQuery = true)
    List<RespuestaEntity> getRespuestaPreguId(@Param("id") long id);


}
