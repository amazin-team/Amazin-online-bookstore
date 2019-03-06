package amazin;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = null;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String name;
    private String description;
    private int ISBN;
    private String picture;
    private String author;
    private String publisher;
    private int SKU;
    private int inventory;
    private double price;


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

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
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

    public int getSKU() {
        return SKU;
    }

    public void setSKU(int SKU) {
        this.SKU = SKU;
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


    public Book(){}

    public Book(String name, String description, int ISBN, String picture, String author, String publisher, int SKU, int inventory, double price) {
        this.name = name;
        this.description = description;
        this.ISBN = ISBN;
        this.picture = picture;
        this.author = author;
        this.publisher = publisher;
        this.SKU = SKU;
        this.inventory = inventory;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ISBN=" + ISBN +
                ", picture='" + picture + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", SKU='" + SKU + '\'' +
                ", inventory='" + inventory + '\'' +
                ", price='" + price + '\'' +
                '}';
    }


    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        launcher.launch();
    }

}
