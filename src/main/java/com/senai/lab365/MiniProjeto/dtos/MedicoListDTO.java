package com.senai.lab365.MiniProjeto.dtos;

import java.time.LocalDate;
import com.senai.lab365.MiniProjeto.models.Especialidade;
import org.springframework.hateoas.RepresentationModel;

public class MedicoListDTO extends RepresentationModel<MedicoListDTO> {

    private String nome;
    private Especialidade especialidade;
    private LocalDate dataNascimento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}