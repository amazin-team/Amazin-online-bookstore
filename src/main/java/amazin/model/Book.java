package amazin.model;

import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = null;
    @NotNull
    @Size(min = 2, message = "Book name should have at least 2 characters")
    private String name;
    @NotNull
    @Size(min = 2, message = "Book description should have at least 2 characters")
    private String description;
    @NotNull
    @Size(min = 13, max = 13, message = "ISBN should be 13 characters long") // ISBN is a 13 digit number
    private String ISBN;
    private String picture_url;
    @NotNull
    @Size(min = 2, message = "Book author should have at least 2 characters")
    private String author;
    @NotNull
    @Size(min = 1, message = "Book publisher should have at least 2 characters")
    private String publisher;
    @Min(0)
    private int inventory;
    @Min(0)
    private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture) {
        this.picture_url = picture;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Book() {
    }

    public Book(Long id, String name, String description, String ISBN, String picture_url, String author, String publisher,
                int inventory, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ISBN = ISBN;
        this.picture_url = picture_url;
        this.author = author;
        this.publisher = publisher;
        this.inventory = inventory;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + ", ISBN="
                + ISBN + ", picture='" + picture_url + '\'' + ", author='" + author + '\'' + ", publisher='" + publisher
                + '\'' + ", inventory='" + inventory + '\'' + ", price='" + price + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Book)) {
            return false;
        }
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(name, book.name)
                && Objects.equals(description, book.description) && Objects.equals(ISBN, book.ISBN)
                && Objects.equals(picture_url, book.picture_url) && Objects.equals(author, book.author)
                && Objects.equals(publisher, book.publisher) && inventory == book.inventory && price == book.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, ISBN, picture_url, author, publisher, inventory, price);
    }

}
