package amazin.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by lauramachado on 2019-04-03.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ShoppingCartTest {

    private ShoppingCart cart;

    @Before
    public void setUp() throws Exception {
        this.cart = new ShoppingCart();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getId() {
        cart.setId(1L);

        assertEquals(1L, cart.getId().longValue());
    }

    @Test
    public void setId() {
        cart.setId(new Long(4));

        assertEquals(4L, cart.getId().longValue());
    }

    @Test
    public void getSetUser() {
        User u = new User();
        u.setFirstName("Laura");
        cart.setUser(u);

        assertEquals("Laura", cart.getUser().getFirstName());
    }

    @Test
    public void setItems(){
        ArrayList<Item> items = new ArrayList<>();
        Item item1 = new Item();
        Book book1 = new Book();
        item1.setBook(book1);
        item1.setQuantity(2);
        Item item2 = new Item();
        Book book2 = new Book();
        item2.setBook(book2);
        item2.setQuantity(3);

        items.add(item1);
        items.add(item2);
        cart.setItems(items);

        assertEquals(2, cart.getItems().size());
        assertEquals(5, cart.getItemCount());
    }

    @Test
    public void testTotal(){
        ArrayList<Item> items = new ArrayList<>();
        Item item1 = new Item();
        Book book1 = new Book();
        book1.setPrice(30);
        item1.setBook(book1);
        item1.setQuantity(2);
        Item item2 = new Item();
        Book book2 = new Book();
        book2.setPrice(15);
        item2.setBook(book2);
        item2.setQuantity(3);

        items.add(item1);
        items.add(item2);

        cart.setItems(items);

        double expectedTotal = (15*3)+(30*2);
        assertEquals(expectedTotal, cart.getTotal(),0);
    }


}
