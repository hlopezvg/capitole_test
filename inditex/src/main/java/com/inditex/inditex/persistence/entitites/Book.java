package com.inditex.inditex.persistence.entitites;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String title;
    private String author;
    @Column(unique = true)
    private String isbn;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Page> pageSet;

    public Book() {
    }

    public Book(String title, String author, String isbn) {
        this.setTitle(title);
        this.setAuthor(author);
        this.setIsbn(isbn);
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Set<Page> getPageEntitySet() {
        return pageSet;
    }

    public void setPageEntitySet(Set<Page> pageSet) {
        this.pageSet = pageSet;
    }
}
