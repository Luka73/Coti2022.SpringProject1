package br.spring.project.sp1.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class EmpresaPostRequest {

    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
}
