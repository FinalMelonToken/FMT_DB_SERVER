package finalmelontoken.sharestudying.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
         http
                    .httpBasic().disable()
                    .csrf().disable()
                    .cors()
                 .and()
                    .authorizeRequests()
                        .requestMatchers("/private/**").authenticated()
                        .requestMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                    .   anyRequest().permitAll();
         return http.build();
    }
}