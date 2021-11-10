package com.encuesta.encuesta.controller;

import com.encuesta.encuesta.Excepciones.ExceptionBuilder;
import com.encuesta.encuesta.entity.PreguntaEntity;
import com.encuesta.encuesta.model.request.EncuestaRequestModel;
import com.encuesta.encuesta.model.request.PreguntaRequestModel;
import com.encuesta.encuesta.model.sharedDto.EncuestaDTO;
import com.encuesta.encuesta.model.sharedDto.EncuestaPreguntaDTO;
import com.encuesta.encuesta.model.sharedDto.PreguntaDTO;
import com.encuesta.encuesta.model.sharedDto.RespuestaDTO;
import com.encuesta.encuesta.service.EncuestaServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EncuestaController.class)
class EncuestaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EncuestaServiceInterface encuestaServiceInterface;

    private EncuestaDTO returnEncuesta;
    private EncuestaDTO paramEncuestaDto;
    private EncuestaPreguntaDTO returnPregunta;

    @Test
    public void createEncuesta() throws Exception {
        returnEncuesta = generateEncuesta();
        paramEncuestaDto = generateEncuesta();
        Mockito.when(encuestaServiceInterface.createEncuesta(paramEncuestaDto)).thenReturn(returnEncuesta);
        mockMvc.perform( MockMvcRequestBuilders
                .post("/encuesta")
                .content(asJsonString(new EncuestaRequestModel(1,"Titulo")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void listarEncuesta() throws Exception {
        returnPregunta = generatePregunta();
        Mockito.when(encuestaServiceInterface.getEcuestaPregunta(1L)).thenReturn(returnPregunta);
        mockMvc.perform( MockMvcRequestBuilders
                .get("/encuesta//listar/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private EncuestaPreguntaDTO generatePregunta() {
        EncuestaPreguntaDTO encuestaPreguntaDTO = new EncuestaPreguntaDTO();
        encuestaPreguntaDTO.setId_encu(1l);
        encuestaPreguntaDTO.setDate("123232");
        encuestaPreguntaDTO.setTitulo("as");
        return encuestaPreguntaDTO;
    }

    @Test
    void createPregunta() throws Exception {
        PreguntaDTO preguntaDTO = new PreguntaDTO();
        preguntaDTO.setIdPregunta(1l);
        preguntaDTO.setTipoPregunta("m");
        preguntaDTO.setNombreEncuesta("asd");
        preguntaDTO.setIdEncuesta(1l);
        preguntaDTO.setRespuestaDTO(new RespuestaDTO(1l,"as"));

        Mockito.when(encuestaServiceInterface.createPregunta(preguntaDTO)).thenReturn(preguntaDTO);
        mockMvc.perform( MockMvcRequestBuilders
                .post("/encuesta/CreatePregunta")
                .content(asJsonString(new PreguntaRequestModel(1l,"mul","asd")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createRespuesta() {
    }

    private EncuestaDTO generateEncuesta() {
        EncuestaDTO returnEncuestaDTO = new EncuestaDTO();
        returnEncuestaDTO.setId_encu(1l);
        returnEncuestaDTO.setDate("2020/10/10");
        returnEncuestaDTO.setTitulo("Titulo");
        return returnEncuestaDTO;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}