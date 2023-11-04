package br.com.encurtador.exception;

import br.com.encurtador.utils.MessageUtils;

public class NotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public NotFoundException() {
		super(MessageUtils.NAO_ENCONTRADO);
	}

}
