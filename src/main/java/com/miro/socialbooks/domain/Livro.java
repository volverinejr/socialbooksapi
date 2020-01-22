package com.miro.socialbooks.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity(name="Livro")
public class Livro {
	
	@Id
	@JsonInclude(Include.NON_NULL)
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name="increment", strategy="increment")
	private Long id;
	
	@NotEmpty(message = "Campo Obrigatório")
	@JsonInclude(Include.NON_NULL)
	private String nome;
	
	
	@NotNull(message = "Campo Obrigatório")
	@JsonInclude(Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date publicacao;
	
	@NotEmpty(message = "Campo Obrigatório")
	@JsonInclude(Include.NON_NULL)
	private String editora;
	
	@NotEmpty(message = "Campo Obrigatório")
	@JsonInclude(Include.NON_NULL)
	@Size(max = 1500, message = "tamanha máximo 1500 caracteres")
	@Length(min = 10)
	private String resumo;
	
	
	@JsonInclude(Include.NON_NULL)
	@OneToMany(mappedBy = "livro")
	// @Transient
	private List<Comentario> comentarios;
	
	
	@ManyToOne()
	@JoinColumn(name = "autor_id")
	@JsonInclude(Include.NON_NULL)
	private Autor autor;
	
	
	public Livro() {}
	
	public Livro(String nome) {
		this.nome = nome;
	}
	


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Date getPublicacao() {
		return publicacao;
	}


	public void setPublicacao(Date publicacao) {
		this.publicacao = publicacao;
	}


	public String getEditora() {
		return editora;
	}


	public void setEditora(String editora) {
		this.editora = editora;
	}


	public String getResumo() {
		return resumo;
	}


	public void setResumo(String resumo) {
		this.resumo = resumo;
	}


	public List<Comentario> getComentarios() {
		return comentarios;
	}


	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}


	public Autor getAutor() {
		return autor;
	}


	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	

	
	
}
