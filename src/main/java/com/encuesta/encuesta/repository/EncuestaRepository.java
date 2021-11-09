package com.encuesta.encuesta.repository;

import com.encuesta.encuesta.entity.EncuestaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EncuestaRepository extends CrudRepository<EncuestaEntity, Long> {

    @Query(value = "SELECT * FROM encuesta e WHERE e.id_encu=:id",nativeQuery = true)
    EncuestaEntity getEncuestaId(@Param("id") long id);

}
