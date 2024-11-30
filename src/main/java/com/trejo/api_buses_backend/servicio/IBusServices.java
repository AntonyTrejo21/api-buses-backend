package com.trejo.api_buses_backend.servicio;

import com.trejo.api_buses_backend.models.Bus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBusServices {
    Page<Bus> GetAllBuses(Pageable pageable);
    Bus FindBusById(int id);
}
