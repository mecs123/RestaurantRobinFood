package com.encuesta.encuesta.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RespuestaRequestModel implements Serializable {
    private static final long serialVersionUID = 3855293466684771420L;
    private Long idEncuesta;
    private Long idPregunta;
    private String desRespuesta;

    public RespuestaRequestModel(long l, String prueba) {

    }
}
