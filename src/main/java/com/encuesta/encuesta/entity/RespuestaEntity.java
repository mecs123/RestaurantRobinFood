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

    private static final long serialVersionUID = 391454452424304291L;
    @Column(name = "IdRes", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idResp;
    @Column(name = "desResp", nullable = false)
    private String desResp;
    @Column(name = "upTipoPreg", nullable = false)
    private boolean upTipoPreg;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "fk_IdPre")
    private PreguntaEntity pregunta;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "fk_tipoPregunta")
    private EncuestaEntity encuesta;



}
