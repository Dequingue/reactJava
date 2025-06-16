package br.com.api.usuarios.servico;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.usuarios.modelo.ModeloResposta;
import br.com.api.usuarios.modelo.ModeloUser;
import br.com.api.usuarios.repositorio.RepositorioUser;

@Service
public class ServicoUser {
    
    @Autowired
    private RepositorioUser ru;

    @Autowired
    private ModeloResposta mr;


    // Método Listar Usuários
    public Iterable<ModeloUser> listar (){
        return ru.findAll();
    }


    // -------------------------------------------------------------------------------
    // Método de Cadastrar Usuários

    public ResponseEntity<?> cadastrar(ModeloUser mu){

        // Regex para telefone com DDD
        String regexTelefone = "\\((1[1-9]|[2-9][0-9])\\) ?(?:9\\d{4}|\\d{4})-?\\d{4}";
        Pattern patternTelefone = Pattern.compile(regexTelefone);
    
        // Regex simples para email (básico, pode ser melhorado)
        String regexEmail = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        Pattern patternEmail = Pattern.compile(regexEmail);
    
        if (mu.getNome() == null || mu.getNome().trim().isEmpty()){
            mr.setMensagem("Informe o nome do Usuário");
            return new ResponseEntity<ModeloResposta>(mr, HttpStatus.BAD_REQUEST);
    
        } else if (mu.getEmail() == null || mu.getEmail().trim().isEmpty()){
            mr.setMensagem("Informe um email válido");
            return new ResponseEntity<ModeloResposta>(mr, HttpStatus.BAD_REQUEST);
    
        } else if (!patternEmail.matcher(mu.getEmail()).matches()){
            mr.setMensagem("Formato de email inválido");
            return new ResponseEntity<ModeloResposta>(mr, HttpStatus.BAD_REQUEST);
    
        } else if (ru.existsByEmail(mu.getEmail())) {  // <-- Verifica se email já existe no banco
            mr.setMensagem("Email já cadastrado");
            return new ResponseEntity<ModeloResposta>(mr, HttpStatus.BAD_REQUEST);
    
        } else if (mu.getTelefone() == null || mu.getTelefone().trim().isEmpty()){
            mr.setMensagem("Informe um telefone válido");
            return new ResponseEntity<ModeloResposta>(mr, HttpStatus.BAD_REQUEST);
    
        } else if (!patternTelefone.matcher(mu.getTelefone()).matches()){
            mr.setMensagem("Telefone Inválido. Use (XX) 9XXXX-XXXX");
            return new ResponseEntity<ModeloResposta>(mr, HttpStatus.BAD_REQUEST);
    
        } else {
            return new ResponseEntity<ModeloUser>(ru.save(mu), HttpStatus.CREATED);
        }
    }

    // ---------------------------------------------------------------------------

    // Método para Excluir 

    public ResponseEntity<ModeloResposta> remover(long codigo){
        ru.deleteById(codigo);

        mr.setMensagem("Usuário Removido com Sucesso");
        return new ResponseEntity<ModeloResposta>(mr, HttpStatus.OK);


    }

    // -----------------------------------------------------------------------------------

    // Método Alterar Usuários

    public ResponseEntity<?> cadastrar(ModeloUser mu, String acao){

        // Regex para telefone com DDD
        String regexTelefone = "\\((1[1-9]|[2-9][0-9])\\) ?(?:9\\d{4}|\\d{4})-?\\d{4}";
        Pattern patternTelefone = Pattern.compile(regexTelefone);
    
        // Regex simples para email (básico, pode ser melhorado)
        String regexEmail = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        Pattern patternEmail = Pattern.compile(regexEmail);
    
        if (mu.getNome() == null || mu.getNome().trim().isEmpty()){
            mr.setMensagem("Informe o nome do Usuário");
            return new ResponseEntity<ModeloResposta>(mr, HttpStatus.BAD_REQUEST);
    
        } else if (mu.getEmail() == null || mu.getEmail().trim().isEmpty()){
            mr.setMensagem("Informe um email válido");
            return new ResponseEntity<ModeloResposta>(mr, HttpStatus.BAD_REQUEST);
    
        } else if (!patternEmail.matcher(mu.getEmail()).matches()){
            mr.setMensagem("Formato de email inválido");
            return new ResponseEntity<ModeloResposta>(mr, HttpStatus.BAD_REQUEST);
    
        } else if (ru.existsByEmail(mu.getEmail())) {  // <-- Verifica se email já existe no banco
            mr.setMensagem("Email já cadastrado");
            return new ResponseEntity<ModeloResposta>(mr, HttpStatus.BAD_REQUEST);
    
        } else if (mu.getTelefone() == null || mu.getTelefone().trim().isEmpty()){
            mr.setMensagem("Informe um telefone válido");
            return new ResponseEntity<ModeloResposta>(mr, HttpStatus.BAD_REQUEST);
    
        } else if (!patternTelefone.matcher(mu.getTelefone()).matches()){
            mr.setMensagem("Telefone Inválido. Use (XX) 9XXXX-XXXX");
            return new ResponseEntity<ModeloResposta>(mr, HttpStatus.BAD_REQUEST);
    
        } else if (acao.equals("cadastrar")) {
            return new ResponseEntity<ModeloUser>(ru.save(mu), HttpStatus.CREATED);
            
        }else{
            return new ResponseEntity<ModeloUser>(ru.save(mu),HttpStatus.OK);
        }
        
    }




}
