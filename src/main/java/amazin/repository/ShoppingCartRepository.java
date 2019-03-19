package amazin.repository;

import amazin.model.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by lauramachado on 2019-03-19.
 */
@RepositoryRestResource(collectionResourceRel = "cart", path = "cart.json")
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {
}
