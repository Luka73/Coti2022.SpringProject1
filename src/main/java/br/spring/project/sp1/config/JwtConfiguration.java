package br.spring.project.sp1.config;

import br.spring.project.sp1.security.JwtSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class JwtConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .addFilterAfter(new JwtSecurity(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/register-user").permitAll()
                .antMatchers(HttpMethod.POST, "/api/password-recover").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest()
                .authenticated();
    }

    // configuração para liberar a documentação do SWAGGER
    private static final String[] SWAGGER = {
            "/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
            "/configuration/security", "/swagger-ui.html", "/webjars/**",
            "/v3/api-docs/**", "/swagger-ui/**"
    };

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(SWAGGER);
    }
}



