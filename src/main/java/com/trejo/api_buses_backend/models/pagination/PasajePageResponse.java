package com.trejo.api_buses_backend.models.pagination;

import com.trejo.api_buses_backend.models.Pasaje;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PasajePageResponse {
    private List<Pasaje> pasajes;
    private int currentPage;
    private int totalPages;
    private long totalItems;
}
