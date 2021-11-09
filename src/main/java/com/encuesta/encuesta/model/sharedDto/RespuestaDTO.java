package com.encuesta.encuesta.model.sharedDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RespuestaDTO implements Serializable {
    private static final long serialVersionUID = 3855293466684771420L;
    private Long idPregunta;
    private String desRespuesta;
}
