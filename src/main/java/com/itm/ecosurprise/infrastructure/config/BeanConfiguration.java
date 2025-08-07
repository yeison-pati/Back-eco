package com.itm.ecosurprise.infrastructure.config;

import com.itm.ecosurprise.application.*;
import com.itm.ecosurprise.domain.port.in.*;
import com.itm.ecosurprise.domain.port.out.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public FileStorageService fileStorageService(jakarta.servlet.http.HttpServletRequest request) {
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

    @Bean
    public ProductUseCase productUseCase(ProductoRepository productoRepository, ComercianteRepository comercianteRepository, FileStorageService fileStorageService) {
        return new ProductApplicationService(productoRepository, comercianteRepository, fileStorageService);
    }

    @Bean
    public ComercianteUseCase comercianteUseCase(ComercianteRepository comercianteRepository, ProductoRepository productoRepository, FileStorageService fileStorageService) {
        return new ComercianteApplicationService(comercianteRepository, productoRepository, fileStorageService);
    }

    @Bean
    public ConsumidorUseCase consumidorUseCase(ConsumidorRepository consumidorRepository) {
        return new ConsumidorApplicationService(consumidorRepository);
    }

    @Bean
    public DireccionUseCase direccionUseCase(DireccionRepository direccionRepository) {
        return new DireccionApplicationService(direccionRepository);
    }

    @Bean
    public EntregaUseCase entregaUseCase(EntregaRepository entregaRepository) {
        return new EntregaApplicationService(entregaRepository);
    }

    @Bean
    public EntregaDireccionUseCase entregaDireccionUseCase(EntregaDireccionRepository entregaDireccionRepository) {
        return new EntregaDireccionApplicationService(entregaDireccionRepository);
    }

    @Bean
    public FechaUseCase fechaUseCase(FechaRepository fechaRepository) {
        return new FechaApplicationService(fechaRepository);
    }

    @Bean
    public OrderUseCase orderUseCase(OrdenRepository ordenRepository, ConsumidorRepository consumidorRepository, ProductoRepository productoRepository, CartUseCase cartUseCase) {
        return new OrdenApplicationService(ordenRepository, consumidorRepository, productoRepository, cartUseCase);
    }

    @Bean
    public OrdenProductoUseCase ordenProductoUseCase(OrdenProductoRepository ordenProductoRepository) {
        return new OrdenProductoApplicationService(ordenProductoRepository);
    }

    @Bean
    public PagoUseCase pagoUseCase(PagoRepository pagoRepository) {
        return new PagoApplicationService(pagoRepository);
    }

    @Bean
    public PuntuacionUseCase puntuacionUseCase(PuntuacionRepository puntuacionRepository) {
        return new PuntuacionApplicationService(puntuacionRepository);
    }

    @Bean
    public RepartidorUseCase repartidorUseCase(RepartidorRepository repartidorRepository) {
        return new RepartidorApplicationService(repartidorRepository);
    }

    @Bean
    public ReporteUseCase reporteUseCase(ReporteRepository reporteRepository) {
        return new ReporteApplicationService(reporteRepository);
    }

    @Bean
    public TelefonoUseCase telefonoUseCase(TelefonoRepository telefonoRepository) {
        return new TelefonoApplicationService(telefonoRepository);
    }

    @Bean
    public UsuarioDireccionUseCase usuarioDireccionUseCase(UsuarioDireccionRepository usuarioDireccionRepository) {
        return new UsuarioDireccionApplicationService(usuarioDireccionRepository);
    }

    @Bean
    public SetUserImageUseCase setUserImageUseCase(UsuarioRepository usuarioRepository, FileStorageService fileStorageService) {
        return new UserApplicationService(usuarioRepository, fileStorageService);
    }

    @Bean
    public PreparationOrdersUseCase preparationOrdersUseCase(ComercianteRepository comercianteRepository, OrderUseCase orderUseCase) {
        return new PreparationOrdersApplicationService(comercianteRepository, orderUseCase);
    }
}
