package br.spring.project.sp1.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class LoginPostRequest {
    private String email;
    private String senha;
}
