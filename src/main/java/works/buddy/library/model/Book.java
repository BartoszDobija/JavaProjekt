package works.buddy.library.model;

import java.util.Objects;

public class Book {

    private Integer id;

    private String title;

    private String author;

    private String genre;

    private Integer releaseYear;

    public Book() {
    }

    public Book(String title, String author, String genre, int releaseYear) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return Objects.equals(getId(), book.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
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

    @Override
    public String toString() {
        return String.format("%s by %s, %s originally published in [%s]", this.title, this.author, this.genre, this.releaseYear);
    }
}
