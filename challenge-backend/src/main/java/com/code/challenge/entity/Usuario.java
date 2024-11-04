package com.code.challenge.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long usuarioId;

    private String nome;

    private String email;

    private String senha;

    private String telefone;

    private String cpf;

    private String endereco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamento departamento;

    public Usuario() {
    }

    public Usuario(Long usuarioId, String nome, String email, String senha, String telefone, String cpf, String endereco, Departamento departamento) {
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.cpf = cpf;
        this.endereco = endereco;
        this.departamento = departamento;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}
