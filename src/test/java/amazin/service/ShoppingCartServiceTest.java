package amazin.service;

import amazin.BookTestUtil;
import amazin.model.Book;
import amazin.model.Item;
import amazin.model.ShoppingCart;
import amazin.model.User;
import amazin.repository.BookRepository;
import amazin.repository.ShoppingCartRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by lauramachado on 2019-04-03.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ShoppingCartServiceTest {

    @InjectMocks
    ShoppingCartService service;

    @Mock
    ShoppingCartRepository repository;

    private static final long ID = 1L;

    @Test
    public void createTest() {

        User u = new User();
        ShoppingCart cart = service.createCart(u);

        ArgumentCaptor<ShoppingCart> cartArgument = ArgumentCaptor.forClass(ShoppingCart.class);
        verify(repository, times(1)).save(cartArgument.capture());
        verifyNoMoreInteractions(repository);

        ShoppingCart model = cartArgument.getValue();

        assertEquals(cart.getUser(), model.getUser());
    }
}
