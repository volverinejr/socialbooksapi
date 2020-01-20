package com.miro.socialbooks.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.miro.socialbooks.domain.Comentario;
import com.miro.socialbooks.domain.Livro;
import com.miro.socialbooks.services.LivrosServices;

@RestController
@RequestMapping("/livros")
public class LivrosResources {
	
	@Autowired
	private LivrosServices _livrosServices;
	
	
	@GetMapping
	public ResponseEntity<List<Livro>> listar() 
	{
		return ResponseEntity.status(HttpStatus.OK).body( _livrosServices.listar() );
	}
	
	
	@GetMapping("/{id}")
	public  ResponseEntity<?> buscar(@PathVariable("id") Long id) 
	{
		Optional<Livro> livro = _livrosServices.buscar(id);
		
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.MINUTES);
		
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(livro);
	}
	
	
	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody Livro livro)
	{
		livro = _livrosServices.salvar(livro);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(livro.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable("id") Long id, @RequestBody Livro livro) 
	{
		livro.setId(id);
		
		_livrosServices.atualizar(livro);
		
		return ResponseEntity.noContent().build();
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) 
	{
		_livrosServices.deletar(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/{id}/comentarios")
	public ResponseEntity<Void> adicionarComentario(@PathVariable("id") Long livroId, @RequestBody Comentario comentario)
	{
		_livrosServices.salvarComentario(livroId, comentario);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.build()
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}/comentarios")
	public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable("id") Long livroId)
	{
		List<Comentario> comentarios = _livrosServices.listarComentarios(livroId);
		
		return ResponseEntity.status(HttpStatus.OK).body(comentarios);
	}
	

}
