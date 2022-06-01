package br.spring.project.sp1.controllers;

import br.spring.project.sp1.repositories.IUsuarioRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;

@Transactional
@Controller
public class PasswordRecoverController {
    private static final String ENDPOINT = "/api/password-recover";

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @ApiOperation("Método para realizar a recuperação da senha do usuário.")
    @RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
    public ResponseEntity<String> post() {
        return null; // TODO
    }

}
