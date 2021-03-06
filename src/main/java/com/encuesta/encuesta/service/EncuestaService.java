package com.encuesta.encuesta.service;

import com.encuesta.encuesta.Excepciones.ExceptionBuilder;
import com.encuesta.encuesta.entity.EncuestaEntity;
import com.encuesta.encuesta.entity.PreguntaEntity;
import com.encuesta.encuesta.entity.RespuestaEntity;
import com.encuesta.encuesta.model.sharedDto.EncuestaDTO;
import com.encuesta.encuesta.model.sharedDto.EncuestaPreguntaDTO;
import com.encuesta.encuesta.model.sharedDto.PreguntaDTO;
import com.encuesta.encuesta.model.sharedDto.RespuestaDTO;
import com.encuesta.encuesta.repository.EncuestaRepository;
import com.encuesta.encuesta.repository.PreguntaRepository;
import com.encuesta.encuesta.repository.RespuestaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class EncuestaService implements EncuestaServiceInterface{
    @Autowired
    EncuestaRepository encuestaRepository;
    @Autowired
    PreguntaRepository preguntaRepository;
    @Autowired
    RespuestaRepository respuestaRepository;
    @Autowired
    ModelMapper modelMapper;
    @Value("${showTipoPregunta}")
    private String tipoPregunta;


    @Override
    public EncuestaDTO createEncuesta(EncuestaDTO encuestaDTO) throws ExceptionBuilder {

        try {
            DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
            EncuestaEntity encuestaEntity = new EncuestaEntity();
            encuestaEntity.setTitulo(encuestaDTO.getTitulo());
            encuestaEntity.setDate(dtf4.format(LocalDateTime.now()));
            EncuestaEntity encuestaEntity1 = encuestaRepository.save(encuestaEntity);
            EncuestaDTO createEncuestaObj = modelMapper.map(encuestaEntity1,EncuestaDTO.class);
            return createEncuestaObj;
        } catch (IllegalArgumentException i){
            throw new ExceptionBuilder("No se creo ninguna encuesta",i.getMessage());
        } catch (Exception e){
            throw new ExceptionBuilder("No se creo ninguna encuesta", e.getMessage());
        }
    }

    @Override
    public PreguntaDTO createPregunta(PreguntaDTO dtoPregunta) throws ExceptionBuilder {
        // 1. Valida la encuesta
        // Si existe la encuesta guarda la encuesta asociando el id de la pregunta.

        ///Pasa del DTO al Entity
        PreguntaEntity preguntaEntity = new PreguntaEntity();
        preguntaEntity.setTitulo_pregunta(dtoPregunta.getTitulo_pregunta());
        preguntaEntity.setTipoPregunta(dtoPregunta.getTipoPregunta());
        try {
            Optional<EncuestaEntity> findEncuestaObj = encuestaRepository.findById(dtoPregunta.getIdEncuesta());

            if (findEncuestaObj.isPresent()){
                // Se guarda encuesta asociada a la pregunta
                preguntaEntity.setEncuesta(encuestaRepository.getEncuestaId(dtoPregunta.getIdEncuesta()));
                //Guarda pregunta
                preguntaRepository.save(preguntaEntity);
                //Se ubica en la pregunta creada
                PreguntaEntity preguntaAll = preguntaRepository.findByIdPregunta(preguntaEntity.getIdPregunta());
                //Retorna al DTO
                //Obtiene datos de la pregunta asociada a la encuesta
                modelMapper.map(preguntaEntity, PreguntaDTO.class);
            }else{
                System.out.printf("No existe encuesta");
            }
            return dtoPregunta;
        } catch (IllegalArgumentException e) {
            throw new ExceptionBuilder("Error para crear pregunta", e.getMessage());
        } catch (Exception e){
            throw new ExceptionBuilder("Error para crear pregunta",e.getMessage());
        }
    }

    @Override
    public RespuestaDTO createRespuesta(RespuestaDTO dtoRespuesta) throws ExceptionBuilder {
        //Valida la encuesta y la pregunta para asignar respuesta
        try {
            Optional<EncuestaEntity> byIdEncuesta = encuestaRepository.findById(dtoRespuesta.getIdEncuesta());
            if (byIdEncuesta.isPresent()){
                PreguntaEntity byIdPregunta = preguntaRepository.findByIdPregunta(dtoRespuesta.getIdPregunta());
                if ( byIdPregunta != null){
                    try {
                        RespuestaEntity respuestaEntity = new RespuestaEntity();
                        respuestaEntity.setDesResp(dtoRespuesta.getDesRespuesta());
                        respuestaEntity.setPregunta(byIdPregunta);
                        respuestaEntity.setEncuesta(byIdEncuesta.get());
                        RespuestaEntity createRespectObj = respuestaRepository.save(respuestaEntity);
                        modelMapper.map(createRespectObj,RespuestaDTO.class);
                    }catch (Exception e){
                        throw new ExceptionBuilder("Error para crear respuesta",e.getMessage());
                    }
                }else{
                    throw new ExceptionBuilder("Error para crear pregunta","Error en pregunta");
                }
            }else{
                throw new ExceptionBuilder("Error para crear pregunta","Error en encuesta");
            }
        }catch (Exception e){
            throw new ExceptionBuilder("No se puede asignar respuesta",e.getMessage());
        }
        return dtoRespuesta;
    }

    @Override
    public EncuestaPreguntaDTO getEcuestaPregunta(Long id) throws ExceptionBuilder {
        try {
            EncuestaPreguntaDTO encuestaPreguntaDTO = new EncuestaPreguntaDTO();
                Optional<EncuestaEntity> findEncuestaObj = encuestaRepository.findById(id);
                if (findEncuestaObj.isPresent()){
                    encuestaPreguntaDTO.setTitulo(findEncuestaObj.get().getTitulo());
                    encuestaPreguntaDTO.setId_encu(findEncuestaObj.get().getId_encu());
                    encuestaPreguntaDTO.setDate(findEncuestaObj.get().getDate());
                    encuestaPreguntaDTO.setId_encu(findEncuestaObj.get().getId_encu());
                    encuestaPreguntaDTO.setPreguntas(preguntaRepository.getPreguntaId(id));
                }
            return encuestaPreguntaDTO;
        } catch (HttpServerErrorException h){
            throw new ExceptionBuilder("500", h.getMessage());
        } catch (IllegalArgumentException i){
            throw new ExceptionBuilder("Error para listar la encuesta",i.getMessage());
        } catch (Exception e){
            throw new ExceptionBuilder("Error para listar la encuesta",e.getMessage());
        }

    }

    @Override
    public PreguntaEntity getPreguntaId(Long idPregunta) {
        return preguntaRepository.findByIdPregunta(idPregunta);
    }


}