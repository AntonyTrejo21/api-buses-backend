package com.trejo.api_buses_backend.servicio.Impl;

import com.trejo.api_buses_backend.dto.ViajeDTO;
import com.trejo.api_buses_backend.models.Viaje;
import com.trejo.api_buses_backend.repository.IViajeRepository;
import com.trejo.api_buses_backend.servicio.IViajeServices;
import com.trejo.api_buses_backend.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ViajeServices implements IViajeServices {
    private final IViajeRepository _viajeRepository;

    @Autowired
    public ViajeServices(IViajeRepository viajeRepository) {
        _viajeRepository = viajeRepository;
    }

    @Override
    public Page<Viaje> GetAllViajes(Pageable pageable) {
        return _viajeRepository.findAll(pageable);
    }

    @Override
    public Viaje FindViajeById(int id) {
        return _viajeRepository.findById(id).orElseGet(Viaje::new);
    }

    @Override
    @Transactional
    public ResponseEntity<String> reservarViaje(ViajeDTO viajeDTO, HttpServletRequest request) {
        try {
            _viajeRepository.reservarViaje(
                    viajeDTO.getIdViaje(),
                    viajeDTO.getIdUsuario(),
                    viajeDTO.getNumeroAsiento()
            );
            return ResponseEntity.ok("Viaje reservado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al reservar el viaje: " + e.getMessage());
        }
    }

}
