package com.senai.lab365.MiniProjeto.exceptions;

public class EspecialidadeNaoEncontradaException extends RuntimeException {
    public EspecialidadeNaoEncontradaException(String especialidade) {
        super("A especialidade '" + especialidade + "' não foi encontrada. Por favor, selecione uma especialidade válida.");
    }
}