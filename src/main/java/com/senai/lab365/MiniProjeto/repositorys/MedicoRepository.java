package com.senai.lab365.MiniProjeto.repositorys;

import com.senai.lab365.MiniProjeto.models.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senai.lab365.MiniProjeto.models.Medico;

import java.time.LocalDate;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    Page<Medico> findByEspecialidade(Especialidade especialidade, Pageable pageable);
    Page<Medico> findByDataNascimento(LocalDate dataNascimento, Pageable pageable);
    Page<Medico> findByNomeContainingIgnoreCaseAndEspecialidade(String nome, Especialidade especialidade, Pageable pageable);
    Page<Medico> findByNomeContainingIgnoreCaseAndDataNascimento(String nome, LocalDate dataNascimento, Pageable pageable);
    Page<Medico> findByEspecialidadeAndDataNascimento(Especialidade especialidade, LocalDate dataNascimento, Pageable pageable);
    Page<Medico> findByNomeContainingIgnoreCaseAndEspecialidadeAndDataNascimento(String nome, Especialidade especialidade, LocalDate dataNascimento, Pageable pageable);
    Page<Medico> findByCrmContainingIgnoreCase(String crm, Pageable pageable);
}