package com.senai.lab365.MiniProjeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.lab365.MiniProjeto.models.Medico;
import com.senai.lab365.MiniProjeto.models.Especialidade;
import com.senai.lab365.MiniProjeto.repositorys.MedicoRepository;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<Medico> getAllMedicos() {
        return medicoRepository.findAll();
    }

    public Medico createMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    public Medico updateMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    public void deleteMedico(Long id) {
        medicoRepository.deleteById(id);
    }

    public List<Medico> getMedicosByEspecialidade(Especialidade especialidade) {
        return medicoRepository.findByEspecialidade(especialidade);
    }

    public List<Medico> getMedicosByNome(String nome) {
        return medicoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Medico> getMedicosByCrm(String crm) {
        return medicoRepository.findByCrmContainingIgnoreCase(crm);
    }
}