package com.encuesta.encuesta.Excepciones;

import com.encuesta.encuesta.model.response.EncuestaRest;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;



@ControllerAdvice
public class ExepcionHandler {

    @Autowired
    ModelMapper modelMapper;

    @ExceptionHandler(value = {ExceptionBuilder.class})
    public ResponseEntity<ErrorMessage> handleRequestException(ExceptionBuilder ex, HttpServletRequest request){
        ErrorResponse response =new ErrorResponse();
        MessageResponse messageResponse = new MessageResponse();
        response.setCodigo("400");
        messageResponse.setMesssage("400");
        messageResponse.setErroMesssage(ex.getMessage());
        return new  ResponseEntity<ErrorMessage>((MultiValueMap<String, String>) response, HttpStatus.BAD_REQUEST);
    }

}
