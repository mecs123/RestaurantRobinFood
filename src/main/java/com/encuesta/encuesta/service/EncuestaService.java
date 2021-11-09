package com.encuesta.encuesta.service;

import com.encuesta.encuesta.entity.EncuestaEntity;
import com.encuesta.encuesta.entity.PreguntaEntity;
import com.encuesta.encuesta.entity.RespuestaEntity;
import com.encuesta.encuesta.model.sharedDto.EncuestaDTO;
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
    public EncuestaDTO createEncuesta(EncuestaDTO encuestaDTO) {
        DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        EncuestaEntity encuestaEntity = new EncuestaEntity();
        encuestaEntity.setTitulo(encuestaDTO.getTitulo());
        encuestaEntity.setDate(dtf4.format(LocalDateTime.now()));
        EncuestaEntity createEncuestaObj =  encuestaRepository.save(encuestaEntity);
        BeanUtils.copyProperties(createEncuestaObj,encuestaDTO);
        return encuestaDTO;
    }

    @Override
    public PreguntaDTO createPregunta(PreguntaDTO dtoPregunta) {
        PreguntaEntity preguntaEntity = new PreguntaEntity();
        preguntaEntity.setTitulo_pregunta(dtoPregunta.getTitulo_pregunta());
        preguntaEntity.setTipoPregunta(dtoPregunta.getTipoPregunta());
        preguntaEntity.setEncuesta(encuestaRepository.getEncuestaId(dtoPregunta.getIdEncuesta()));
        Optional<EncuestaEntity> findEncuestaObj = encuestaRepository.findById(dtoPregunta.getIdEncuesta());

        if (findEncuestaObj.isPresent()){
            //Guarda pregunta
            preguntaEntity.setTipoPreguntaUp(true);
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
    }

    @Override
    public RespuestaDTO createRespuesta(RespuestaDTO dtoRespuesta) {
        PreguntaEntity byIdPregunta = preguntaRepository.findByIdPregunta(dtoRespuesta.getIdPregunta());
        if ( byIdPregunta != null){
            if ((byIdPregunta.getTipoPregunta() == tipoPregunta && byIdPregunta.isTipoPreguntaUp()) != true){
                creaPreguntaMultiple(byIdPregunta,dtoRespuesta);
            }else{
                if (byIdPregunta.isTipoPreguntaUp()==false){
                    creaPregunNoMultiple(byIdPregunta,dtoRespuesta);
                }
              dtoRespuesta.setDesRespuesta(null);
            }
       }
        return dtoRespuesta;
    }

    private void creaPreguntaMultiple(PreguntaEntity byIdPregunta, RespuestaDTO dtoRespuesta) {
            RespuestaEntity respuestaEntity = new RespuestaEntity();
            respuestaEntity.setDesResp(dtoRespuesta.getDesRespuesta());
            respuestaEntity.setPregunta(byIdPregunta);
            respuestaEntity.setUpTipoPreg(false);
            byIdPregunta.setTipoPreguntaUp(false);
            RespuestaEntity createRespuestObj = respuestaRepository.save(respuestaEntity);
            BeanUtils.copyProperties(createRespuestObj,dtoRespuesta);

    }

    private void creaPregunNoMultiple(PreguntaEntity byIdPregunta, RespuestaDTO dtoRespuesta) {
            RespuestaEntity respuestaEntity = new RespuestaEntity();
            respuestaEntity.setDesResp(dtoRespuesta.getDesRespuesta());
            respuestaEntity.setPregunta(byIdPregunta);
            respuestaEntity.setUpTipoPreg(true);
            byIdPregunta.setTipoPreguntaUp(true);
            RespuestaEntity createRespuestObj = respuestaRepository.save(respuestaEntity);
            BeanUtils.copyProperties(createRespuestObj,dtoRespuesta);
    }

    @Override
    public EncuestaDTO getEcuestaPregunta(Long id){
        EncuestaDTO encuestaDTO = new EncuestaDTO();
        Optional<EncuestaEntity> findEncuestaObj = encuestaRepository.findById(id);
        encuestaDTO.setPreguntas(preguntaRepository.getPreguntaId(id));
        encuestaDTO.setTitulo(findEncuestaObj.get().getTitulo());
        encuestaDTO.setId_encu(findEncuestaObj.get().getId_encu());
        encuestaDTO.setDate(findEncuestaObj.get().getDate());
        encuestaDTO.setId_encu(findEncuestaObj.get().getId_encu());

        return encuestaDTO;
    }

    @Override
    public PreguntaEntity getPreguntaId(Long idPregunta) {
        return preguntaRepository.findByIdPregunta(idPregunta);
    }


}