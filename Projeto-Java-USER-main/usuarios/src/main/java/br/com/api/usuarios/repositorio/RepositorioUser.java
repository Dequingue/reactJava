package br.com.api.usuarios.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.api.usuarios.modelo.ModeloUser;


@Repository
public interface RepositorioUser extends CrudRepository <ModeloUser , Long> {
    boolean existsByEmail(String email);
    
} 
