//--------------------------------------------------------------------------------
//
// This file defines the tag entity that is used for finding user book
// recommendations and store tags for a specific book.
//
//--------------------------------------------------------------------------------
package amazin.model;

//--------------------------------------------------------------------------------
import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import javax.persistence.*;

//--------------------------------------------------------------------------------
@Entity
@Table(name="tags")
public class Tag {

    @Id
    @Column(name = "tag_id", updatable = true, nullable = false)
    private String id;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tags")
    private Set<User> users;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tags")
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

    public Tag() {
        books = new HashSet<>();
        users = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Tag{" + "id=" + id + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Tag)) {
            return false;
        }
        Tag tag = (Tag) o;
        return id.equals(tag.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
