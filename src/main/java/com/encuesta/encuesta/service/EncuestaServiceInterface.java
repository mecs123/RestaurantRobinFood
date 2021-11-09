package com.encuesta.encuesta.service;

import com.encuesta.encuesta.entity.PreguntaEntity;
import com.encuesta.encuesta.model.sharedDto.EncuestaDTO;
import com.encuesta.encuesta.model.sharedDto.PreguntaDTO;
import com.encuesta.encuesta.model.sharedDto.RespuestaDTO;

public interface EncuestaServiceInterface {
    EncuestaDTO createEncuesta(EncuestaDTO encuestaDTO);

    PreguntaDTO createPregunta(PreguntaDTO dtoPregunta);

    RespuestaDTO createRespuesta(RespuestaDTO dtoRespuesta);

    EncuestaDTO getEcuestaPregunta(Long id) throws Exception;

    PreguntaEntity getPreguntaId(Long idPregunta);
}
