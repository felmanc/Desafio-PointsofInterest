package br.com.felmanc.pointsofinterest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class TransacaoInvalidaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TransacaoInvalidaException(String mensagem) {
        super(mensagem);
    }
}
