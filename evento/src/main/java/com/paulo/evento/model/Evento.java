package com.paulo.evento.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "O campo não pode ser nulo")
    @Size(min = 4, message = "O nome deve ter 4 ou mais caracteres")
    private String nome;

    @NotEmpty(message = "O campo não pode ser nulo")
    @Size(min = 4, message = "O local deve ter 4 ou mais caracteres")
    private String local;

    @NotNull(message = "ensira uma data")
    private String data;

    @NotNull(message = "ensira uma hora")
    private String horario;

    @OneToMany
    @JoinColumn(name = "eventoConvidado", referencedColumnName = "id")
    private List<Convidado> convidado;
}
    