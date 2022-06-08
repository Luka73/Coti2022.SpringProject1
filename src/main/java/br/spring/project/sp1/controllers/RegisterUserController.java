package br.spring.project.sp1.controllers;

import br.spring.project.sp1.entities.Usuario;
import br.spring.project.sp1.repositories.IUsuarioRepository;
import br.spring.project.sp1.requests.RegisterUserPostRequest;
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
public class RegisterUserController {
    private static final String ENDPOINT = "/api/register-user";

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @ApiOperation("Método para realizar o registro do usuário.")
    @RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
    public ResponseEntity<String> post(@RequestBody RegisterUserPostRequest request) {
        try{
            if(usuarioRepository.findByEmail(request.getEmail()) != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("O email '" + request.getEmail() + "' já está cadastrado no sistema, tente outro.");
            }

            Usuario usuario = new Usuario();
            usuario.setNome(request.getNome());
            usuario.setEmail(request.getEmail());
            usuario.setSenha(request.getSenha());

            usuarioRepository.save(usuario);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Parabéns '" + usuario.getNome() + "' sua conta foi criada com sucesso!");

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
