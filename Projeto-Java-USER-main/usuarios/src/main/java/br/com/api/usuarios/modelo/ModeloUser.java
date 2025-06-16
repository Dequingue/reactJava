package br.com.api.usuarios.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "usuarios")

public class ModeloUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long codigo;
    private String nome;
    private String email;
    private String telefone;

    
}
