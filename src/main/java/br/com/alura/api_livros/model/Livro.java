package br.com.alura.api_livros.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "livros")

public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "livro_autor",
    joinColumns = @JoinColumn(name = "livro_id"),
    inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private List<Autor> autor;

    private String idioma;
    private Double numeroDeDownloads;



    public Livro() {}


    public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.titulo();
        this.autor = dadosLivro.autores().stream()
                .map(a -> new Autor(a.nome(), a.anoDeNascimento(), a.anoDeFalecimento()))
                .collect(Collectors.toList());
        this.idioma = dadosLivro.idioma().toString();
        this.numeroDeDownloads = dadosLivro.numeroDeDownloads();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutor() {
        return autor;
    }

    public void setAutor(List<Autor> autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDeDownloads() {
        return numeroDeDownloads;
    }

    public void setNumeroDeDownloads(Double numeroDeDownloads) {
        this.numeroDeDownloads = numeroDeDownloads;
    }

    public List<String> getNomesAutores() {
        return autor.stream()
                .map(Autor::getNome)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return
                " titulo='" + titulo + '\'' +
                ", autor='" + getNomesAutores() + '\'' +
                ", idioma='" + idioma + '\'' +
                ", numeroDeDownloads=" + numeroDeDownloads;
    }
}
