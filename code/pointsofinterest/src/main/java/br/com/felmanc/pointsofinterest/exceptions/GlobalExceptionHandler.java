package br.com.felmanc.pointsofinterest.exceptions;

import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestControllerAdvice
@Tag(name = "Erros Globais", description = "Documentação de erros comuns da API")
public class GlobalExceptionHandler {

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<Object> handleMissingRequestParameter(MissingServletRequestParameterException ex) {
	    return ResponseEntity
	            .status(HttpStatus.BAD_REQUEST)
	            .body(Map.of(
	                    "error", "BAD_REQUEST",
	                    "message", String.format("O parâmetro '%s' é obrigatório e não foi fornecido.", ex.getParameterName()),
	                    "details", ex.getMessage()
	            ));
	}
		
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleJsonParseException(HttpMessageNotReadableException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "error", "BAD_REQUEST",
                        "message", "O JSON enviado não pôde ser lido. Verifique a formatação da requisição.",
                        "details", ex.getMessage()
                ));
    }
	
    @ExceptionHandler(TransacaoInvalidaException.class)
    public ResponseEntity<Object> handleTransacaoInvalida(TransacaoInvalidaException ex) {
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(Map.of(
                        "error", "UNPROCESSABLE_ENTITY",
                        "message", "A transação enviada é inválida. Consulte os critérios necessários.",
                        "details", ex.getMessage()
                ));
    }	
	
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "error", "INTERNAL_SERVER_ERROR",
                        "message", "Erro interno do servidor. Por favor, tente novamente mais tarde.",
                        "details", "Um erro inesperado ocorreu."
                ));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of(
                        "error", "CONFLICT",
                        "message", "Erro de duplicação de entrada. Por favor, verifique os dados fornecidos.",
                        "details", "Um erro de duplicação de dados ocorreu."
                ));
    }
}

