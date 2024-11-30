package com.trejo.api_buses_backend.servicio;

import com.trejo.api_buses_backend.dto.ViajeDTO;
import com.trejo.api_buses_backend.models.Viaje;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface IViajeServices {
    Page<Viaje> GetAllViajes(Pageable pageable);
    Viaje FindViajeById(int id);
    ResponseEntity<String> reservarViaje(ViajeDTO viajeDTO, HttpServletRequest request); // Agregado el parámetro HttpServletRequest
}
