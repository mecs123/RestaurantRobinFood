package com.encuesta.encuesta.model.sharedDto;

import com.encuesta.encuesta.entity.PreguntaEntity;
import com.encuesta.encuesta.entity.RespuestaEntity;
import com.encuesta.encuesta.model.response.PreguntaResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PreguntaDTO extends PreguntaResponse implements Serializable {

    private static final long serialVersionUID = -6261690654201102815L;
    private Long idPregunta;
    private String titulo_pregunta;
    private String tipoPregunta;

    private Long idEncuesta;
    private String nombreEncuesta;
    
    private RespuestaDTO respuestaDTO;
    private List<RespuestaEntity> respuestaList;
}
