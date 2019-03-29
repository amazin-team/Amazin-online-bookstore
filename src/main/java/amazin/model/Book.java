package amazin.model;

import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name = "book")
public class Book {
    public static final String BOOK_NAME_FIELD = "name";
    public static final String BOOK_DESCRIPTION_FIELD = "description";
    public static final String BOOK_ISBN_FIELD = "ISBN";
    public static final String BOOK_AUTHOR_FIELD = "author";
    public static final String BOOK_PUBLISHER_FIELD = "publisher";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id", updatable = false, nullable = false)
    private Long id;
    @NotNull
    @Size(min = 2, message = "Book name should have at least 2 characters")
    @Column(name = "book_name")
    @Field
    private String name;
    @NotNull
    @Size(min = 2, message = "Book description should have at least 2 characters")
    @Column(name = "book_description")
    @Field
    private String description;
    @NotNull
    @Size(min = 13, max = 13, message = "ISBN should be 13 characters long") // ISBN is a 13 digit number
    @Column(name = "book_isbn")
    @Field
    private String ISBN;
    @Column(name = "book_picture")
    @Field
    private String picture;
    @NotNull
    @Size(min = 2, message = "Book author should have at least 2 characters")
    @Column(name = "book_author")
    @Field
    private String author;
    @NotNull
    @Size(min = 1, message = "Book publisher should have at least 2 characters")
    @Column(name = "book_publisher")
    @Field
    private String publisher;
    @Min(0)
    @Column(name = "book_inventory")
    @Field
    private int inventory;
    @Min(0)
    @Column(name = "book_price")
    @Field
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public Book(Long id, String name, String description, String ISBN, String picture, String author, String publisher,
            int inventory, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ISBN = ISBN;
        this.picture = picture;
        this.author = author;
        this.publisher = publisher;
        this.inventory = inventory;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + ", ISBN="
                + ISBN + ", picture='" + picture + '\'' + ", author='" + author + '\'' + ", publisher='" + publisher
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
                && Objects.equals(picture, book.picture) && Objects.equals(author, book.author)
                && Objects.equals(publisher, book.publisher) && inventory == book.inventory && price == book.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, ISBN, picture, author, publisher, inventory, price);
    }

}
