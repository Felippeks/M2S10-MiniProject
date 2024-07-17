package com.senai.lab365.MiniProjeto.controllers;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import com.senai.lab365.MiniProjeto.models.Medico;
import com.senai.lab365.MiniProjeto.dtos.MedicoListDTO;
import org.springframework.stereotype.Component;

@Component
public class MedicoModelAssembler extends RepresentationModelAssemblerSupport<Medico, MedicoListDTO> {

    public MedicoModelAssembler() {
        super(MedicoController.class, MedicoListDTO.class);
    }

    @Override
    public MedicoListDTO toModel(Medico entity) {
        MedicoListDTO dto = new MedicoListDTO();
        dto.setNome(entity.getNome());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setEspecialidade(entity.getEspecialidade());
        return dto;
    }
}