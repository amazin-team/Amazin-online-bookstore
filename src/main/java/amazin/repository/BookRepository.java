package amazin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import amazin.model.Book;
import amazin.model.Tag;

@RepositoryRestResource(collectionResourceRel = "book", path = "book.json")
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findDistinctByTags(Tag tag);
}
