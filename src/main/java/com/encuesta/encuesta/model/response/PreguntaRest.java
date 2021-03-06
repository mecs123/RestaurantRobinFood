package com.encuesta.encuesta.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PreguntaRest implements Serializable {

    private static final long serialVersionUID = -6261690654201102815L;
    private String titulo_pregunta;
    private String tipoPregunta;
    private List<RespuestaRest> respuesta;


}
