package br.com.alura.api_livros.repository;

import br.com.alura.api_livros.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT l FROM Livro l WHERE l.idioma LIKE '%'|| :idioma ||'%'")
    List<Livro> findLivrosPorIdioma(String idioma);
}
