package br.spring.project.sp1.controllers;

import br.spring.project.sp1.entities.Usuario;
import br.spring.project.sp1.repositories.IUsuarioRepository;
import br.spring.project.sp1.requests.LoginPostRequest;
import br.spring.project.sp1.security.TokenSecurity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;

@Transactional
@Controller
public class LoginController {
    private static final String ENDPOINT = "/api/login";

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @ApiOperation("Método para realizar a autenticação do usuário da API.")
    @RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
    public ResponseEntity<String> post(@RequestBody LoginPostRequest request) {
        try {
            Usuario usuario = usuarioRepository.findByEmailAndSenha(request.getEmail(), request.getSenha());
            if(usuario == null) {
                ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Acesso negado. Email e senha inválidos.");
            }

            String token = TokenSecurity.generateToken(usuario.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body(token);

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


}
