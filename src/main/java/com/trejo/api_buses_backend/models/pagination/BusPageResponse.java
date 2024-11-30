package com.trejo.api_buses_backend.models.pagination;

import com.trejo.api_buses_backend.models.Bus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BusPageResponse {
    private List<Bus> buses;
    private int currentPage;
    private int totalPages;
    private long totalItems;
}
