package works.buddy.library.model;

public class Book {

    private Integer id;

    private String title;

    private String author;

    private String genre;

    private Integer releaseYear;

    public Book() {

    }

    public Book(Integer id, String title, String author, String genre, int releaseYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

}
