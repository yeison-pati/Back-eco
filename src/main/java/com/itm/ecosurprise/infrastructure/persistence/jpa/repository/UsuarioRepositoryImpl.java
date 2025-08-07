package com.itm.ecosurprise.infrastructure.persistence.jpa.repository;

import com.itm.ecosurprise.domain.model.Usuario;
import com.itm.ecosurprise.domain.port.out.UsuarioRepository;
import com.itm.ecosurprise.infrastructure.persistence.jpa.entity.UsuarioEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioJpaRepository usuarioJpaRepository;
    private final ModelMapper modelMapper;

    public UsuarioRepositoryImpl(UsuarioJpaRepository usuarioJpaRepository, ModelMapper modelMapper) {
        this.usuarioJpaRepository = usuarioJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity usuarioEntity = modelMapper.map(usuario, UsuarioEntity.class);
        UsuarioEntity savedUsuario = usuarioJpaRepository.save(usuarioEntity);
        return modelMapper.map(savedUsuario, Usuario.class);
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        return usuarioJpaRepository.findById(id).map(entity -> modelMapper.map(entity, Usuario.class));
    }

    @Override
    public Optional<Usuario> findByCorreo(String correo) {
        return usuarioJpaRepository.findByCorreo(correo).map(entity -> modelMapper.map(entity, Usuario.class));
    }

    @Override
    public Optional<Usuario> findByNumero(String numero) {
        return usuarioJpaRepository.findByNumero(numero).map(entity -> modelMapper.map(entity, Usuario.class));
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioJpaRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, Usuario.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        usuarioJpaRepository.deleteById(id);
    }
}
