package com.senai.lab365.MiniProjeto.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.senai.lab365.MiniProjeto.dtos.MedicoListDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.senai.lab365.MiniProjeto.models.Medico;
import com.senai.lab365.MiniProjeto.models.Especialidade;
import com.senai.lab365.MiniProjeto.services.MedicoService;
import com.senai.lab365.MiniProjeto.dtos.MedicoRequestDTO;
import com.senai.lab365.MiniProjeto.dtos.MedicoResponseDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private Validator validator;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PagedResourcesAssembler<MedicoListDTO> pagedResourcesAssembler;

    @Autowired
    private MedicoModelAssembler medicoModelAssembler;


    @PostMapping
    @Operation(summary = "Cria um novo médico", description = "Este endpoint é responsável por criar um novo registro de médico no sistema. Ele aceita um único médico ou uma lista de médicos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Médico(s) criado(s) com sucesso", content = @Content(schema = @Schema(implementation = MedicoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida, verifique os dados enviados")
    })
    public ResponseEntity<?> createMedico(@Validated @RequestBody Object request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(Map.of("errors", errorMessages));
        }
        List<MedicoResponseDTO> savedMedicos = new ArrayList<>();
        try {
            if (request instanceof Map) {
                MedicoRequestDTO medicoRequestDTO = objectMapper.convertValue(request, MedicoRequestDTO.class);
                Medico medico = convertToEntity(medicoRequestDTO);
                Medico savedMedico = medicoService.createMedico(medico);
                MedicoResponseDTO responseDTO = convertToResponseDTO(savedMedico);
                savedMedicos.add(responseDTO);
            } else if (request instanceof List) {
                List<MedicoRequestDTO> medicoRequestDTOList = objectMapper.convertValue(request, new TypeReference<List<MedicoRequestDTO>>(){});
                for (MedicoRequestDTO medicoRequestDTO : medicoRequestDTOList) {
                    Medico medico = convertToEntity(medicoRequestDTO);
                    Medico savedMedico = medicoService.createMedico(medico);
                    MedicoResponseDTO responseDTO = convertToResponseDTO(savedMedico);
                    savedMedicos.add(responseDTO);
                }
            } else {
                return ResponseEntity.badRequest().body("Invalid request format.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error processing request: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "mensagem", "Médico(s) cadastrado(s) com sucesso",
                "medicos", savedMedicos
        ));
    }



    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um médico", description = "Este endpoint é responsável por atualizar os dados de um médico baseado no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Médico atualizado com sucesso", content = @Content(schema = @Schema(implementation = MedicoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado")
    })
    public ResponseEntity<?> updateMedico(@PathVariable Long id, @RequestBody MedicoRequestDTO medicoRequestDTO) {
        Medico medico = convertToEntity(medicoRequestDTO);
        medico.setId(id);
        Medico updatedMedico = medicoService.updateMedico(medico);
        MedicoResponseDTO responseDTO = convertToResponseDTO(updatedMedico);
        return ResponseEntity.ok(Map.of(
                "mensagem", "Médico atualizado com sucesso",
                "medico", responseDTO
        ));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um médico", description = "Este endpoint é responsável por deletar um médico baseado no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Médico deletado com sucesso")
    })
    public ResponseEntity<?> deleteMedico(@PathVariable Long id) {
        medicoService.deleteMedico(id);
        return ResponseEntity.ok(Map.of("mensagem", "Médico deletado com sucesso"));
    }

    @GetMapping("/list")
    @Operation(summary = "Lista todos os médicos", description = "Este endpoint retorna uma lista com todos os médicos cadastrados no sistema em formato de paginação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de médicos recuperada com sucesso", content = @Content(schema = @Schema(implementation = MedicoListDTO.class)))
    })
    public ResponseEntity<Map<String, Object>> getMedicos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Especialidade especialidade,
            @RequestParam(required = false) LocalDate dataNascimento,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Medico> medicosPage = medicoService.getMedicos(nome, especialidade, dataNascimento, PageRequest.of(page, size));
        Page<MedicoListDTO> medicoListDTOPage = medicosPage.map(medico -> medicoModelAssembler.toModel(medico));
        PagedModel<EntityModel<MedicoListDTO>> pagedModel = pagedResourcesAssembler.toModel(medicoListDTOPage);
        Map<String, Object> response = Map.of(
                "mensagem", "Lista de médicos recuperada com sucesso",
                "medicos", pagedModel);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/crm/{crm}")
    @Operation(summary = "Obtém um médico pelo CRM", description = "Este endpoint retorna os dados de um médico baseado no CRM fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Médico encontrado", content = @Content(schema = @Schema(implementation = MedicoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado")
    })
    public ResponseEntity<Map<String, Object>> getMedicoByCrm(@PathVariable Integer crm) {
        Medico medico = medicoService.getMedicoByCrm(String.valueOf(crm));
        if (medico != null) {
            MedicoResponseDTO responseDTO = convertToResponseDTO(medico);
            Map<String, Object> response = Map.of(
                    "mensagem", "Médico encontrado com sucesso",
                    "medico", responseDTO);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém um médico pelo ID", description = "Este endpoint retorna os dados de um médico baseado no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Médico encontrado", content = @Content(schema = @Schema(implementation = MedicoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado")
    })
    public ResponseEntity<Map<String, Object>> getMedicoById(@PathVariable Long id) {
        Optional<Medico> medico = medicoService.getMedicoById(id);
        return medico.map(value -> {
            MedicoResponseDTO responseDTO = convertToResponseDTO(value);
            Map<String, Object> response = Map.of(
                    "mensagem", "Médico encontrado com sucesso",
                    "medico", responseDTO);
            return ResponseEntity.ok(response);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Lista todos os médicos", description = "Este endpoint retorna uma lista com todos os médicos cadastrados no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de médicos recuperada com sucesso", content = @Content(schema = @Schema(implementation = MedicoResponseDTO.class)))
    })
    public List<MedicoResponseDTO> getAllMedicos(@Parameter(description = "Nome do médico") @RequestParam(required = false) String nome) {
        List<Medico> medicos = medicoService.getAllMedicos();
        return medicos.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }


    private Medico convertToEntity(MedicoRequestDTO dto) {
        Medico medico = new Medico();
        medico.setNome(dto.getNome());
        try {
            medico.setCrm(String.valueOf(Integer.parseInt(dto.getCrm())));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("CRM inválido");
        }
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

}