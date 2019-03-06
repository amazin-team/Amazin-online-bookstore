package amazin;

import org.junit.*;

public class BookTest extends BaseTest {

    private void testAllFieldsDefaultExcept(Fields.Book field) {
        for(Fields.Book f : Fields.Book.values()) {
            if (f.compareTo(field) != 0)
                assertFieldEqualsDefault(f);
        }
    }

    private void testSetField(Fields.Book field, Object val) {
        switch(field) {
            case ID:
                book.setId((Long) val);
                break;

            case NAME:
                book.setName((String) val);
                break;

            case AUTHOR:
                book.setAuthor((String) val);
                break;

            case DESCRIPTION:
                book.setDescription((String) val);
                break;

            case ISBN:
                book.setISBN((Integer) val);
                break;

            case PICTURE:
                book.setPicture((String) val);
                break;

            case PUBLISHER:
                book.setPublisher((String) val);
                break;

            case SKU:
                book.setSKU((Integer) val);
                break;

            case INVENTORY:
                book.setInventory((Integer) val);
                break;

            case PRICE:
                book.setPrice((Double) val);
                break;
        }

        // Test the field has changed.
        assertFieldEquals(field, val);

        // Other fields should be unchanged.
        testAllFieldsDefaultExcept(field);
    }

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
        int TEST = 400;
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
    public void testGetSKU() {
        assertFieldEqualsDefault(Fields.Book.SKU);
    }

    @Test
    public void testSetSKU() {
        int TEST = 400;
        testSetField(Fields.Book.SKU, TEST);
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
