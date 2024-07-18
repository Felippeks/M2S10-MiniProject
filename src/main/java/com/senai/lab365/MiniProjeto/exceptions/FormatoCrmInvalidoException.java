package com.senai.lab365.MiniProjeto.exceptions;

public class FormatoCrmInvalidoException extends RuntimeException {
    public FormatoCrmInvalidoException(String crm) {
        super("O CRM '" + crm + "' possui um formato inválido. Por favor, insira um CRM válido.");
    }
}
