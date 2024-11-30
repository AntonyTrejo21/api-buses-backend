package com.trejo.api_buses_backend.servicio;

import com.trejo.api_buses_backend.models.Pasaje;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPasajeServices {
    Page<Pasaje> GetAllPasajes(Pageable pageable);
}
