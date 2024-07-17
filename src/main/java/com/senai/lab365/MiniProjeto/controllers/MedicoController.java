package com.senai.lab365.MiniProjeto.controllers;

import com.senai.lab365.MiniProjeto.dtos.MedicoListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.senai.lab365.MiniProjeto.models.Medico;
import com.senai.lab365.MiniProjeto.models.Especialidade;
import com.senai.lab365.MiniProjeto.services.MedicoService;
import com.senai.lab365.MiniProjeto.dtos.MedicoRequestDTO;
import com.senai.lab365.MiniProjeto.dtos.MedicoResponseDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PagedResourcesAssembler<MedicoListDTO> pagedResourcesAssembler;

    @Autowired
    private MedicoModelAssembler medicoModelAssembler;

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
    public ResponseEntity<PagedModel<EntityModel<MedicoListDTO>>> getMedicos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Especialidade especialidade,
            @RequestParam(required = false) LocalDate dataNascimento,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Medico> medicosPage = medicoService.getMedicos(nome, especialidade, dataNascimento, PageRequest.of(page, size));
        Page<MedicoListDTO> medicoListDTOPage = medicosPage.map(medico -> medicoModelAssembler.toModel(medico));
        PagedModel<EntityModel<MedicoListDTO>> pagedModel = pagedResourcesAssembler.toModel(medicoListDTOPage);
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/crm/{crm}")
    public ResponseEntity<MedicoResponseDTO> getMedicoByCrm(@PathVariable String crm) {
        Medico medico = medicoService.getMedicoByCrm(crm);
        if (medico != null) {
            return ResponseEntity.ok(convertToResponseDTO(medico));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> getMedicoById(@PathVariable Long id) {
        Optional<Medico> medico = medicoService.getMedicoById(id);
        return medico.map(value -> ResponseEntity.ok(convertToResponseDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<MedicoResponseDTO> getAllMedicos() {
        List<Medico> medicos = medicoService.getAllMedicos();
        return medicos.stream()
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
        dto.setDataNascimento(medico.getDataNascimento());
        dto.setEspecialidade(medico.getEspecialidade());
        dto.setCrm(medico.getCrm());
        dto.setTelefone(medico.getTelefone());
        return dto;
    }

    private MedicoListDTO convertToMedicoListDTO(Medico medico) {
        MedicoListDTO dto = new MedicoListDTO();
        dto.setNome(medico.getNome());
        dto.setDataNascimento(medico.getDataNascimento());
        dto.setEspecialidade(medico.getEspecialidade());
        return dto;
    }
}