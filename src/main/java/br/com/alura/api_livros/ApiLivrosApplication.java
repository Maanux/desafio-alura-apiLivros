package br.com.alura.api_livros;

import br.com.alura.api_livros.principal.Principal;
import br.com.alura.api_livros.repository.AutorRepository;
import br.com.alura.api_livros.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiLivrosApplication implements CommandLineRunner {
	@Autowired
	private  LivroRepository repositorioLivro;
	@Autowired
	private AutorRepository repositoryAutor;
	public static void main(String[] args) {
		SpringApplication.run(ApiLivrosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorioLivro, repositoryAutor);

		principal.exibirMenu();
	}
}
