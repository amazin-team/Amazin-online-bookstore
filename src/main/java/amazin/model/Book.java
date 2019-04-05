package amazin.model;

import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

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
    private String picture_url;
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
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "book_tags",
               joinColumns = @JoinColumn(name = "book_id"),
               inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

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

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;

        // The tags need to reference the books associated
        // to build the join table properly.
        for(Tag tag: tags) {
            tag.getBooks().add(this);
        }
    }

    public boolean addTag(Tag tag) {
        tag.getBooks().add(this);
        return tags.add(tag);
    }

    public boolean removeTag(Tag tag) {
        tag.getBooks().remove(this);
        return tags.remove(tag);
    }

    public Book() {
        tags = new HashSet<>();
    }

    public Book(Long id, String name, String description, String ISBN, String picture_url, String author, String publisher,
                int inventory, double price, Set<Tag> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ISBN = ISBN;
        this.picture_url = picture_url;
        this.author = author;
        this.publisher = publisher;
        this.inventory = inventory;
        this.price = price;
        this.tags = tags;
    }

    @Override
    public String toString() {
        String returnString = "Book{" + "id=" + id + ", name='" + name + '\'' + ", description='"
                               + description + '\'' + ", tags='[";

        for (Tag tag: tags) {
            returnString += tag.toString() + ", ";
        }

        if (tags.size() > 0)
            returnString = returnString.substring(0, returnString.length() - 2);

        returnString += "]'";

        return returnString + '\'' +  ", ISBN=" + ISBN + ", picture='" + picture_url + '\''
                + ", author='" + author + '\'' + ", publisher='" + publisher
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
                && Objects.equals(publisher, book.publisher) && inventory == book.inventory && price == book.price
                && Objects.equals(tags, book.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, tags, ISBN,
                            picture_url, author, publisher, inventory, price);
    }

}
