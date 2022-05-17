package br.spring.project.sp1.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmpresasPutRequest {

    private Integer idEmpresa;
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;

}
