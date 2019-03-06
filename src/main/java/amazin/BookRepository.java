package amazin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "book", path = "book.json")
public interface BookRepository extends CrudRepository<Book, Long> {
}
