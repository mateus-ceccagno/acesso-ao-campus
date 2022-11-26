package com.acessocampus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acessocampus.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    
}
