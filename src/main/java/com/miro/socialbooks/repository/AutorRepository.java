package com.miro.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miro.socialbooks.domain.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

}
