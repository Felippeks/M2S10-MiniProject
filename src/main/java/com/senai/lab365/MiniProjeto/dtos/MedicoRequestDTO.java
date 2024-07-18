package com.senai.lab365.MiniProjeto.dtos;

import java.time.LocalDate;

import com.senai.lab365.MiniProjeto.models.Especialidade;
import com.senai.lab365.MiniProjeto.validation.ValidCrm;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class MedicoRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    @NotBlank(message = "O CRM é obrigatório")
    @ValidCrm
    private String crm;
    @NotBlank(message = "A data de nascimento é obrigatória")
    private LocalDate dataNascimento;
    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(regexp = "^\\(?\\d{2}\\)?[\\s.-]?\\d{4,5}[\\s.-]?\\d{4}$", message = "O formato do telefone é inválido")
    private String telefone;
    @NotBlank(message = "A especialidade é obrigatória")
    private Especialidade especialidade;

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