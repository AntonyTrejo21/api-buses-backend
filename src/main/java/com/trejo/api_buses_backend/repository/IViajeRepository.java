package com.trejo.api_buses_backend.repository;

import com.trejo.api_buses_backend.models.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface IViajeRepository extends JpaRepository<Viaje, Integer> {
    @Procedure(name = "reservarViaje")
    void reservarViaje(int idViaje, Long idUsuario, int numeroAsiento);
}
