package com.miro.socialbooks.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.miro.socialbooks.domain.Autor;
import com.miro.socialbooks.services.AutorServices;

@RestController
@RequestMapping("/autores")
public class AutorResources {
	
	@Autowired
	private AutorServices _autorServices;
	
	/**
	 * Retorna Lista os Autores
	 * 
	 * @return ResponseEntity<List<Autor>>
	 */
	@GetMapping
	public ResponseEntity<List<Autor>> listar() 
	{
		return ResponseEntity.status(HttpStatus.OK).body( _autorServices.listar() );
	}
	
	/**
	 * Retorna um Autor
	 * 
	 * @param id
	 * @return ResponseEntity<Autor>
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id)
	{
		Optional<Autor> autor = _autorServices.buscar(id);
		
		return ResponseEntity.status(HttpStatus.OK).body( autor );
	}
	
	/**
	 * Cadastra um Autor
	 * 
	 * @param Autor
	 * @return ResponseEntity<Void>
	 */
	@PostMapping
	public ResponseEntity<Void> salvar(@Valid @RequestBody Autor autor)
	{
		autor = _autorServices.salvar(autor);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(autor.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}

	/**
	 * Atualiza um Autor
	 * 
	 * @param id
	 * @param Autor
	 * @return ResponseEntity<Void>
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable("id") Long id, @Valid @RequestBody Autor autor) 
	{
		autor.setId(id);
		
		_autorServices.atualizar(autor);
		
		return ResponseEntity.noContent().build();
	}

	
	/**
	 * Remove um Autor
	 * 
	 * @param id
	 * @return ResponseEntity<Void>
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) 
	{
		_autorServices.deletar(id);
		
		return ResponseEntity.noContent().build();
	}
	
	

}
