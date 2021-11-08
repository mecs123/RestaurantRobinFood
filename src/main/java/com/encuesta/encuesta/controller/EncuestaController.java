package com.encuesta.encuesta.controller;

import com.encuesta.encuesta.model.request.EncuestaRequestModel;
import com.encuesta.encuesta.model.request.PreguntaRequestModel;
import com.encuesta.encuesta.model.request.RespuestaRequestModel;
import com.encuesta.encuesta.model.response.EncuestaResponse;
import com.encuesta.encuesta.model.response.PreguntaResponse;
import com.encuesta.encuesta.model.response.RespuestaResponse;
import com.encuesta.encuesta.model.sharedDto.EncuestaDTO;
import com.encuesta.encuesta.model.sharedDto.PreguntaDTO;
import com.encuesta.encuesta.model.sharedDto.RespuestaDTO;
import com.encuesta.encuesta.service.EncuestaServiceInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/encuesta")
public class EncuestaController {

    @Autowired
    EncuestaServiceInterface encuestaServiceInterface;
    @PostMapping
    public EncuestaResponse createEncuesta(@RequestBody EncuestaRequestModel encuestaRequestModel){
        EncuestaResponse encuestaResponse = new EncuestaResponse();
        EncuestaDTO dtoEncuesta = new EncuestaDTO();
        BeanUtils.copyProperties(encuestaRequestModel,dtoEncuesta);
        EncuestaDTO encuestaDTO = encuestaServiceInterface.createEncuesta(dtoEncuesta);
        BeanUtils.copyProperties(encuestaDTO, encuestaResponse);
        return encuestaResponse;
    }

    @PostMapping("/pregunta")
    public PreguntaResponse createPregunta(@RequestBody PreguntaRequestModel preguntaRequestModel){
        PreguntaResponse preguntaResponse = new PreguntaResponse();
        PreguntaDTO dtoPregunta = new PreguntaDTO();
        BeanUtils.copyProperties(preguntaRequestModel,dtoPregunta);
        PreguntaDTO preguntaDTO = encuestaServiceInterface.createPregunta(dtoPregunta);
        BeanUtils.copyProperties(preguntaDTO, preguntaResponse);
        return preguntaResponse;
    }
    @PostMapping("/respuesta")
    public RespuestaResponse createRespuesta(@RequestBody RespuestaRequestModel respuestaRequestModel){
        RespuestaResponse respuestaResponse = new RespuestaResponse();
        RespuestaDTO dtoRespuesta = new RespuestaDTO();
        BeanUtils.copyProperties(respuestaRequestModel,dtoRespuesta);
        RespuestaDTO respuestaDTO = encuestaServiceInterface.createPregunta(dtoRespuesta);
        BeanUtils.copyProperties(respuestaDTO, respuestaResponse);
        return respuestaResponse;
    }
}
