package com.encuesta.encuesta.Excepciones;

public class MessageResponse {
    private String codigo;
    private String messsage;
    private String erroMesssage;

    public String getErroMesssage() {
        return erroMesssage;
    }

    public void setErroMesssage(String erroMesssage) {
        this.erroMesssage = erroMesssage;
    }

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

    public MessageResponse(String codigo, String messsage, String erroMesssage) {
        this.codigo = codigo;
        this.messsage = messsage;
        this.erroMesssage = erroMesssage;
    }

    public MessageResponse() {
    }
}
