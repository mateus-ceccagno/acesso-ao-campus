package com.acessocampus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acessocampus.entities.NivelAcesso;

public interface NivelAcessoRepository extends JpaRepository<NivelAcesso, Long> {
    
}
