package com.senai.lab365.MiniProjeto.repositorys;

import com.senai.lab365.MiniProjeto.models.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senai.lab365.MiniProjeto.models.Medico;

import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    List<Medico> findByEspecialidade(Especialidade especialidade);
    List<Medico> findByNomeContainingIgnoreCase(String nome);
    List<Medico> findByCrmContainingIgnoreCase(String crm);
}