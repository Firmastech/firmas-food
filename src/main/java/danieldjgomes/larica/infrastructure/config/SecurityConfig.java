package danieldjgomes.larica.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .requestCache(RequestCacheConfigurer::disable)
                .authorizeHttpRequests(
                        authorizeConfig -> {
                            authorizeConfig.requestMatchers(
                                    new AntPathRequestMatcher("/login", "POST"),
                                    new AntPathRequestMatcher("/usuarios", "POST")
                            ).permitAll();
                            authorizeConfig.anyRequest().authenticated();
                        })
                .oauth2Login(Customizer.withDefaults())
                .oauth2ResourceServer(conf -> conf.jwt((Customizer.withDefaults()))
                ).build();
    }
}
