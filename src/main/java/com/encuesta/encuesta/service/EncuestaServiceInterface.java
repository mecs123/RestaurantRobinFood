package com.encuesta.encuesta.service;

import com.encuesta.encuesta.model.sharedDto.EncuestaDTO;
import com.encuesta.encuesta.model.sharedDto.PreguntaDTO;
import com.encuesta.encuesta.model.sharedDto.RespuestaDTO;

public interface EncuestaServiceInterface {
    EncuestaDTO createEncuesta(EncuestaDTO encuestaDTO);

    PreguntaDTO createPregunta(PreguntaDTO dtoPregunta);

    RespuestaDTO createPregunta(RespuestaDTO dtoRespuesta);
}
