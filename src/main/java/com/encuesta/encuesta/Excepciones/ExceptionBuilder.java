package com.encuesta.encuesta.Excepciones;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionBuilder extends Exception{
    private String code;
    private String message;
    private String errorMessage;

    public ExceptionBuilder(String code, String message) {
        this.code= code;
        this.message=message;

    }
}
