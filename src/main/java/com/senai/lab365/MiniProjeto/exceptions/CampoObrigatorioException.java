package com.senai.lab365.MiniProjeto.exceptions;

import java.util.List;

public class CampoObrigatorioException extends RuntimeException {
    private List<String> camposFaltantes;

    public CampoObrigatorioException(String mensagem, List<String> camposFaltantes) {
        super(mensagem);
        this.camposFaltantes = camposFaltantes;
    }

    public List<String> getCamposFaltantes() {
        return camposFaltantes;
    }
}