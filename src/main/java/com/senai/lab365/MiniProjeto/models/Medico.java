package com.senai.lab365.MiniProjeto.models;

import com.senai.lab365.MiniProjeto.validation.ValidCrm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
@Entity
@Table(name = "medicos", uniqueConstraints = {@UniqueConstraint(columnNames = "crm")})
@Schema(description = "Representa um médico no sistema")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do médico", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull(message = "O nome é obrigatório")
    @Column(nullable = false)
    @Schema(description = "Nome completo do médico", example = "Dr. João Silva")
    private String nome;

    @NotNull(message = "O CRM é obrigatório")
    @Column(unique = true, nullable = false)
    @Schema(description = "Registro CRM do médico", example = "123456", required = true)
    private String crm;

    @NotNull(message = "A data de nascimento é obrigatória")
    @Column(nullable = false)
    @Schema(description = "Data de nascimento do médico", example = "1980-04-23")
    private LocalDate dataNascimento;

    @NotNull(message = "O telefone é obrigatório")
    @Column(nullable = false)
    @Pattern(regexp = "^\\(?\\d{2}\\)?[\\s.-]?\\d{4,5}[\\s.-]?\\d{4}$", message = "O formato do telefone é inválido")
    @Schema(description = "Número de telefone do médico", example = "(11) 91234-5678")
    private String telefone;

    @NotNull(message = "A especialidade é obrigatória")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Schema(description = "Especialidade médica", example = "CARDIOLOGIA")
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