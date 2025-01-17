package br.com.alura.api_livros.principal;

import br.com.alura.api_livros.model.Autor;
import br.com.alura.api_livros.model.DadosLivro;
import br.com.alura.api_livros.model.Livro;
import br.com.alura.api_livros.model.ResultadoPesquisa;
import br.com.alura.api_livros.repository.AutorRepository;
import br.com.alura.api_livros.repository.LivroRepository;
import br.com.alura.api_livros.service.ConsumoApi;
import br.com.alura.api_livros.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@Service
public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private  String endereco = "https://gutendex.com/books/?search=";
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();

    private List<Livro> livros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();

    private  LivroRepository repositorioLivro;

    private AutorRepository repositoryAutor;
    public Principal(LivroRepository repositorioLivro, AutorRepository repositoryAutor) {
        this.repositorioLivro = repositorioLivro;
        this.repositoryAutor = repositoryAutor;
    }

    public void exibirMenu() throws IOException, InterruptedException {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                         Escolha o número de sua opção:
                         1 - Buscar livro pelo título
                         2 - Listar livros registrados
                         3 - Listar autores Registrados
                         4 - Listar autores vivos em um deterniado ano
                         5 - Listar livros em um determinado idioma

                         0 - Sair
                        """;
            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    buscarLivroPeloTitulo();
                    break;
                case 2:
                    obterLivrosRegistrados();
                    break;
                case 3:
                    obterAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEmUmDeterminadoAno();
                    break;
                case 5:
                    listarLivroPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
            }
        }
    }

    private void listarLivroPorIdioma() {
        System.out.println("Digite um idioma de preferencia(es,en,fr,pt;");
        var idioma = scanner.nextLine();
        System.out.println(idioma);
        livros = repositorioLivro.findLivrosPorIdioma(idioma);
        System.out.println(livros);
        livros.stream()
                .sorted(Comparator.comparing(Livro::getTitulo))
                .forEach(System.out::println);
    }

    private void listarAutoresVivosEmUmDeterminadoAno() {
        System.out.println("Digite o ano para listar os Autores: ");
        var ano = scanner.nextInt();
        autores = repositoryAutor.findAutoresVivosEmAno(ano);
        System.out.println(ano);
        autores.stream()
                .sorted(Comparator.comparing(Autor::getNome))
                .forEach(System.out::println);
    }

    private void obterAutoresRegistrados() {
        autores = repositoryAutor.findAll();
        autores.stream()
                .sorted(Comparator.comparing(Autor::getId))
                .forEach(System.out::println);
    }

    private void obterLivrosRegistrados() {
        livros = repositorioLivro.findAll();
        livros.stream()
                .sorted(Comparator.comparing(Livro::getTitulo))
                .forEach(System.out::println);
    }

    private DadosLivro getObterDadosTitulo() throws IOException, InterruptedException {
        System.out.println("Digite o Titulo do Livro");

        var nomeLivro = scanner.nextLine();
        System.out.println(endereco + nomeLivro);

        var json = consumo.obterDados(endereco + nomeLivro.replace(" ", "+"));

        ResultadoPesquisa dados = converteDados.obterDados(json, ResultadoPesquisa.class);

        DadosLivro dadosLivro = dados.livros().get(0);

        return dadosLivro;
    }

    private void buscarLivroPeloTitulo() throws IOException, InterruptedException {
        DadosLivro dadosLivro = getObterDadosTitulo();

        Livro livro = new Livro(dadosLivro);

        repositorioLivro.save(livro);
        dadosLivro.autores().forEach(System.out::println);
        System.out.println(livro);

    }

}
