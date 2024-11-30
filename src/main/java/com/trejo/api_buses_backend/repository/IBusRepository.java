package com.trejo.api_buses_backend.repository;

import com.trejo.api_buses_backend.models.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBusRepository extends JpaRepository<Bus,Integer> {
}
