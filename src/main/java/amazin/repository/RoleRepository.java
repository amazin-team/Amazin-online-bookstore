package amazin.repository;

import amazin.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

@RepositoryRestResource(collectionResourceRel = "role", path = "role.json")
public interface RoleRepository extends CrudRepository<Role, Long> {
    Set<Role> findByName(String name);
    Set<Role> findAll();
}
