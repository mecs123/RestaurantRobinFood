package com.encuesta.encuesta.model.sharedDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EncuestaDTO {
    private int id_encu;
    private String titulo;
    private String date;
}
