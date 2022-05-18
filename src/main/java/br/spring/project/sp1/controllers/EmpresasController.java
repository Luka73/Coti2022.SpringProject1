package br.spring.project.sp1.controllers;

import br.spring.project.sp1.entities.Empresa;
import br.spring.project.sp1.repositories.IEmpresaRepository;
import br.spring.project.sp1.requests.EmpresaPostRequest;
import br.spring.project.sp1.requests.EmpresasPutRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Controller
public class EmpresasController {

    final String ENDPOINT = "/api/empresas";

    @Autowired
    private IEmpresaRepository empresaRepository;

    @ApiOperation("Método para realizar o cadastro de uma empresa.")
    @RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
    public ResponseEntity<String> post(@RequestBody EmpresaPostRequest request) {
        try{
            Empresa empresa =  new Empresa();
            empresa.setNomeFantasia(request.getNomeFantasia());
            empresa.setRazaoSocial(request.getRazaoSocial());
            empresa.setCnpj(request.getCnpj());

            empresaRepository.save(empresa);

            return ResponseEntity.status(HttpStatus.CREATED) //HTTP 201
                    .body("Empresa cadastrada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }

    }

    @ApiOperation("Método para realizar a atualização dos dados de uma empresa.")
    @RequestMapping(value = ENDPOINT, method = RequestMethod.PUT)
    public ResponseEntity<String> put(@RequestBody EmpresasPutRequest request){
        try {
            Optional<Empresa> consulta = empresaRepository.findById(request.getIdEmpresa());

            if(consulta.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Nenhuma empresa foi encontrada, por favor verifique o id informado.");
            }

            Empresa empresa = consulta.get();
            empresa.setNomeFantasia(request.getNomeFantasia());
            empresaRepository.save(empresa);

            return ResponseEntity.status(HttpStatus.OK)
                    .body("Empresa atualizada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @ApiOperation("Método para deletar uma empresa através do seu identificador.")
    @RequestMapping(value = ENDPOINT + "/{idEmpresa}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("idEmpresa") Integer idEmpresa) {

        try {
            Optional<Empresa> consulta = empresaRepository.findById(idEmpresa);

            if(consulta.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Nenhuma empresa foi encontrada, por favor verifique o id informado.");
            }

            Empresa empresa = consulta.get();
            empresaRepository.delete(empresa);

            return ResponseEntity.status(HttpStatus.OK).body("Empresa excluída com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @ApiOperation("Método para consultar todas as empresas cadastradas.")
    @RequestMapping(value = ENDPOINT, method = RequestMethod.GET)
    public ResponseEntity<List<Empresa>> getAll() {
        try {
            List<Empresa> consulta = (List<Empresa>) empresaRepository.findAll();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(consulta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @ApiOperation("Método para consultar uma a empresas através do seu identificador.")
    @RequestMapping(value = ENDPOINT + "/{idEmpresa}", method = RequestMethod.GET)
    public ResponseEntity<Empresa> getById(@PathVariable("idEmpresa") Integer idEmpresa) {
        try {
            Optional<Empresa> consulta = empresaRepository.findById(idEmpresa);

            if(consulta.isPresent()) {
                Empresa empresa = consulta.get();
                return ResponseEntity.status(HttpStatus.OK).body(empresa);
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
