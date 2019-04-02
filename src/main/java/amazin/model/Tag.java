//--------------------------------------------------------------------------------
//
// This file defines the tag entity that is used for finding user book
// recommendations and store tags for a specific book.
//
//--------------------------------------------------------------------------------
package amazin.model;

//--------------------------------------------------------------------------------
import java.util.Set;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import javax.persistence.*;

//--------------------------------------------------------------------------------
@Entity
@Indexed
@Table(name="tags")
public class Tag {

    @Id
    @Field
    @Column(name = "tag_id", updatable = false, nullable = false)
    private String id;

    @ManyToMany(mappedBy = "tags")
    private Set<User> users;

    @ManyToMany(mappedBy = "tags")
    private Set<Book> books;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
