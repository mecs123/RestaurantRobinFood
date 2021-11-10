package com.encuesta.encuesta.model.sharedDto;

import com.encuesta.encuesta.entity.PreguntaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EncuestaDTO {

    private Long id_encu;
    private String titulo;
    private String date;
}
