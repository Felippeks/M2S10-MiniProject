package com.senai.lab365.MiniProjeto.dtos;

import java.time.LocalDate;

import com.senai.lab365.MiniProjeto.models.Especialidade;
import org.springframework.hateoas.RepresentationModel;

public class MedicoResponseDTO extends RepresentationModel<MedicoResponseDTO> {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private Especialidade especialidade;
    private String crm;


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String telefone;

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }
}