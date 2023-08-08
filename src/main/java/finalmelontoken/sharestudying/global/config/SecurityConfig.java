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
                .cors().and()
                .authorizeRequests()
                .requestMatchers("/private/**").authenticated() //private로 시작하는 uri는 로그인 필수
                .requestMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") //admin으로 시작하는 uri는 관릴자 계정만 접근 가능
                .anyRequest().permitAll(); //나머지 uri는 모든 접근 허용
         return http.build();
    }
}