//package br.com.alura.api_livros.service;
//
//import br.com.alura.api_livros.dto.LivroDTO;
//import br.com.alura.api_livros.model.Livro;
//import br.com.alura.api_livros.repository.LivroRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class LivroService {
//    @Autowired
//    private LivroRepository repositorio;
//
//
//    public List<LivroDTO> obterTodosOsLivros(){
//        return converteDados(repositorio.findAll());
//
//    }
//    public List<LivroDTO> converteDados(List<Livro> livro) {
//        return livro.stream()
//                .map(l -> new LivroDTO(l.getAutor(),  l.getIdioma(),  l.getNumeroDeDownloads(), l.getTitulo()))
//                .collect(Collectors.toList());
//    }
//
//
//}
///*series.stream()
//                .map(s -> new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse()))
//                .collect(Collectors.toList());*/