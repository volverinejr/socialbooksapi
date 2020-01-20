package com.miro.socialbooks.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.miro.socialbooks.domain.DetalhesErro;
import com.miro.socialbooks.services.exceptions.AutorNaoEncontradoException;
import com.miro.socialbooks.services.exceptions.LivroNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(LivroNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handerLivroNaoEncontradoException(
			LivroNaoEncontradoException e, 
			HttpServletRequest request)
	{
		DetalhesErro erro = new DetalhesErro();
		
		erro.setStatus(404L);
		erro.setTitulo("O Livro não foi encontrado");
		erro.setMensagem("http://localhost:8080/404");
		erro.setTimestamp( System.currentTimeMillis() );
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(AutorNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handerAutorNaoEncontradoException(
			AutorNaoEncontradoException e, 
			HttpServletRequest request)
	{
		DetalhesErro erro = new DetalhesErro();
		
		erro.setStatus(404L);
		erro.setTitulo("O Autor não foi encontrado");
		erro.setMensagem("http://localhost:8080/404");
		erro.setTimestamp( System.currentTimeMillis() );
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetalhesErro> handerDataIntegrityViolationException(
			DataIntegrityViolationException e, 
			HttpServletRequest request)
	{
		DetalhesErro erro = new DetalhesErro();
		
		erro.setStatus(400L);
		erro.setTitulo("Requisição inválida!");
		erro.setMensagem("http://localhost:8080/400");
		erro.setTimestamp( System.currentTimeMillis() );
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}	

}
