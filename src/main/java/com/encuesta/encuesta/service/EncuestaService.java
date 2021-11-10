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
        PreguntaEntity preguntaEntity = new PreguntaEntity();
        preguntaEntity.setTitulo_pregunta(dtoPregunta.getTitulo_pregunta());
        preguntaEntity.setTipoPregunta(dtoPregunta.getTipoPregunta());
        try {
            preguntaEntity.setEncuesta(encuestaRepository.getEncuestaId(dtoPregunta.getIdEncuesta()));
            Optional<EncuestaEntity> findEncuestaObj = encuestaRepository.findById(dtoPregunta.getIdEncuesta());

            if (findEncuestaObj.isPresent()){
                //Guarda pregunta
                preguntaRepository.save(preguntaEntity);
                //Se ubica en la pregunta creada
                PreguntaEntity preguntaAll = preguntaRepository.findByIdPregunta(preguntaEntity.getIdPregunta());
                //Obtiene datos de la pregunta asociada a la encuesta
                dtoPregunta.setIdPregunta(preguntaAll.getIdPregunta());
                dtoPregunta.setIdEncuesta(findEncuestaObj.get().getId_encu());
                dtoPregunta.setTipoPregunta(preguntaAll.getTipoPregunta());
                dtoPregunta.setNombreEncuesta(findEncuestaObj.get().getTitulo());

            }else{
                System.out.printf("No city found with id %d%n");
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
        try {
            PreguntaEntity byIdPregunta = preguntaRepository.findByIdPregunta(dtoRespuesta.getIdPregunta());
            if ( byIdPregunta != null){
                try {
                    RespuestaEntity respuestaEntity = new RespuestaEntity();
                    respuestaEntity.setDesResp(dtoRespuesta.getDesRespuesta());
                    respuestaEntity.setPregunta(byIdPregunta);
                    RespuestaEntity createRespuestObj = respuestaRepository.save(respuestaEntity);
                    BeanUtils.copyProperties(createRespuestObj,dtoRespuesta);
                }catch (Exception e){
                    throw new ExceptionBuilder("Error para crear pregunta",e.getMessage());
                }

            }

        }catch (Exception e){
            throw new ExceptionBuilder("Error para crear respuesta",e.getMessage());
        }
        return dtoRespuesta;
    }

    @Override
    public EncuestaPreguntaDTO getEcuestaPregunta(Long id) throws ExceptionBuilder {
        try {
            EncuestaEntity encuestaId =encuestaRepository.getEncuestaId(id);
            if (encuestaId != null){
                EncuestaPreguntaDTO encuestaDTO = new EncuestaPreguntaDTO();
                Optional<EncuestaEntity> findEncuestaObj = encuestaRepository.findById(id);
                encuestaDTO.setTitulo(findEncuestaObj.get().getTitulo());
                encuestaDTO.setId_encu(findEncuestaObj.get().getId_encu());
                encuestaDTO.setDate(findEncuestaObj.get().getDate());
                encuestaDTO.setId_encu(findEncuestaObj.get().getId_encu());
                encuestaDTO.setPreguntas(preguntaRepository.getPreguntaId(id));
                return encuestaDTO;
            }else {
                throw new ExceptionBuilder("Not Found","Verifique id ingresado");
            }

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