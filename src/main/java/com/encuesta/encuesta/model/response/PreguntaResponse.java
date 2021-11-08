package com.encuesta.encuesta.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PreguntaResponse implements Serializable {

    private static final long serialVersionUID = -6261690654201102815L;
    private int id_pre;
    private String titulo_pregunta;
    private Long fk_id_encu;
}
