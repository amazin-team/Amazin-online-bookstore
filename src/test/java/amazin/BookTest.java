package amazin;

import org.junit.*;

public class BookTest extends BaseTest {

    @Test
    public void testBook() {
        for (Fields.Book field : Fields.Book.values())
            assertFieldEqualsDefault(field);
    }

    @Test
    public void testGetId() {
        assertFieldEqualsDefault(Fields.Book.ID);
    }

    @Test
    public void testSetId() {
        long TEST = 400;
        testSetField(Fields.Book.ID, TEST);
    }

    @Test
    public void testGetName() {
        assertFieldEqualsDefault(Fields.Book.NAME);
    }

    @Test
    public void testSetName() {
        String TEST = "400";
        testSetField(Fields.Book.NAME, TEST);
    }

    @Test
    public void testGetDescription() {
        assertFieldEqualsDefault(Fields.Book.DESCRIPTION);
    }

    @Test
    public void testSetDescription() {
        String TEST = "400";
        testSetField(Fields.Book.DESCRIPTION, TEST);
    }

    @Test
    public void testGetISBN() {
        assertFieldEqualsDefault(Fields.Book.ISBN);
    }

    @Test
    public void testSetISBN() {
        String TEST = "1111111111111";
        testSetField(Fields.Book.ISBN, TEST);
    }

    @Test
    public void testGetPicture() {
        assertFieldEqualsDefault(Fields.Book.PICTURE);
    }

    @Test
    public void testSetPicture() {
        String TEST = "400";
        testSetField(Fields.Book.PICTURE, TEST);
    }

    @Test
    public void testGetAuthor() {
        assertFieldEqualsDefault(Fields.Book.AUTHOR);
    }

    @Test
    public void testSetAuthor() {
        String TEST = "400";
        testSetField(Fields.Book.AUTHOR, TEST);
    }

    @Test
    public void testGetPublisher() {
        assertFieldEqualsDefault(Fields.Book.PUBLISHER);
    }

    @Test
    public void testSetPublisher() {
        String TEST = "400";
        testSetField(Fields.Book.PUBLISHER, TEST);
    }

    @Test
    public void testGetInventory() {
        assertFieldEqualsDefault(Fields.Book.INVENTORY);
    }

    @Test
    public void testSetInventory() {
        int TEST = 400;
        testSetField(Fields.Book.INVENTORY, TEST);
    }

    @Test
    public void testGetPrice() {
        assertFieldEqualsDefault(Fields.Book.PRICE);
    }

    @Test
    public void testSetPrice() {
        double TEST = 400.0;
        testSetField(Fields.Book.PRICE, TEST);
    }
}
