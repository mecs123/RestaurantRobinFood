package com.encuesta.encuesta.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PreguntaRequestModel implements Serializable {
    private static final long serialVersionUID = 3855293466684771420L;
    private Long idEncuesta;
    private String tipoPregunta;
    private String titulo_pregunta;

}
