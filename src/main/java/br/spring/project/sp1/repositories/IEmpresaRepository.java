package br.spring.project.sp1.repositories;

import br.spring.project.sp1.entities.Empresa;
import org.springframework.data.repository.CrudRepository;

public interface IEmpresaRepository extends CrudRepository<Empresa, Integer> {

}
