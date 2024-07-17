package com.senai.lab365.MiniProjeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.senai.lab365.MiniProjeto.models.Medico;
import com.senai.lab365.MiniProjeto.models.Especialidade;
import com.senai.lab365.MiniProjeto.repositorys.MedicoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico createMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    public List<Medico> createMedicos(List<Medico> medicos) {
        return medicoRepository.saveAll(medicos);
    }

    public Medico updateMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    public void deleteMedico(Long id) {
        medicoRepository.deleteById(id);
    }

    public Page<Medico> getMedicosByEspecialidade(Especialidade especialidade, Pageable pageable) {
        return medicoRepository.findByEspecialidade(especialidade, pageable);
    }

    public Page<Medico> getMedicosByNome(String nome, Pageable pageable) {
        return medicoRepository.findByNomeContainingIgnoreCase(nome, pageable);
    }

    public Page<Medico> getMedicosByCrm(String crm, Pageable pageable) {
        return medicoRepository.findByCrmContainingIgnoreCase(crm, pageable);
    }

    public Medico getMedicoByCrm(String crm) {
        return medicoRepository.findByCrm(crm).orElse(null);
    }

    public Optional<Medico> getMedicoById(Long id) {
        return medicoRepository.findById(id);
    }

    public List<Medico> getAllMedicos() {
        return medicoRepository.findAll();
    }

    public Page<Medico> getMedicos(String nome, Especialidade especialidade, LocalDate dataNascimento, Pageable pageable) {
        if (nome != null && especialidade != null && dataNascimento != null) {
            return medicoRepository.findByNomeContainingIgnoreCaseAndEspecialidadeAndDataNascimento(nome, especialidade, dataNascimento, pageable);
        } else if (nome != null && especialidade != null) {
            return medicoRepository.findByNomeContainingIgnoreCaseAndEspecialidade(nome, especialidade, pageable);
        } else if (nome != null && dataNascimento != null) {
            return medicoRepository.findByNomeContainingIgnoreCaseAndDataNascimento(nome, dataNascimento, pageable);
        } else if (especialidade != null && dataNascimento != null) {
            return medicoRepository.findByEspecialidadeAndDataNascimento(especialidade, dataNascimento, pageable);
        } else if (nome != null) {
            return medicoRepository.findByNomeContainingIgnoreCase(nome, pageable);
        } else if (especialidade != null) {
            return medicoRepository.findByEspecialidade(especialidade, pageable);
        } else if (dataNascimento != null) {
            return medicoRepository.findByDataNascimento(dataNascimento, pageable);
        } else {
            return medicoRepository.findAll(pageable);
        }
    }
}