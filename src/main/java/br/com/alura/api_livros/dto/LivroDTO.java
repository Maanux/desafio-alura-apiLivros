package br.com.alura.api_livros.dto;

import br.com.alura.api_livros.model.Autor;

import java.util.List;

public record LivroDTO(List<Autor> autor, String idioma, Double numeorDeDowloads, String titulo) {
}
