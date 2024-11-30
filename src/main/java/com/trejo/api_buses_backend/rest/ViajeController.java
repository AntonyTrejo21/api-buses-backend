package com.trejo.api_buses_backend.rest;

import com.trejo.api_buses_backend.dto.ViajeDTO;
import com.trejo.api_buses_backend.models.Viaje;
import com.trejo.api_buses_backend.models.pagination.ViajePageResponse;
import com.trejo.api_buses_backend.servicio.IViajeServices;
import com.trejo.api_buses_backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ViajeController {

    private final IViajeServices viajeServices;
    private final JwtUtil jwtUtil;

    @Autowired
    public ViajeController(IViajeServices viajeServices, JwtUtil jwtUtil) {
        this.viajeServices = viajeServices;
        this.jwtUtil = jwtUtil;
    }

    // Obtener todos los viajes
    @GetMapping("/viajes")
    public ViajePageResponse getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Viaje> viajePage = viajeServices.GetAllViajes(pageable);

        ViajePageResponse response = new ViajePageResponse();
        response.setViajes(viajePage.getContent());
        response.setCurrentPage(viajePage.getNumber());
        response.setTotalPages(viajePage.getTotalPages());
        response.setTotalItems(viajePage.getTotalElements());

        return response;
    }

    // Obtener un viaje por id
    @GetMapping("/viaje/{id}")
    public Viaje getViaje(@PathVariable int id) {
        return viajeServices.FindViajeById(id);
    }

    @PostMapping("/reservar")
    public ResponseEntity<String> reservarViaje(@RequestBody ViajeDTO viajeDTO, HttpServletRequest request) {
        try {
            // Extraer el token y el idUsuario
            String token = jwtUtil.extractTokenFromRequest(request);
            Long idUsuario = jwtUtil.extractClaims(token).get("idUsuario", Long.class);

            if (idUsuario == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("El idUsuario no está presente en el token.");
            }

            viajeDTO.setIdUsuario(idUsuario);
            return viajeServices.reservarViaje(viajeDTO, request);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la reserva: " + e.getMessage());
        }
    }

}
