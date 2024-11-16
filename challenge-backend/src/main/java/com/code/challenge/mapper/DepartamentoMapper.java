package com.code.challenge.mapper;

import com.code.challenge.dto.DepartamentoDto;
import com.code.challenge.entity.Departamento;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DepartamentoMapper {

    // Converte um DepartamentoDto em uma entidade Departamento
    public Departamento toEntity(DepartamentoDto dto) {
        if (dto == null) {
            return null;
        }

        Departamento departamento = new Departamento();
        departamento.setDepartamentoId(dto.departamentoId());
        departamento.setNome(dto.nome());

        return departamento;
    }

    // Converte uma entidade Departamento em um DepartamentoDto
    public DepartamentoDto toDto(Departamento entity) {
        if (entity == null) {
            return null;
        }

        return new DepartamentoDto(entity.getDepartamentoId(), entity.getNome());
    }

    // Converte uma lista de entidades Departamento em uma lista de DepartamentoDto
    public List<DepartamentoDto> toDtoList(List<Departamento> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Converte uma lista de DepartamentoDto em uma lista de entidades Departamento
    public List<Departamento> toEntityList(List<DepartamentoDto> dtos) {
        if (dtos == null) {
            return null;
        }

        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
