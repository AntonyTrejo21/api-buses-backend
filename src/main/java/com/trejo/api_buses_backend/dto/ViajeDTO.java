package com.trejo.api_buses_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViajeDTO {
    private int idViaje;
    private Long idUsuario;
    private int numeroAsiento;
}
