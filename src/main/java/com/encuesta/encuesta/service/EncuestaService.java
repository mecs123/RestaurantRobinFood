package com.encuesta.encuesta.service;

import com.encuesta.encuesta.entity.EncuestaEntity;
import com.encuesta.encuesta.entity.PreguntaEntity;
import com.encuesta.encuesta.model.sharedDto.EncuestaDTO;
import com.encuesta.encuesta.model.sharedDto.PreguntaDTO;
import com.encuesta.encuesta.model.sharedDto.RespuestaDTO;
import com.encuesta.encuesta.repository.EncuestaRepository;
import com.encuesta.encuesta.repository.PreguntaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class EncuestaService implements EncuestaServiceInterface{
    @Autowired
    EncuestaRepository encuestaRepository;

    @Autowired
    PreguntaRepository preguntaRepository;

    @Override
    public EncuestaDTO createEncuesta(EncuestaDTO encuestaDTO) {
        DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        encuestaDTO.setDate(dtf4.format(LocalDateTime.now()));
        EncuestaEntity encuestaEntity = new EncuestaEntity();
        encuestaEntity.setTitulo(encuestaDTO.getTitulo());
        EncuestaEntity createEncuestaObj =  encuestaRepository.save(encuestaEntity);
        BeanUtils.copyProperties(createEncuestaObj,encuestaDTO);
        return encuestaDTO;
    }

    @Override
    public PreguntaDTO createPregunta(PreguntaDTO dtoPregunta) {
        PreguntaEntity preguntaEntity = new PreguntaEntity();
        preguntaEntity.setTitulo_pregunta(dtoPregunta.getTitulo_pregunta());
        Optional<EncuestaEntity> findEncuestaObj = encuestaRepository.findById(dtoPregunta.getFk_id_encu());
        preguntaEntity.setEncuesta(findEncuestaObj.get());
        if (findEncuestaObj.isPresent()){
            PreguntaEntity createPreguntaObj = preguntaRepository.save(preguntaEntity);
            BeanUtils.copyProperties(createPreguntaObj,dtoPregunta);
        }else{
            System.out.printf("No city found with id %d%n");
        }
        return dtoPregunta;
    }

    @Override
    public RespuestaDTO createPregunta(RespuestaDTO dtoRespuesta) {
        return dtoRespuesta;
    }
}
