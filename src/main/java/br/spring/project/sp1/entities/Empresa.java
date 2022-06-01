package br.spring.project.sp1.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "EMPRESA")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDEMPRESA")
    private Integer idEmpresa;

    @Column(name = "NOMEFANTASIA", length = 150, nullable = false)
    private String nomeFantasia;

    @Column(name = "RAZAOSOCIAL", length = 150, nullable = false, unique = true)
    private String razaoSocial;

    @Column(name = "CNPJ", length = 20, nullable = false, unique = true)
    private String cnpj;

}
