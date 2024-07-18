package com.senai.lab365.MiniProjeto.exceptions;

public class MedicoNaoEncontradoException extends RuntimeException {
    public MedicoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}