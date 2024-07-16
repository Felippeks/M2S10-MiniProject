package com.senai.lab365.MiniProjeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.senai.lab365.MiniProjeto.models.Medico;
import com.senai.lab365.MiniProjeto.models.Especialidade;
import com.senai.lab365.MiniProjeto.services.MedicoService;
import com.senai.lab365.MiniProjeto.dtos.MedicoRequestDTO;
import com.senai.lab365.MiniProjeto.dtos.MedicoResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public List<MedicoResponseDTO> getAllMedicos() {
        return medicoService.getAllMedicos().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public MedicoResponseDTO createMedico(@RequestBody MedicoRequestDTO medicoRequestDTO) {
        Medico medico = convertToEntity(medicoRequestDTO);
        return convertToResponseDTO(medicoService.createMedico(medico));
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

    @GetMapping("/especialidade/{especialidade}")
    public List<MedicoResponseDTO> getMedicosByEspecialidade(@PathVariable Especialidade especialidade) {
        return medicoService.getMedicosByEspecialidade(especialidade).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/nome/{nome}")
    public List<MedicoResponseDTO> getMedicosByNome(@PathVariable String nome) {
        return medicoService.getMedicosByNome(nome).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/crm/{crm}")
    public List<MedicoResponseDTO> getMedicosByCrm(@PathVariable String crm) {
        return medicoService.getMedicosByCrm(crm).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
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
        dto.setId(medico.getId());
        dto.setNome(medico.getNome());
        dto.setCrm(medico.getCrm());
        dto.setDataNascimento(medico.getDataNascimento());
        dto.setTelefone(medico.getTelefone());
        dto.setEspecialidade(medico.getEspecialidade());
        return dto;
    }
}