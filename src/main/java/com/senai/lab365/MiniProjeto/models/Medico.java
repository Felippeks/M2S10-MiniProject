package com.senai.lab365.MiniProjeto.models;

import com.senai.lab365.MiniProjeto.validation.ValidCrm;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

@Entity
@Table(name = "medicos", uniqueConstraints = {@UniqueConstraint(columnNames = "crm")})
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    @ValidCrm
    @NotNull(message = "O CRM é obrigatório")
    @Column(unique = true, nullable = false)
    private String crm;

    @NotNull(message = "A data de nascimento é obrigatória")
    @Column(nullable = false)
    private LocalDate dataNascimento;

    @NotNull(message = "O telefone é obrigatório")
    @Column(nullable = false)
    @Pattern(regexp = "^\\(?\\d{2}\\)?[\\s.-]?\\d{4,5}[\\s.-]?\\d{4}$", message = "O formato do telefone é inválido")
    private String telefone;

    @NotNull(message = "A especialidade é obrigatória")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    public Medico() {
    }

    public Medico(String nome, String crm, LocalDate dataNascimento, String telefone, Especialidade especialidade) {
        this.nome = nome;
        this.crm = crm;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.especialidade = especialidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }
}