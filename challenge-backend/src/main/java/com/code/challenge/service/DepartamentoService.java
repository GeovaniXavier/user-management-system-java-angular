package com.code.challenge.service;

import com.code.challenge.dto.DepartamentoDto;
import com.code.challenge.entity.Departamento;
import com.code.challenge.mapper.DepartamentoMapper;
import com.code.challenge.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    private final DepartamentoMapper departamentoMapper;

    public DepartamentoService(DepartamentoRepository departamentoRepository, DepartamentoMapper departamentoMapper) {
        this.departamentoRepository = departamentoRepository;
        this.departamentoMapper = departamentoMapper;
    }

    public DepartamentoDto createDepartamento(DepartamentoDto departamentoDto) {
        Departamento departamento = departamentoMapper.toEntity(departamentoDto);
        Departamento savedDepartamento = departamentoRepository.save(departamento);
        return departamentoMapper.toDto(savedDepartamento);
    }

    public DepartamentoDto getDepartamentoById(Long departamentoId) {
        Optional<Departamento> departamento = departamentoRepository.findById(departamentoId);
        if (departamento.isPresent()) {
            return departamentoMapper.toDto(departamento.get());
        } else {
            throw new RuntimeException("Departamento não encontrado para o ID: " + departamentoId);
        }
    }

    public List<DepartamentoDto> getAllDepartamentos() {
        List<Departamento> departamentos = departamentoRepository.findAll();
        return departamentoMapper.toDtoList(departamentos);
    }

    public DepartamentoDto updateDepartamento(Long departamentoId, DepartamentoDto departamentoDto) {
        Optional<Departamento> optionalDepartamento = departamentoRepository.findById(departamentoId);
        if (optionalDepartamento.isPresent()) {
            Departamento departamentoToUpdate = optionalDepartamento.get();
            departamentoToUpdate.setNome(departamentoDto.nome());
            Departamento updatedDepartamento = departamentoRepository.save(departamentoToUpdate);
            return departamentoMapper.toDto(updatedDepartamento);
        } else {
            throw new RuntimeException("Departamento não encontrado para o ID: " + departamentoId);
        }
    }

    public void deleteDepartamento(Long departamentoId) {
        if (departamentoRepository.existsById(departamentoId)) {
            departamentoRepository.deleteById(departamentoId);
        } else {
            throw new RuntimeException("Departamento não encontrado para o ID: " + departamentoId);
        }
    }
}
