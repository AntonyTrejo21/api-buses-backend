package com.trejo.api_buses_backend.models.pagination;

import com.trejo.api_buses_backend.models.Viaje;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ViajePageResponse {
    private List<Viaje> viajes;
    private int currentPage;
    private int totalPages;
    private long totalItems;
}
