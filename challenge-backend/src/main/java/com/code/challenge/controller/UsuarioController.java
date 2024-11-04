package com.code.challenge.controller;

import com.code.challenge.dto.UsuarioDto;
import com.code.challenge.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> createUsuario(@RequestBody UsuarioDto usuarioDto) {
        UsuarioDto createdUsuario = usuarioService.save(usuarioDto);
        return ResponseEntity.ok(createdUsuario); // Retorna 200 OK com o usuário criado
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> getAllUsuarios() {
        List<UsuarioDto> usuarios = usuarioService.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content se a lista estiver vazia
        }
        return ResponseEntity.ok(usuarios); // 200 OK com a lista de usuários
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<UsuarioDto> getUsuarioById(@PathVariable Long usuarioId) {
        Optional<UsuarioDto> usuario = usuarioService.findById(usuarioId);
        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); // 404 Not Found se o usuário não for encontrado
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<UsuarioDto> updateUsuario(@PathVariable Long usuarioId, @RequestBody UsuarioDto usuarioDto) {
        Optional<UsuarioDto> updatedUsuario = usuarioService.update(usuarioId, usuarioDto);
        return updatedUsuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); // 404 Not Found se o usuário não for encontrado
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long usuarioId) {
        boolean deleted = usuarioService.deleteById(usuarioId);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content se o usuário foi excluído
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found se o usuário não for encontrado
        }
    }
}
