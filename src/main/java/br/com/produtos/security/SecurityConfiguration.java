package br.com.produtos.security;

import br.com.produtos.filter.UsuarioAuthenticationFilter;
import ch.qos.logback.core.encoder.Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final UsuarioAuthenticationFilter usuarioAuthenticationFilter;
    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/usuario/login", //url que usaremos para fazer login
            "/usuario" //url que usaremos para criar um usuário
    };
    // Endpoints que requerem autenticação para serem acessados
    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {
            "/usuario/*",
            "/produto/*",
            "/categoria/*"
    };

    public SecurityConfiguration(UsuarioAuthenticationFilter usuarioAuthenticationFilter) {
        this.usuarioAuthenticationFilter = usuarioAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        DefaultSecurityFilterChain build = httpSecurity.csrf().disable() // Desativa a proteção contra CSRF
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Configura a política de criação de sessão como stateless
                .and().authorizeHttpRequests() // Habilita a autorização para as requisições HTTP
                .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()
                .anyRequest().denyAll()
                .and().addFilterBefore(usuarioAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
        return build;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
