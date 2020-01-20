package com.miro.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miro.socialbooks.domain.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

}
