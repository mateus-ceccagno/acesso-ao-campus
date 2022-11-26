package com.acessocampus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acessocampus.entities.Jornada;

public interface JornadaRepository extends JpaRepository<Jornada, Long> {
    
}
