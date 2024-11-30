package com.trejo.api_buses_backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name = "Viajes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idViaje;

    @ManyToOne
    @JoinColumn(name = "idBus", nullable = false)
    private Bus bus;

    @ManyToOne
    @JoinColumn(name = "origen", nullable = false)
    private Ciudad origen;

    @ManyToOne
    @JoinColumn(name = "destino", nullable = false)
    private Ciudad destino;

    @Column(nullable = false)
    private LocalDateTime fechaSalida;

    @Column(nullable = false)
    private LocalDateTime fechaLlegadaEstimada;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(nullable = false)
    private Integer asientosDisponibles;
}
