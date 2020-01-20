package com.miro.socialbooks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.miro.socialbooks.domain.Autor;
import com.miro.socialbooks.repository.AutorRepository;
import com.miro.socialbooks.services.exceptions.AutorNaoEncontradoException;

@Service
public class AutorServices {
	
	@Autowired
	private AutorRepository _autorRepository;
	
	
	public List<Autor> listar()
	{
		return _autorRepository.findAll();
	}
	
	
	public Optional<Autor> buscar(Long id) 
	{
		Optional<Autor> autor = _autorRepository.findById(id);
		
		if (!autor.isPresent()) 
		{
			throw new AutorNaoEncontradoException("Autor não foi encontrado");
		}
		
		return autor;
	}
	
	
	public Autor salvar(Autor autor)
	{
		autor.setId(null);
		
		return _autorRepository.save(autor);
	}	
	
	
	public void deletar(Long id)
	{
		try {
			_autorRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new AutorNaoEncontradoException("Autor não foi encontrado");
		}
	}
	
	public void atualizar(Autor autor)
	{
		this.verificarExistencia( autor );
		
		_autorRepository.save(autor);
	}
	
	
	private void verificarExistencia(Autor autor)
	{
		this.buscar(autor.getId());
	}
	
	
	
	

}
