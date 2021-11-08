package com.encuesta.encuesta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "respuesta")
public class RespuestaEntity implements Serializable {
    private static final long serialVersionUID = 9085784276634477339L;
    @Column(name = "IdRes", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idResp;
    private String desResp;

}
