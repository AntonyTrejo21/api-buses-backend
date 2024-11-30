package com.trejo.api_buses_backend.rest;

import com.trejo.api_buses_backend.models.Bus;
import com.trejo.api_buses_backend.models.pagination.BusPageResponse;
import com.trejo.api_buses_backend.servicio.IBusServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusController {
    IBusServices busServices;

    @Autowired
    public BusController(IBusServices busServices) {
        this.busServices = busServices;
    }

    @GetMapping("/buses")
    public BusPageResponse getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Bus> busPage = busServices.GetAllBuses(pageable);

        // Crear el DTO de respuesta
        BusPageResponse response = new BusPageResponse();
        response.setBuses(busPage.getContent());
        response.setCurrentPage(busPage.getNumber());
        response.setTotalPages(busPage.getTotalPages());
        response.setTotalItems(busPage.getTotalElements());

        return response;
    }

    @GetMapping("/bus/{id}")
    public Bus getBus(@PathVariable int id){
        return busServices.FindBusById(id);
    }
}
