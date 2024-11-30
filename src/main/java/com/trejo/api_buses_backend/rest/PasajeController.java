package com.trejo.api_buses_backend.rest;

import com.trejo.api_buses_backend.models.Pasaje;
import com.trejo.api_buses_backend.models.pagination.PasajePageResponse;
import com.trejo.api_buses_backend.servicio.IPasajeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasajeController {
    IPasajeServices pasajeServices;

    @Autowired
    public PasajeController(IPasajeServices pasajeServices) {
        this.pasajeServices = pasajeServices;
    }

    @GetMapping("/pasajes")
    public PasajePageResponse getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Pasaje> pasajePage = pasajeServices.GetAllPasajes(pageable);

        // Crear el DTO de respuesta
        PasajePageResponse response = new PasajePageResponse();
        response.setPasajes(pasajePage.getContent());
        response.setCurrentPage(pasajePage.getNumber());
        response.setTotalPages(pasajePage.getTotalPages());
        response.setTotalItems(pasajePage.getTotalElements());

        return response;
    }
}
