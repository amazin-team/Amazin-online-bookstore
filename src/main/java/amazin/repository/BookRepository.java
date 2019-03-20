package amazin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import amazin.model.Book;

@RepositoryRestResource(collectionResourceRel = "book", path = "book.json")
public interface BookRepository extends JpaRepository<Book, Long> {
}
