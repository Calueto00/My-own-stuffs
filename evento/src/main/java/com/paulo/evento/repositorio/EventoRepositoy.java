package com.paulo.evento.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paulo.evento.model.Evento;

public interface EventoRepositoy extends JpaRepository<Evento, Long> {
    
}
