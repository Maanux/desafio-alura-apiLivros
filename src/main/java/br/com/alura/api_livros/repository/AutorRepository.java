package br.com.alura.api_livros.repository;

import br.com.alura.api_livros.model.Autor;
import br.com.alura.api_livros.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE (a.anoDeFalecimento IS NULL OR a.anoDeFalecimento > :ano) AND a.anoDeNascimento <= :ano")
    List<Autor> findAutoresVivosEmAno(int ano);

}
