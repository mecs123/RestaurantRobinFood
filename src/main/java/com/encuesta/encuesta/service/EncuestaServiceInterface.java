package com.encuesta.encuesta.service;

import com.encuesta.encuesta.Excepciones.ExceptionBuilder;
import com.encuesta.encuesta.entity.PreguntaEntity;
import com.encuesta.encuesta.model.response.EncuestaResponse;
import com.encuesta.encuesta.model.sharedDto.EncuestaDTO;
import com.encuesta.encuesta.model.sharedDto.EncuestaPreguntaDTO;
import com.encuesta.encuesta.model.sharedDto.PreguntaDTO;
import com.encuesta.encuesta.model.sharedDto.RespuestaDTO;

public interface EncuestaServiceInterface {
    EncuestaDTO createEncuesta(EncuestaDTO encuestaDTO) throws ExceptionBuilder;

    PreguntaDTO createPregunta(PreguntaDTO dtoPregunta) throws ExceptionBuilder, ExceptionBuilder;

    RespuestaDTO createRespuesta(RespuestaDTO dtoRespuesta) throws ExceptionBuilder;

    EncuestaPreguntaDTO getEcuestaPregunta(Long id) throws Exception;

    PreguntaEntity getPreguntaId(Long idPregunta);
}
