package com.trejo.api_buses_backend.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Pasajes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pasaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPasaje;

    @ManyToOne
    @JoinColumn(name = "idViaje", nullable = false)
    private Viaje viaje;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private Integer numeroAsiento;

    @Column(nullable = false)
    private LocalDateTime fechaCompra = LocalDateTime.now();

    @Column(nullable = false, length = 20)
    private String estado = "Confirmado";
}