package br.com.encurtador.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.encurtador.entity.UrlEncurtada;

@Repository
public interface UrlRepository extends JpaRepository<UrlEncurtada, Long> {

	Optional<UrlEncurtada> findByUrlEncurtada(String urlEncurtada);

}
