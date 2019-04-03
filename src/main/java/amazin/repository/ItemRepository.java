package amazin.repository;

import amazin.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by lauramachado on 2019-04-02.
 */
@RepositoryRestResource(collectionResourceRel = "item", path = "item.json")
public interface ItemRepository extends JpaRepository<Item, Long> {
}
