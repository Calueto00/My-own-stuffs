package com.paulo.evento.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paulo.evento.model.Convidado;
import com.paulo.evento.model.Evento;

public interface ConviadoRepository extends JpaRepository<Convidado, Long>{
    List<Convidado> findByEvento(Evento evento);
}
