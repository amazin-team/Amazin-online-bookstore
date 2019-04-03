package amazin;

import org.junit.*;
import org.springframework.test.util.AssertionErrors;

import amazin.model.Book;

public class BaseTest {

    protected Book book;

    protected static long DEFAULT_ID = 6;
    protected static String DEFAULT_NAME = "The Test";
    protected static String DEFAULT_DESCRIPTION = "A book about testing.";
    protected static String DEFAULT_ISBN = "6666666666666";
    protected static String DEFAULT_PICTURE = "/the-test.jpg";
    protected static String DEFAULT_AUTHOR = "Moe";
    protected static String DEFAULT_PUBLISHER = "Amazin Publishers";
    protected static int DEFAULT_INVENTORY = 6666;
    protected static double DEFAULT_PRICE = 66.66;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        book = new Book(DEFAULT_ID, DEFAULT_NAME, DEFAULT_DESCRIPTION, DEFAULT_ISBN, DEFAULT_PICTURE, DEFAULT_AUTHOR,
                DEFAULT_PUBLISHER, DEFAULT_INVENTORY, DEFAULT_PRICE);
    }

    @After
    public void tearDown() throws Exception {
    }

    protected void testAllFieldsDefaultExcept(Fields.Book field) {
        for (Fields.Book f : Fields.Book.values()) {
            if (f.compareTo(field) != 0)
                assertFieldEqualsDefault(f);
        }
    }

    protected void testSetField(Fields.Book field, Object val) {
        switch (field) {
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
            book.setISBN((String) val);
            break;

        case PICTURE:
            book.setPicture_url((String) val);
            break;

        case PUBLISHER:
            book.setPublisher((String) val);
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

    protected void assertFieldBase(Fields.Book field, Object expected, boolean defaultValue,
            TriConsumer<String, Object, Object> assertFunc) {
        Object result;
        String message = "Unkown field enum specified: " + field + ".";
        switch (field) {
        default:
            result = null;

        case ID:
            result = book.getId();
            if (defaultValue)
                expected = DEFAULT_ID;
            break;

        case NAME:
            result = book.getName();
            if (defaultValue)
                expected = DEFAULT_NAME;
            break;

        case AUTHOR:
            result = book.getAuthor();
            if (defaultValue)
                expected = DEFAULT_AUTHOR;
            break;

        case DESCRIPTION:
            result = book.getDescription();
            if (defaultValue)
                expected = DEFAULT_DESCRIPTION;
            break;

        case ISBN:
            result = book.getISBN();
            if (defaultValue)
                expected = DEFAULT_ISBN;
            break;

        case PICTURE:
            result = book.getPicture_url();
            if (defaultValue)
                expected = DEFAULT_PICTURE;
            break;

        case PUBLISHER:
            result = book.getPublisher();
            if (defaultValue)
                expected = DEFAULT_PUBLISHER;
            break;

        case INVENTORY:
            result = book.getInventory();
            if (defaultValue)
                expected = DEFAULT_INVENTORY;
            break;

        case PRICE:
            result = book.getPrice();
            if (defaultValue)
                expected = DEFAULT_PRICE;
            break;

        }

        if (expected != null)
            message = "The " + field.name() + " should be " + expected.toString() + ".";

        assertFunc.accept(message, expected, result);
    }

    protected void assertFieldEquals(Fields.Book field, Object expected) {
        assertFieldBase(field, expected, false,
                (String msg, Object exp, Object res) -> AssertionErrors.assertEquals(msg, exp, res));
    }

    protected void assertFieldNotEquals(Fields.Book field, Object expected) {
        assertFieldBase(field, expected, false,
                (String msg, Object exp, Object res) -> AssertionErrors.assertEquals(msg, exp, res));
    }

    protected void assertFieldEqualsDefault(Fields.Book field) {
        assertFieldBase(field, null, true,
                (String msg, Object exp, Object res) -> AssertionErrors.assertEquals(msg, exp, res));
    }

}
