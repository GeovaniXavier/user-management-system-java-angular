package com.code.challenge.controller;

import com.code.challenge.dto.DepartamentoDto;
import com.code.challenge.service.DepartamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {


    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @PostMapping
    public ResponseEntity<DepartamentoDto> createDepartamento(@RequestBody DepartamentoDto departamentoDto) {
        DepartamentoDto createdDepartamento = departamentoService.createDepartamento(departamentoDto);
        return new ResponseEntity<>(createdDepartamento, HttpStatus.CREATED);
    }

    @GetMapping("/{departamentoId}")
    public ResponseEntity<DepartamentoDto> getDepartamentoById(@PathVariable Long departamentoId) {
        DepartamentoDto departamentoDto = departamentoService.getDepartamentoById(departamentoId);
        return new ResponseEntity<>(departamentoDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DepartamentoDto>> getAllDepartamentos() {
        List<DepartamentoDto> departamentos = departamentoService.getAllDepartamentos();
        return new ResponseEntity<>(departamentos, HttpStatus.OK);
    }

    @PutMapping("/{departamentoId}")
    public ResponseEntity<DepartamentoDto> updateDepartamento(@PathVariable Long departamentoId, @RequestBody DepartamentoDto departamentoDto) {
        DepartamentoDto updatedDepartamento = departamentoService.updateDepartamento(departamentoId, departamentoDto);
        return new ResponseEntity<>(updatedDepartamento, HttpStatus.OK);
    }

    @DeleteMapping("/{departamentoId}")
    public ResponseEntity<Void> deleteDepartamento(@PathVariable Long departamentoId) {
        departamentoService.deleteDepartamento(departamentoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
