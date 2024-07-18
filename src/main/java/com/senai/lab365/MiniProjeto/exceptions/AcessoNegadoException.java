package com.senai.lab365.MiniProjeto.exceptions;

public class AcessoNegadoException extends RuntimeException {
    public AcessoNegadoException() {
        super("Você não tem permissão para realizar esta ação.");
    }
}