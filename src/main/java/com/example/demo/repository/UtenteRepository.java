package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.UtenteDTO;

@Repository
public interface UtenteRepository extends JpaRepository<UtenteDTO, Long> {
    UtenteDTO findByNumeroBI(String name);
}
