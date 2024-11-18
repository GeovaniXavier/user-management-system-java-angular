package com.code.challenge.service;

import com.code.challenge.dto.UsuarioDto;
import com.code.challenge.entity.Departamento;
import com.code.challenge.entity.Usuario;
import com.code.challenge.exception.ResourceNotFoundException;
import com.code.challenge.mapper.UsuarioMapper;
import com.code.challenge.repository.DepartamentoRepository;
import com.code.challenge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper;

    private final DepartamentoRepository departamentoRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, DepartamentoRepository departamentoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.departamentoRepository = departamentoRepository;
    }

    public UsuarioDto save(UsuarioDto usuarioDto) {
        Departamento departamento = departamentoRepository.findByNome(usuarioDto.departamento().nome())
                .orElseThrow(() -> new ResourceNotFoundException("Departamento não encontrado: " + usuarioDto.departamento().nome()));
        Usuario usuario = usuarioMapper.toEntity(usuarioDto);
        usuario.setDepartamento(departamento);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(savedUsuario);
    }

    public List<UsuarioDto> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.isEmpty() ? Collections.emptyList() : usuarios.stream().map(usuarioMapper::toDto).toList();
    }

    public Optional<UsuarioDto> findById(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDto)
                .or(() -> {
                    throw new ResourceNotFoundException("Usuário não encontrado com ID: " + id);
                });
    }

    public Optional<UsuarioDto> update(Long id, UsuarioDto usuarioDto) {
        return usuarioRepository.findById(id)
                .map(existingUsuario -> {
                    Usuario updatedUsuario = usuarioMapper.toEntity(usuarioDto);
                    updatedUsuario.setUsuarioId(existingUsuario.getUsuarioId());
                    Usuario savedUsuario = usuarioRepository.save(updatedUsuario);
                    return usuarioMapper.toDto(savedUsuario);
                }).or(() -> {
                    throw new ResourceNotFoundException("Usuário não encontrado com ID: " + id);
                });
    }

    public boolean deleteById(Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuarioRepository.deleteById(id);
                    return true;
                }).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
    }
}
