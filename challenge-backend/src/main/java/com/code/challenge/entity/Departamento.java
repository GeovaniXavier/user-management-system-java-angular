package com.code.challenge.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_departamento")
public class Departamento {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long departamentoId;

    private String nome;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Usuario> usuarios;

    public Departamento() {
    }

    public Departamento(Long departamentoId, String nome, List<Usuario> usuarios) {
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

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
