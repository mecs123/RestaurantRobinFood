package com.encuesta.encuesta.Excepciones;

public class ErrorResponse {
    private String codigo;
    private String messsage;

    public String getCodigo() {
        return codigo;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }

    public ErrorResponse(String codigo, String messsage) {
        this.codigo = codigo;
        this.messsage = messsage;
    }

    public ErrorResponse() {
    }
}
