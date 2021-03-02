package dev.salex.resourceserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.client.WebClient;

@EnableWebFlux
@Configuration
public class ResourceServerConfiguration {

    @Value("${spring.security.oauth2.resourceserver.opaque.introspection-uri}")
    private String introspectionUri;

    @Value("${spring.security.oauth2.resourceserver.opaque.introspection-client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.resourceserver.opaque.introspection-client-secret}")
    private String clientSecret;

    @Value("${chuckUri}")
    private String chuckUri;

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.httpBasic().disable()
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(
                            HttpMethod.GET,
                            "/v2/api-docs",
                            "/swagger-resources/**",
                            "/swagger-ui/**",
                            "/webjars/**",
                            "favicon.ico").permitAll()
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .opaqueToken(token -> token.introspectionUri(this.introspectionUri)
                        .introspectionClientCredentials(this.clientId, this.clientSecret)));
        return http.build();
    }

    @Bean
    WebClient webClient() {
        WebClient client = WebClient.create(chuckUri);
        return client;
    }

}
