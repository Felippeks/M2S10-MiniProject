package com.senai.lab365.MiniProjeto.dtos;

import java.time.LocalDate;

import com.senai.lab365.MiniProjeto.models.Especialidade;
import com.senai.lab365.MiniProjeto.validation.ValidCrm;

public class MedicoRequestDTO {


    private String nome;

    @ValidCrm
    private String crm;

    private LocalDate dataNascimento;


    private String telefone;

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