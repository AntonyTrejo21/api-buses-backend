package com.trejo.api_buses_backend.servicio.Impl;

import com.trejo.api_buses_backend.models.Bus;
import com.trejo.api_buses_backend.repository.IBusRepository;
import com.trejo.api_buses_backend.servicio.IBusServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class BusServices implements IBusServices {

    IBusRepository _busRepository;

    @Autowired
    public BusServices(IBusRepository busRepository) {
        _busRepository = busRepository;
    }

    @Override
    public Page<Bus> GetAllBuses(Pageable pageable) {
        return _busRepository.findAll(pageable);
    }

    @Override
    public Bus FindBusById(int id) {
        return _busRepository.findById(id).orElseGet(Bus::new);
    }
}
