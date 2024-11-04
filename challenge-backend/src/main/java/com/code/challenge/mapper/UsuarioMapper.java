package com.code.challenge.mapper;

import com.code.challenge.dto.UsuarioDto;
import com.code.challenge.entity.Usuario;
import com.code.challenge.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {


    private final DepartamentoRepository departamentoRepository;

    @Autowired
    public UsuarioMapper(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    public UsuarioDto toDto(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return new UsuarioDto(
                usuario.getUsuarioId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getTelefone(),
                usuario.getCpf(),
                usuario.getEndereco(),
                usuario.getDepartamento().getDepartamentoId()
        );
    }

    public Usuario toEntity(UsuarioDto dto) {
        if (dto == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(dto.usuarioId());
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setTelefone(dto.telefone());
        usuario.setCpf(dto.cpf());
        usuario.setEndereco(dto.endereco());
        // Buscar o departamento pelo ID
        usuario.setDepartamento(departamentoRepository.findById(dto.departamentoId()).orElse(null));

        return usuario;
    }

    public List<UsuarioDto> toDto(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}