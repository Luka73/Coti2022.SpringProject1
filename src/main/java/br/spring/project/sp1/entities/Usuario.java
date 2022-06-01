package br.spring.project.sp1.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "USUARIO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IDUSUARIO")
    private Integer idUsuario;

    @Column(name = "NOME", length = 150, nullable = false)
    private String nome;

    @Column(name = "EMAIL", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "SENHA", length = 40, nullable = false)
    private String senha;
}
