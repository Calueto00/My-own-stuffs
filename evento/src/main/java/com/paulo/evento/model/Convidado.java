package com.paulo.evento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Convidado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull(message = "preencha o campo")
    @Size(min = 4, message = "O nome deve ter 4 ou mais caracteres")
    private String nome;
    @Email(message = "email incorreto")
    private String email;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Evento evento;
}
