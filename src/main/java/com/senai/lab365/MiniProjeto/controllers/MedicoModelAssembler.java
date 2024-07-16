package com.senai.lab365.MiniProjeto.controllers;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import com.senai.lab365.MiniProjeto.models.Medico;
import com.senai.lab365.MiniProjeto.dtos.MedicoResponseDTO;

public class MedicoModelAssembler extends RepresentationModelAssemblerSupport<Medico, MedicoResponseDTO> {

    public MedicoModelAssembler() {
        super(MedicoController.class, MedicoResponseDTO.class);
    }

    @Override
    public MedicoResponseDTO toModel(Medico entity) {
        MedicoResponseDTO dto = new MedicoResponseDTO();
        dto.setNome(entity.getNome());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setEspecialidade(entity.getEspecialidade());
        return dto;
    }
}