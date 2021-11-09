package com.encuesta.encuesta.model.response;

import com.encuesta.encuesta.entity.PreguntaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EncuestaRest {
    private Long id_encu;
    private String tituloEncuesta;
    private String date;
    private List<PreguntaRest> preguntasList;
}
