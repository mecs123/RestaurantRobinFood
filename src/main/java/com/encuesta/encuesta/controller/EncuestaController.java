package com.encuesta.encuesta.controller;

import com.encuesta.encuesta.entity.PreguntaEntity;
import com.encuesta.encuesta.model.request.EncuestaRequestModel;
import com.encuesta.encuesta.model.request.PreguntaRequestModel;
import com.encuesta.encuesta.model.request.RespuestaRequestModel;
import com.encuesta.encuesta.model.response.EncuestaResponse;
import com.encuesta.encuesta.model.response.EncuestaRest;
import com.encuesta.encuesta.model.response.PreguntaResponse;
import com.encuesta.encuesta.model.response.RespuestaResponse;
import com.encuesta.encuesta.model.sharedDto.EncuestaDTO;
import com.encuesta.encuesta.model.sharedDto.PreguntaDTO;
import com.encuesta.encuesta.model.sharedDto.RespuestaDTO;
import com.encuesta.encuesta.service.EncuestaServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/encuesta")
public class EncuestaController {

    @Autowired
    ModelMapper modelMapper;

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
    @GetMapping(path = "/listar/{id}")
    public EncuestaRest listarEncuesta(@PathVariable("id") Long id)  {
        try {
            EncuestaDTO encuestaDTO = encuestaServiceInterface.getEcuestaPregunta(id);
            EncuestaRest encuestaRest = modelMapper.map(encuestaDTO,EncuestaRest.class);
           return encuestaRest;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @PostMapping(path = "/CreatePregunta")
    public PreguntaResponse createPregunta(@RequestBody PreguntaRequestModel preguntaRequestModel) throws Exception {
        PreguntaResponse preguntaResponse = new PreguntaResponse();
        PreguntaDTO dtoPregunta = modelMapper.map(preguntaRequestModel,PreguntaDTO.class);
        //Crea pregunta
        PreguntaDTO preguntaDTO = encuestaServiceInterface.createPregunta(dtoPregunta);
        BeanUtils.copyProperties(preguntaDTO, preguntaResponse);
        return preguntaResponse;
    }
    @PostMapping("/respuesta")
    public String createRespuesta(@RequestBody RespuestaRequestModel respuestaRequestModel) throws Exception {
        RespuestaDTO dtoRespuesta = new RespuestaDTO();
        BeanUtils.copyProperties(respuestaRequestModel,dtoRespuesta);
        encuestaServiceInterface.createRespuesta(dtoRespuesta);
        if (dtoRespuesta.getDesRespuesta() !=null){
            PreguntaEntity preguntaDTO = encuestaServiceInterface.getPreguntaId(dtoRespuesta.getIdPregunta());
            return preguntaDTO.getTitulo_pregunta()+ "\nRTA: "+ dtoRespuesta.getDesRespuesta()+ "...:::Gracias:::....";
        }else{
            return "No es posible crear Nueva respuesta a la pregunta";
        }
    }


}
