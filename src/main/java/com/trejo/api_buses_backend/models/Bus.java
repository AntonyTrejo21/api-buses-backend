package com.trejo.api_buses_backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "buses")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private int numero;
    private String placa;
    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fecha_creacion = LocalDateTime.now();
    private String caracteristicas;
    private Integer capacidad;
    @ManyToOne
    @JoinColumn(name="marca_id")
    private Marca marca;
    private boolean activo;
}
