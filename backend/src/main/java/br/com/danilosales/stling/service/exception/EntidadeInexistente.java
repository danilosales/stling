package br.com.danilosales.stling.service.exception;

public class EntidadeInexistente extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EntidadeInexistente(String mensagem) {
		super(mensagem);
	}

}
