package com.encuesta.encuesta.service;

import com.encuesta.encuesta.Excepciones.ExceptionBuilder;
import com.encuesta.encuesta.entity.EncuestaEntity;
import com.encuesta.encuesta.model.sharedDto.EncuestaDTO;
import com.encuesta.encuesta.repository.EncuestaRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(EncuestaService.class)
class EncuestaServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    EncuestaRepository encuestaRepository;

    @MockBean
    EncuestaService encuestaService;

    private EncuestaDTO encuestaDtoServic;

    @Test
    public void createEncuestaNull() throws ExceptionBuilder {
        encuestaDtoServic = generateEncuesta();
        EncuestaEntity encuestaEntity = new EncuestaEntity();
        encuestaEntity.setId_encu(null);
        encuestaEntity.setTitulo("prueba");
        encuestaEntity.setDate("202020");
        when(encuestaRepository.save(encuestaEntity)).thenReturn(null);
        EncuestaDTO encuestaDTO = encuestaService.createEncuesta(encuestaDtoServic);
        assertEquals(encuestaDTO,null);
    }


    private EncuestaDTO generateEncuesta() {
        EncuestaDTO returnEncuestaDTO = new EncuestaDTO();
        returnEncuestaDTO.setId_encu(1l);
        returnEncuestaDTO.setDate("2020/10/10");
        returnEncuestaDTO.setTitulo("Titulo");
        return returnEncuestaDTO;
    }

    @Test
    void createPregunta() {
    }

    @Test
    void createRespuesta() {
    }

    @Test
    void getEcuestaPregunta() {
    }

    @Test
    void getPreguntaId() {
    }
}