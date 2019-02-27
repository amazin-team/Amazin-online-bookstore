package amazin;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id = null;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String name;
    private String description;
    private Integer ISBN;
    private String picture_path;
    private String author;
    private String publisher;
    private String sku;
    private String inventory;
    private String manufacter_price;
    private String retail_price;


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

    public Integer getISBN() {
        return ISBN;
    }

    public void setISBN(Integer ISBN) {
        this.ISBN = ISBN;
    }

    public String getPicture_path() {
        return picture_path;
    }

    public void setPicture_path(String picture_path) {
        this.picture_path = picture_path;
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public String getManufacter_price() {
        return manufacter_price;
    }

    public void setManufacter_price(String manufacter_price) {
        this.manufacter_price = manufacter_price;
    }

    public String getRetail_price() {
        return retail_price;
    }

    public void setRetail_price(String retail_price) {
        this.retail_price = retail_price;
    }

    public Book(){}

    public Book(Integer id, String name, String description, Integer ISBN, String picture_path, String author, String publisher, String sku, String inventory, String manufacter_price, String retail_price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ISBN = ISBN;
        this.picture_path = picture_path;
        this.author = author;
        this.publisher = publisher;
        this.sku = sku;
        this.inventory = inventory;
        this.manufacter_price = manufacter_price;
        this.retail_price = retail_price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ISBN=" + ISBN +
                ", picture_path='" + picture_path + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", sku='" + sku + '\'' +
                ", inventory='" + inventory + '\'' +
                ", manufacter_price='" + manufacter_price + '\'' +
                ", retail_price='" + retail_price + '\'' +
                '}';
    }


    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        launcher.launch();
    }

}
