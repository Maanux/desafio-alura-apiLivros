package br.com.alura.api_livros.service;

public interface IConverteDados {
    <T> T  obterDados(String json, Class<T> classe);
}