package com.trejo.api_buses_backend.servicio.Impl;


import com.trejo.api_buses_backend.models.Pasaje;
import com.trejo.api_buses_backend.repository.IPasajeRepository;
import com.trejo.api_buses_backend.servicio.IPasajeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PasajeServices implements IPasajeServices {
    IPasajeRepository _pasajeRepository;

    @Autowired
    public PasajeServices(IPasajeRepository pasajeRepository) {
        _pasajeRepository =pasajeRepository;
    }

    @Override
    public Page<Pasaje> GetAllPasajes(Pageable pageable) {
        return _pasajeRepository.findAll(pageable);
    }
}
