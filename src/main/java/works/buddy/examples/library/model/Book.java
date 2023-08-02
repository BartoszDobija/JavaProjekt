package works.buddy.examples.library.model;

public class Book {

    private Integer id;

    private String title;

    private String author;

    private String genre;

    private Integer releaseDate;

    private String content = "Lorem Ipsum itd itp tutaj tresc ksiazki";

    public Book() {

    }

    public Book(Integer id, String title, String author, String genre, int releaseDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.releaseDate = releaseDate;
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

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
