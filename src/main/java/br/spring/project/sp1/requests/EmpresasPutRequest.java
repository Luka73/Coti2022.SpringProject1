package br.spring.project.sp1.requests;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmpresasPutRequest {

    private Integer idEmpresa;
    private String nomeFantasia;
}
