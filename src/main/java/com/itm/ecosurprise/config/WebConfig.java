package com.itm.ecosurprise.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Configuración de CORS para permitir solicitudes desde el frontend.
 * Permite solicitudes desde http://localhost:8081 y define los métodos HTTP permitidos.
 * 
 * @Configuration indica que esta clase es una configuración de Spring.
 * @WebMvcConfigurer permite personalizar la configuración de Spring MVC.
 * CorsRegistry se utiliza para registrar las configuraciones de CORS.
 * Cors es un mecanismo de seguridad que permite o restringe el acceso a recursos en un servidor web desde un dominio diferente.
 * .registry.addMapping("/**") permite todas las rutas.
 * .allowedOrigins("http://localhost:8081") permite solicitudes desde el frontend.
 * .allowedMethods("GET", "POST", "PUT", "DELETE") permite los métodos HTTP especificados.
 * .allowedHeaders("*") permite todos los encabezados en las solicitudes.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOriginPatterns("*") // Permite cualquier origen (solo para desarrollo)
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .exposedHeaders("Authorization")
            .allowCredentials(true);
    }
}