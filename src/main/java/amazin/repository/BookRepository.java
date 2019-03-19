package amazin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import amazin.model.Book;

@RepositoryRestResource(collectionResourceRel = "book", path = "book.json")
public interface BookRepository extends CrudRepository<Book, Long> {
}
