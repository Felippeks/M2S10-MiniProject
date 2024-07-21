package com.senai.lab365.MiniProjeto.services;

import com.senai.lab365.MiniProjeto.exceptions.CrmJaCadastradoException;
import com.senai.lab365.MiniProjeto.exceptions.DataNascimentoInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.senai.lab365.MiniProjeto.models.Medico;
import com.senai.lab365.MiniProjeto.models.Especialidade;
import com.senai.lab365.MiniProjeto.repositorys.MedicoRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico createMedico(Medico medico) {
        Optional<Medico> medicoExistente = medicoRepository.findByCrm(medico.getCrm());
        if (medicoExistente.isPresent()) {
            throw new CrmJaCadastradoException("CRM já cadastrado: " + medico.getCrm());
        }

        validarDataNascimento(medico.getDataNascimento());
        return medicoRepository.save(medico);
    }
    public boolean validarDataNascimento(LocalDate dataNascimento) {
        LocalDate hoje = LocalDate.now();
        if (dataNascimento.isAfter(hoje)) {
            throw new DataNascimentoInvalidaException("A data de nascimento não pode ser futura.");
        }
        if (Period.between(dataNascimento, hoje).getYears() < 18) {
            throw new DataNascimentoInvalidaException("O médico deve ter pelo menos 18 anos.");
        }
        return true;
    }

    public Medico updateMedico(Medico medico) {
        validarDataNascimento(medico.getDataNascimento());
        return medicoRepository.save(medico);
    }

    public void deleteMedico(Long id) {
        medicoRepository.deleteById(id);
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