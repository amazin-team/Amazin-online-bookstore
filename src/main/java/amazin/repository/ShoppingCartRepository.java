package amazin.repository;

import amazin.model.ShoppingCart;
import amazin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by lauramachado on 2019-04-02.
 */
@RepositoryRestResource(collectionResourceRel = "cart", path = "cart.json")
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findByUser(User u);
}

