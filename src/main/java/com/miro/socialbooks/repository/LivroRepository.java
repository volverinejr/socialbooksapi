package com.miro.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miro.socialbooks.domain.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
