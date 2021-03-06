package com.encuesta.encuesta.controller;

import com.encuesta.encuesta.Excepciones.ExceptionBuilder;
import com.encuesta.encuesta.model.request.EncuestaRequestModel;
import com.encuesta.encuesta.model.request.PreguntaRequestModel;
import com.encuesta.encuesta.model.request.RespuestaRequestModel;
import com.encuesta.encuesta.model.response.EncuestaRest;
import com.encuesta.encuesta.model.response.PreguntaResponse;
import com.encuesta.encuesta.model.sharedDto.EncuestaDTO;
import com.encuesta.encuesta.model.sharedDto.EncuestaPreguntaDTO;
import com.encuesta.encuesta.model.sharedDto.PreguntaDTO;
import com.encuesta.encuesta.model.sharedDto.RespuestaDTO;
import com.encuesta.encuesta.service.EncuestaServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/encuesta")
public class EncuestaController {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    EncuestaServiceInterface encuestaServiceInterface;


    @PostMapping
    public EncuestaDTO createEncuesta(@RequestBody EncuestaRequestModel encuestaRequestModel) throws ExceptionBuilder {
        EncuestaDTO dtoEncuesta = modelMapper.map(encuestaRequestModel,EncuestaDTO.class);
        return encuestaServiceInterface.createEncuesta(dtoEncuesta);
    }

    @PostMapping(path = "/CreatePregunta")
    public PreguntaResponse createPregunta(@RequestBody PreguntaRequestModel preguntaRequestModel) throws ExceptionBuilder {
        PreguntaDTO dtoPregunta = modelMapper.map(preguntaRequestModel,PreguntaDTO.class);
        return encuestaServiceInterface.createPregunta(dtoPregunta);
    }

    @PostMapping("/respuesta")
    public RespuestaDTO createRespuesta(@RequestBody RespuestaRequestModel respuestaRequestModel) throws ExceptionBuilder {
        RespuestaDTO dtoRespuesta = modelMapper.map(respuestaRequestModel,RespuestaDTO.class);
        return encuestaServiceInterface.createRespuesta(dtoRespuesta);
    }

    @GetMapping(path = "/listar/{id}")
    public EncuestaRest listarEncuesta(@PathVariable("id") Long id) throws Exception {
            EncuestaPreguntaDTO encuestaDTO = encuestaServiceInterface.getEcuestaPregunta(id);
            EncuestaRest encuestaRest = modelMapper.map(encuestaDTO,EncuestaRest.class);
           return encuestaRest;
    }

}
