package com.code.challenge.controller;

import com.code.challenge.dto.UsuarioDto;
import com.code.challenge.service.DepartamentoService;
import com.code.challenge.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private DepartamentoService departamentoService;

    @Test
    void deveCriarUsuarioComSucesso() throws Exception {
        // Arrange
        UsuarioDto usuarioDto = new UsuarioDto(1L, "João", "joao@email.com", "senha123", "11999999999", "123.456.789-00", "Rua A, 123", null);
        when(usuarioService.save(any(UsuarioDto.class))).thenReturn(usuarioDto);

        // Act & Assert
        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"usuarioId\": 1," +
                                "\"nome\": \"João\", " +
                                "\"email\": \"joao@email.com\", " +
                                "\"senha\": \"senha123\", " +
                                "\"telefone\": \"11999999999\", " +
                                "\"cpf\": \"123.456.789-00\", " +
                                "\"endereco\": \"Rua A, 123\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João"));
    }

    @Test
    void deveRetornarTodosOsUsuarios() throws Exception {
        // Arrange
        UsuarioDto usuarioDto = new UsuarioDto(1L, "Maria", "maria@email.com", "senha123", "11988888888", "987.654.321-00", "Rua B, 456", null);
        when(usuarioService.findAll()).thenReturn(List.of(usuarioDto));

        // Act & Assert
        mockMvc.perform(get("/usuarios")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Maria"));
    }

    @Test
    void deveRetornarNenhumUsuario() throws Exception {
        // Arrange
        when(usuarioService.findAll()).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(get("/usuarios")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deveRetornarUsuarioPorId() throws Exception {
        // Arrange
        UsuarioDto usuarioDto = new UsuarioDto(1L, "Carlos", "carlos@email.com", "senha123", "11977777777", "123.123.123-00", "Rua C, 789", null);
        when(usuarioService.findById(1L)).thenReturn(Optional.of(usuarioDto));

        // Act & Assert
        mockMvc.perform(get("/usuarios/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Carlos"));
    }

    @Test
    void deveRetornar404QuandoUsuarioNaoEncontrado() throws Exception {
        // Arrange
        when(usuarioService.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(get("/usuarios/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveAtualizarUsuarioComSucesso() throws Exception {
        // Arrange
        UsuarioDto usuarioDto = new UsuarioDto(1L, "Ana", "ana@email.com", "senha123", "11966666666", "456.456.456-00", "Rua D, 101", null);
        when(usuarioService.update(any(Long.class), any(UsuarioDto.class))).thenReturn(Optional.of(usuarioDto));

        // Act & Assert
        mockMvc.perform(put("/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"usuarioId\": 1," +
                                "\"nome\": \"Ana\", " +
                                "\"email\": \"ana@email.com\", " +
                                "\"senha\": \"senha123\", " +
                                "\"telefone\": \"11966666666\", " +
                                "\"cpf\": \"456.456.456-00\", " +
                                "\"endereco\": \"Rua D, 101\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Ana"));
    }

    @Test
    void deveDeletarUsuarioComSucesso() throws Exception {
        // Arrange
        when(usuarioService.deleteById(1L)).thenReturn(true);

        // Act & Assert
        mockMvc.perform(delete("/usuarios/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deveRetornar404AoDeletarUsuarioNaoExistente() throws Exception {
        // Arrange
        when(usuarioService.deleteById(1L)).thenReturn(false);

        // Act & Assert
        mockMvc.perform(delete("/usuarios/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
