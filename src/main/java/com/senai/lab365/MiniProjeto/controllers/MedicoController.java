package com.senai.lab365.MiniProjeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import com.senai.lab365.MiniProjeto.models.Medico;
import com.senai.lab365.MiniProjeto.models.Especialidade;
import com.senai.lab365.MiniProjeto.services.MedicoService;
import com.senai.lab365.MiniProjeto.dtos.MedicoRequestDTO;
import com.senai.lab365.MiniProjeto.dtos.MedicoResponseDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PagedResourcesAssembler<Medico> pagedResourcesAssembler;

    @PostMapping
    public List<MedicoResponseDTO> createMedicos(@RequestBody List<MedicoRequestDTO> medicoRequestDTOs) {
        List<Medico> medicos = medicoRequestDTOs.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        return medicoService.createMedicos(medicos).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public MedicoResponseDTO updateMedico(@PathVariable Long id, @RequestBody MedicoRequestDTO medicoRequestDTO) {
        Medico medico = convertToEntity(medicoRequestDTO);
        medico.setId(id);
        return convertToResponseDTO(medicoService.updateMedico(medico));
    }

    @DeleteMapping("/{id}")
    public void deleteMedico(@PathVariable Long id) {
        medicoService.deleteMedico(id);
    }

    @GetMapping("/list")
    public PagedModel<MedicoResponseDTO> getMedicos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Especialidade especialidade,
            @RequestParam(required = false) LocalDate dataNascimento,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Medico> medicosPage = medicoService.getMedicos(nome, especialidade, dataNascimento, PageRequest.of(page, size));
        return pagedResourcesAssembler.toModel(medicosPage, new MedicoModelAssembler());
    }

    private Medico convertToEntity(MedicoRequestDTO dto) {
        Medico medico = new Medico();
        medico.setNome(dto.getNome());
        medico.setCrm(dto.getCrm());
        medico.setDataNascimento(dto.getDataNascimento());
        medico.setTelefone(dto.getTelefone());
        medico.setEspecialidade(dto.getEspecialidade());
        return medico;
    }

    private MedicoResponseDTO convertToResponseDTO(Medico medico) {
        MedicoResponseDTO dto = new MedicoResponseDTO();
        dto.setNome(medico.getNome());
        dto.setDataNascimento(medico.getDataNascimento());
        dto.setEspecialidade(medico.getEspecialidade());
        return dto;
    }
}