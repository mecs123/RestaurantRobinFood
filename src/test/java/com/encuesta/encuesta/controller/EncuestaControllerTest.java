package com.encuesta.encuesta.controller;

import com.encuesta.encuesta.model.request.EncuestaRequestModel;
import com.encuesta.encuesta.model.request.PreguntaRequestModel;
import com.encuesta.encuesta.model.request.RespuestaRequestModel;
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
    private EncuestaPreguntaDTO returnEncuPregunta;
    private PreguntaDTO returnPreguntaDto;

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
    void createPregunta() throws Exception {
        returnPreguntaDto = generatePreguntaDto();
        Mockito.when(encuestaServiceInterface.createPregunta(returnPreguntaDto)).thenReturn(returnPreguntaDto);
        mockMvc.perform( MockMvcRequestBuilders
                .post("/encuesta/CreatePregunta")
                .content(asJsonString(new PreguntaRequestModel(1l,"mul","asd")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void createRespuesta() throws Exception {
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        respuestaDTO.setIdPregunta(1l);
        respuestaDTO.setDesRespuesta("Prueba");
        Mockito.when(encuestaServiceInterface.createRespuesta(respuestaDTO)).thenReturn(respuestaDTO);
        mockMvc.perform( MockMvcRequestBuilders
                .post("/encuesta/respuesta")
                .content(asJsonString(new RespuestaRequestModel(1l,"Prueba")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void listarEncuesta() throws Exception {
        returnEncuPregunta = generateEncuestPregunta();
        Mockito.when(encuestaServiceInterface.getEcuestaPregunta(1L)).thenReturn(returnEncuPregunta);
        mockMvc.perform( MockMvcRequestBuilders
                .get("/encuesta//listar/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private PreguntaDTO generatePreguntaDto() {
        PreguntaDTO preguntaDTO = new PreguntaDTO();
        preguntaDTO.setIdPregunta(1l);
        preguntaDTO.setTipoPregunta("m");
        preguntaDTO.setNombreEncuesta("asd");
        preguntaDTO.setIdEncuesta(1l);
        preguntaDTO.setRespuestaDTO(new RespuestaDTO(1l,"as"));
        return preguntaDTO;
    }

    private EncuestaPreguntaDTO generateEncuestPregunta() {
        EncuestaPreguntaDTO encuestaPreguntaDTO = new EncuestaPreguntaDTO();
        encuestaPreguntaDTO.setId_encu(1l);
        encuestaPreguntaDTO.setDate("123232");
        encuestaPreguntaDTO.setTitulo("as");
        return encuestaPreguntaDTO;
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