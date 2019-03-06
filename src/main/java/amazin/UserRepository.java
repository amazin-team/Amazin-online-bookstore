package amazin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "user", path = "user.json")
public interface UserRepository extends CrudRepository<User, Long> {
}
