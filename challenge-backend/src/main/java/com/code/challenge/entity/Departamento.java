package com.code.challenge.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "departamento")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departamentoId;

    @Column(nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Usuario> usuarios;

    public Departamento() {
    }

    public Departamento(Long departamentoId, String nome, Set<Usuario> usuarios) {
        this.departamentoId = departamentoId;
        this.nome = nome;
        this.usuarios = usuarios;
    }

    public Long getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Long departamentoId) {
        this.departamentoId = departamentoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}