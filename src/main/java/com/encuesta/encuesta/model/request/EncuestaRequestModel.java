package com.encuesta.encuesta.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EncuestaRequestModel implements Serializable {
    private static final long serialVersionUID = 3855293466684771420L;
    private int id_encu;
    private String titulo;

}
