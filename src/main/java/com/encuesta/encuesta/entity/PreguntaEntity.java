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
@Entity(name = "pregunta")
public class PreguntaEntity implements Serializable {
    private static final long serialVersionUID = 9085784276634477339L;
    @Column(name = "IdPre", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idPregunta;
    private String titulo_pregunta;
    private String tipoPregunta;
    private boolean tipoPreguntaUp;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "fk_IdEncu")
    private EncuestaEntity encuesta;

    @OneToMany(mappedBy = "pregunta")
    private List<RespuestaEntity> respuestaEntities;

}
