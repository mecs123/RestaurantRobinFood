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
@Entity(name = "encuesta")
public class EncuestaEntity implements Serializable {
    private static final long serialVersionUID = -6190117583555398351L;
    @Column(name = "IdEncu", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_encu;
    private String titulo;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private List<PreguntaEntity> preguntas;
}