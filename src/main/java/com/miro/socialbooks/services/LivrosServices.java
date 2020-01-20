package com.miro.socialbooks.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.miro.socialbooks.domain.Comentario;
import com.miro.socialbooks.domain.Livro;
import com.miro.socialbooks.repository.ComentarioRepository;
import com.miro.socialbooks.repository.LivroRepository;
import com.miro.socialbooks.services.exceptions.LivroNaoEncontradoException;

@Service
public class LivrosServices {
	@Autowired
	private LivroRepository _livroRepository;
	
	@Autowired
	private ComentarioRepository _comentarioRepository;
	
	public List<Livro> listar()
	{
		return _livroRepository.findAll();
	}
	
	
	public Optional<Livro> buscar(Long id)
	{
		Optional<Livro>  livro = _livroRepository.findById(id);
		
		if ( !livro.isPresent() )
		{
			throw new LivroNaoEncontradoException("Livro não foi encontrado");
		}
		
		return livro;
	}
	
	public Livro salvar(Livro livro)
	{
		livro.setId(null);
		
		return _livroRepository.save(livro);
	}
	
	public void deletar(Long id)
	{
		try {
			_livroRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new LivroNaoEncontradoException("Livro não foi encontrado");
		}
	}
	
	public void atualizar(Livro livro)
	{
		this.verificarExistencia( livro );
		
		_livroRepository.save(livro);
	}
	
	
	private void verificarExistencia(Livro livro)
	{
		this.buscar(livro.getId());
	}
	
	
	public Comentario salvarComentario(Long livroId, Comentario comentario)
	{
		Livro livro = this.buscar(livroId).get();
		
		comentario.setLivro(livro);
		comentario.setData( new Date() );
		
		return _comentarioRepository.save(comentario);
	}
	
	
	public List<Comentario> listarComentarios(Long livroId)
	{
		Livro livro = this.buscar(livroId).get();
		
		return livro.getComentarios();
	}
	

}
