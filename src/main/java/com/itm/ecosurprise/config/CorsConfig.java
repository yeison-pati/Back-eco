package com.itm.ecosurprise.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Value("${app.ip}")
    private String ip;
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // Orígenes específicos autorizados - mejor práctica para producción
        config.addAllowedOrigin("http://localhost:8081");
        config.addAllowedOrigin("http://" + ip + ":8081");
        config.addAllowedOrigin("exp://" + ip + ":8081");
        
        // También puedes agregar otros orígenes específicos si los necesitas
        // config.addAllowedOrigin("https://tudominio.com");
        
        // Permitir todos los encabezados
        config.addAllowedHeader("*");
        
        // Permitir métodos específicos (explícitamente)
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("OPTIONS");
        
        // Permitir cookies y credenciales
        config.setAllowCredentials(true);
        
        // Exponer encabezados específicos al cliente
        config.addExposedHeader("Authorization");
        
        // Caché de preflight durante 1 hora
        config.setMaxAge(3600L);
        
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}