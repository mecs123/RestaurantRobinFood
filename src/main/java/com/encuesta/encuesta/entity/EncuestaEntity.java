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

    private static final long serialVersionUID = -8620913105504542892L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEncu", nullable = false)
    private Long id_encu;
    private String titulo;
    private String date;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private List<PreguntaEntity> preguntas;
}