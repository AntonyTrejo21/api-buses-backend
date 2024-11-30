package com.trejo.api_buses_backend.repository;

import com.trejo.api_buses_backend.models.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICiudadRepository extends JpaRepository<Ciudad,Integer> {
}
