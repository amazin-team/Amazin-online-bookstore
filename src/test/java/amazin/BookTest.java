package amazin;

import org.junit.*;

public class BookTest extends BaseTest {

    @Test
    public void testBook() {
        for(Fields.Book field : Fields.Book.values())
            assertFieldEqualsDefault(field);
    }

    @Test
    public void testGetId() {
        assertFieldEqualsDefault(Fields.Book.ID);
    }

    @Test
    public void testGetName() {
        assertFieldEqualsDefault(Fields.Book.NAME);
    }

    @Test
    public void testGetDescription() {
        assertFieldEqualsDefault(Fields.Book.DESCRIPTION);
    }

    @Test
    public void testGetISBN() {
        assertFieldEqualsDefault(Fields.Book.ISBN);
    }

    @Test
    public void testGetPicture() {
        assertFieldEqualsDefault(Fields.Book.PICTURE);
    }

    @Test
    public void testGetAuthor() {
        assertFieldEqualsDefault(Fields.Book.AUTHOR);
    }

    @Test
    public void testGetPublisher() {
        assertFieldEqualsDefault(Fields.Book.PUBLISHER);
    }

    @Test
    public void testGetSKU() {
        assertFieldEqualsDefault(Fields.Book.SKU);
    }

    @Test
    public void testGetInventory() {
        assertFieldEqualsDefault(Fields.Book.INVENTORY);
    }

    @Test
    public void testGetPrice() {
        assertFieldEqualsDefault(Fields.Book.PRICE);
    }
}
