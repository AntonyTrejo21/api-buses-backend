package com.trejo.api_buses_backend.repository;

import com.trejo.api_buses_backend.models.Pasaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPasajeRepository extends JpaRepository<Pasaje,Integer> {
}
