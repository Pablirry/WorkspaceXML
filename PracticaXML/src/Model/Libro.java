package Model;

public class Libro {
    private String id;
    private String titulo;
    private String genero;
    private String ano;
    private String autor;

    public Libro(String id, String titulo, String genero, String ano, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.ano = ano;
        this.autor = autor;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public String getAno() {
        return ano;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", ano='" + ano + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }
}