package com.acessocampus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acessocampus.entities.RegistroPonto;

public interface RegistroPontoRepository extends JpaRepository<RegistroPonto, Long> {
    
}
