package com.encuesta.encuesta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "respuesta")
public class RespuestaEntity implements Serializable {

    private static final long serialVersionUID = -8558215397395405360L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdRes", nullable = false)
    private Long idResp;
    @Column(name = "desResp", nullable = false)
    private String desResp;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "fk_IdPre")
    private PreguntaEntity pregunta;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "fk_idEncuesta")
    private EncuestaEntity encuesta;



}
