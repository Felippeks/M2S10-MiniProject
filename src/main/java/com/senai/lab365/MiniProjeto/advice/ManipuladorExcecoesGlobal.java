package com.senai.lab365.MiniProjeto.advice;

import com.senai.lab365.MiniProjeto.exceptions.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.WebRequest;
import com.senai.lab365.MiniProjeto.exceptions.FormatoCrmInvalidoException;
import com.senai.lab365.MiniProjeto.exceptions.DataNascimentoInvalidaException;
import com.senai.lab365.MiniProjeto.exceptions.TelefoneInvalidoException;
import com.senai.lab365.MiniProjeto.exceptions.EspecialidadeNaoEncontradaException;
import com.senai.lab365.MiniProjeto.exceptions.AcessoNegadoException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ManipuladorExcecoesGlobal {

    @ExceptionHandler(CampoObrigatorioException.class)
    public ResponseEntity<Map<String, Object>> handleCampoObrigatorioException(CampoObrigatorioException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("mensagem", ex.getMessage());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("camposFaltantes", ex.getCamposFaltantes());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, Object> body = new HashMap<>();
        String errors = ex.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining(", "));
        body.put("mensagem", "Erro de validação");
        body.put("detalhes", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        Map<String, Object> body = new HashMap<>();
        body.put("mensagem", "Erro de validação");
        body.put("detalhes", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("mensagem", "Entidade não encontrada");
        body.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(DataAccessException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("mensagem", "Erro de acesso a dados");
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("mensagem", "Método HTTP não suportado");
        body.put("status", HttpStatus.METHOD_NOT_ALLOWED.value());
        return new ResponseEntity<>(body, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MedicoNaoEncontradoException.class)
    public ResponseEntity<Object> handleMedicoNaoEncontradoException(MedicoNaoEncontradoException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("mensagem", ex.getMessage());
        body.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CrmJaCadastradoException.class)
    public ResponseEntity<Object> handleCrmJaCadastradoException(CrmJaCadastradoException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("mensagem", ex.getMessage());
        body.put("status", HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(FormatoCrmInvalidoException.class)
    public ResponseEntity<Object> handleFormatoCrmInvalidoException(FormatoCrmInvalidoException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("mensagem", ex.getMessage());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataNascimentoInvalidaException.class)
    public ResponseEntity<Object> handleDataNascimentoInvalidaException(DataNascimentoInvalidaException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("mensagem", ex.getMessage());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TelefoneInvalidoException.class)
    public ResponseEntity<Object> handleTelefoneInvalidoException(TelefoneInvalidoException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("mensagem", ex.getMessage());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EspecialidadeNaoEncontradaException.class)
    public ResponseEntity<Object> handleEspecialidadeNaoEncontradaException(EspecialidadeNaoEncontradaException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("mensagem", ex.getMessage());
        body.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AcessoNegadoException.class)
    public ResponseEntity<Object> handleAcessoNegadoException(AcessoNegadoException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("mensagem", ex.getMessage());
        body.put("status", HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }

}