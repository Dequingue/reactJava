package br.com.api.usuarios.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import br.com.api.usuarios.modelo.ModeloResposta;
import br.com.api.usuarios.modelo.ModeloUser;
import br.com.api.usuarios.servico.ServicoUser;

@CrossOrigin(origins = "*") // ou restrito por domínio
@RestController
@RequestMapping("/usuarios")

public class ControleUser {

    @Autowired
    private ServicoUser su;

    // Teste de API
    @GetMapping("")
    public String rota(){
        return "api funcionando";
    }
    
    // Rota para listar
    @GetMapping("/listar")
    public Iterable <ModeloUser> listar(){
        return su.listar();
    }

    // Rota para cadastrar
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody ModeloUser mu){
        return su.cadastrar(mu);


    }


    // Rota para alterar

    @PutMapping("/alterar")
    public ResponseEntity<?> alterar(@RequestBody ModeloUser mu){
        return su.cadastrar(mu,"alterar");
    }

    // Rota para excluir

    @DeleteMapping("/remover/{codigo}")
    public ResponseEntity<ModeloResposta> remover(@PathVariable long codigo){
        return su.remover(codigo);

    }

}
