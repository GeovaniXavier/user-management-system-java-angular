package com.code.challenge.dto;

public record UsuarioDto(Long usuarioId, String nome, String email, String senha, String telefone, String cpf,
                         String endereco, DepartamentoDto departamento) {
}
