package com.senai.lab365.MiniProjeto.exceptions;

public class TelefoneInvalidoException extends RuntimeException {
    public TelefoneInvalidoException(String telefone) {
        super("O telefone '" + telefone + "' possui um formato inválido. Por favor, insira um número de telefone válido.");
    }
}