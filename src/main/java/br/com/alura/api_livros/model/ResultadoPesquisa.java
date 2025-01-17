package br.com.alura.api_livros.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
@JsonIgnoreProperties(ignoreUnknown = true)
public record ResultadoPesquisa(@JsonAlias("results")ArrayList<DadosLivro> livros) {
}
