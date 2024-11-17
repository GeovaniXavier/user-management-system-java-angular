package com.code.challenge.mapper;

import com.code.challenge.dto.UsuarioDto;
import com.code.challenge.dto.DepartamentoDto;
import com.code.challenge.entity.Usuario;
import com.code.challenge.entity.Departamento;
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
                new DepartamentoDto(usuario.getDepartamento().getDepartamentoId(), usuario.getDepartamento().getNome())
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

        // Buscar o departamento pelo nome fornecido no DTO
        Departamento departamento = departamentoRepository.findByNome(dto.departamento().nome())
                .orElseThrow(() -> new RuntimeException("Departamento não encontrado: " + dto.departamento().nome()));
        usuario.setDepartamento(departamento);

        return usuario;
    }

    public List<UsuarioDto> toDtoList(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
