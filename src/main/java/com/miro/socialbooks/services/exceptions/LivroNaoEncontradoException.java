package com.miro.socialbooks.services.exceptions;

public class LivroNaoEncontradoException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8147459428764816369L;


	public LivroNaoEncontradoException(String mensagem) 
	{
		super(mensagem);
	}
	
	
	public LivroNaoEncontradoException(String mensagem, Throwable causa)
	{
		super(mensagem, causa);
	}
	
}
