package com.acessocampus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acessocampus.entities.Portal;

public interface PortalRepository extends JpaRepository<Portal, Long> {
    
}
