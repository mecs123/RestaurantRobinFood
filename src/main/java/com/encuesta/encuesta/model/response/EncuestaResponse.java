package com.encuesta.encuesta.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EncuestaResponse implements Serializable {

    private static final long serialVersionUID = -6261690654201102815L;
    private String titulo;
    private String date;
}
