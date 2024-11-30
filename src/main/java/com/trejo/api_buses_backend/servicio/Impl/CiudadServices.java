package com.trejo.api_buses_backend.servicio.Impl;

import com.trejo.api_buses_backend.models.Ciudad;
import com.trejo.api_buses_backend.repository.ICiudadRepository;
import com.trejo.api_buses_backend.servicio.ICiudadServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiudadServices implements ICiudadServices {
    ICiudadRepository _ciudadRepository;

    @Autowired
    public CiudadServices(ICiudadRepository ciudadRepository){_ciudadRepository = ciudadRepository;}

    @Override
    public List<Ciudad> GetAllCiudades() {
        return _ciudadRepository.findAll();
    }
}
