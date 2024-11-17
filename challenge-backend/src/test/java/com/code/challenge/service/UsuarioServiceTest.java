package com.code.challenge.service;

import com.code.challenge.dto.DepartamentoDto;
import com.code.challenge.dto.UsuarioDto;
import com.code.challenge.entity.Departamento;
import com.code.challenge.entity.Usuario;
import com.code.challenge.mapper.UsuarioMapper;
import com.code.challenge.repository.DepartamentoRepository;
import com.code.challenge.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private DepartamentoRepository departamentoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @Test
    void deveSalvarUsuarioComSucesso() {
        // Arrange
        DepartamentoDto departamentoDto = new DepartamentoDto(1L, "Departamento A");
        UsuarioDto usuarioDto = new UsuarioDto(1L, "João", "joao@email.com", "senha123", "11999999999", "123.456.789-00", "Rua A, 123", departamentoDto);
        Usuario usuario = new Usuario();
        usuario.setNome("João");

        when(departamentoRepository.findByNome("Departamento A")).thenReturn(Optional.of(new Departamento()));
        when(usuarioMapper.toEntity(usuarioDto)).thenReturn(usuario);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        when(usuarioMapper.toDto(usuario)).thenReturn(usuarioDto);

        // Act
        UsuarioDto resultado = usuarioService.save(usuarioDto);

        // Assert
        assertNotNull(resultado);
        assertEquals("João", resultado.nome());
        verify(departamentoRepository, times(1)).findByNome("Departamento A");
        verify(usuarioRepository, times(1)).save(usuario);
        verify(usuarioMapper, times(1)).toEntity(usuarioDto);
        verify(usuarioMapper, times(1)).toDto(usuario);
    }

    @Test
    void deveBuscarUsuarioPorIdComSucesso() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(1L);
        usuario.setNome("Maria");
        UsuarioDto usuarioDto = new UsuarioDto(1L, "Maria", "maria@email.com", "senha123", "11988888888", "987.654.321-00", "Rua B, 456", new DepartamentoDto(2L, "Departamento B"));

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioMapper.toDto(usuario)).thenReturn(usuarioDto);

        // Act
        Optional<UsuarioDto> resultado = usuarioService.findById(1L);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals("Maria", resultado.get().nome());
        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioMapper, times(1)).toDto(usuario);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        // Arrange
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> usuarioService.findById(1L).orElseThrow(() -> new RuntimeException("Usuário não encontrado")));
        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void deveDeletarUsuarioComSucesso() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(1L);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        // Act
        boolean resultado = usuarioService.deleteById(1L);

        // Assert
        assertTrue(resultado);
        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, times(1)).deleteById(1L);
    }

    @Test
    void deveAtualizarUsuarioComSucesso() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(1L);
        usuario.setNome("Maria");
        UsuarioDto usuarioDto = new UsuarioDto(1L, "Maria", "maria@email.com", "senha123", "11988888888", "987.654.321-00", "Rua B, 456", new DepartamentoDto(2L, "Departamento B"));

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioMapper.toEntity(usuarioDto)).thenReturn(usuario);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        when(usuarioMapper.toDto(usuario)).thenReturn(usuarioDto);

        // Act
        Optional<UsuarioDto> resultado = usuarioService.update(1L, usuarioDto);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals("Maria", resultado.get().nome());
        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioMapper, times(1)).toEntity(usuarioDto);
        verify(usuarioRepository, times(1)).save(usuario);
        verify(usuarioMapper, times(1)).toDto(usuario);
    }
}
