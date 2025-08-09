package com.itm.ecosurprise.infrastructure.config;

import com.itm.ecosurprise.application.*;
import com.itm.ecosurprise.domain.port.in.*;
import com.itm.ecosurprise.domain.port.out.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class BeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public FileStorageService fileStorageService(HttpServletRequest request) {
        return new FileStorageApplicationService(request);
    }

    @Bean
    public LoginUseCase loginUseCase(UsuarioRepository usuarioRepository, TelefonoRepository telefonoRepository, ComercianteRepository comercianteRepository, JwtService jwtService) {
        return new AuthApplicationService(usuarioRepository, telefonoRepository, comercianteRepository, jwtService);
    }

    @Bean
    public RegisterUserUseCase registerUserUseCase(UsuarioRepository usuarioRepository, TelefonoRepository telefonoRepository, ComercianteRepository comercianteRepository, JwtService jwtService) {
        return new AuthApplicationService(usuarioRepository, telefonoRepository, comercianteRepository, jwtService);
    }

    /* se eliminaron los @bean que instancian los servicios de aplicacion que ya tienen @service y @allargsconstructor
    @Bean
    public ComercianteUseCase comercianteUseCase(ComercianteRepository comercianteRepository, FileStorageService fileStorageService) {
        return new ComercianteApplicationService(comercianteRepository, fileStorageService);
    }
    */
}
