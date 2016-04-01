package main.java.SAXParser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Hasmik.Ghazaryan on 3/31/2016.
 */
public class Book {

    private String id;
    private String lang;
    private String title;
    private String isbn;
    private Date regDate;
    private String publisher;
    private Integer price;
    private List<String> authors;

    public Book(String id, String lang) {
        this.id = id;
        this.lang = lang;
        this.authors = new ArrayList<String>();
    }

    public Book(){
        authors = new ArrayList<String>();
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", lang='" + lang + '\'' +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", regDate=" + regDate +
                ", publisher='" + publisher + '\'' +
                ", price=" + price +
                ", authors=" + authors +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
}
