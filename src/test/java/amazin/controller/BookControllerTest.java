package amazin.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.support.BindingAwareModelMap;

import amazin.BookTestUtil;
import amazin.model.Book;
import amazin.repository.BookRepository;
import amazin.service.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
public class BookControllerTest {

    private BookController controller;
    private BookService bookService;

    @Mock
    private BookRepository repo;

    @Before
    public void setUp() {
        bookService = new BookService(repo);
        controller = new BookController(bookService);
    }

    @Test
    public void showAddBookForm() {
        BindingAwareModelMap model = new BindingAwareModelMap();

        String view = controller.addBookForm(model);

        assertEquals(BookController.VIEW_CREATE_BOOK, view);

        Book formModel = (Book) model.asMap().get(BookController.MODEL_ATTRIBUTE_BOOK);

        assertNull(formModel.getId());
        assertNull(formModel.getDescription());
        assertNull(formModel.getAuthor());
        assertNull(formModel.getPublisher());
        assertNull(formModel.getISBN());
        assertNull(formModel.getName());
        assertEquals(formModel.getInventory(), 0);
        assertEquals(formModel.getPrice(), 0, 0.1);
    }

    @Test
    public void showUpdateBookForm() {
        BindingAwareModelMap model = new BindingAwareModelMap();
        Book updated = BookTestUtil.createModel(BookTestUtil.ID, BookTestUtil.NAME, BookTestUtil.DESCRIPTION,
                BookTestUtil.ISBN, BookTestUtil.PICTURE, BookTestUtil.AUTHOR, BookTestUtil.PUBLISHER,
                BookTestUtil.INVENTORY, BookTestUtil.PRICE);

        Optional<Book> optionalBook = Optional.of(updated);
        when(bookService.findById(BookTestUtil.ID)).thenReturn(optionalBook);

        String view = controller.updateBookForm(BookTestUtil.ID, model);

        verify(repo, times(1)).findById(BookTestUtil.ID);
        verifyNoMoreInteractions(repo);

        assertEquals(BookController.VIEW_UPDATE_BOOK, view);

        Book formModel = (Book) model.asMap().get(BookController.MODEL_ATTRIBUTE_BOOK);

        assertEquals(updated.getId(), formModel.getId());
        assertEquals(updated.getDescription(), formModel.getDescription());
        assertEquals(updated.getAuthor(), formModel.getAuthor());
        assertEquals(updated.getPublisher(), formModel.getPublisher());
        assertEquals(updated.getISBN(), formModel.getISBN());
        assertEquals(updated.getName(), formModel.getName());
        assertEquals(updated.getInventory(), formModel.getInventory());
        assertEquals(updated.getPrice(), formModel.getPrice(), 0.1);
    }

    @Test
    public void createBook() {
        Book formModel = BookTestUtil.createModel(BookTestUtil.ID, BookTestUtil.NAME, BookTestUtil.DESCRIPTION,
                BookTestUtil.ISBN, BookTestUtil.PICTURE, BookTestUtil.AUTHOR, BookTestUtil.PUBLISHER,
                BookTestUtil.INVENTORY, BookTestUtil.PRICE);

        bookService.create(formModel);

        ArgumentCaptor<Book> bookArgument = ArgumentCaptor.forClass(Book.class);
        verify(repo, times(1)).save(bookArgument.capture());
        verifyNoMoreInteractions(repo);

        Book model = bookArgument.getValue();

        assertNotNull(model.getId());
        assertEquals(formModel.getDescription(), model.getDescription());
        assertEquals(formModel.getAuthor(), model.getAuthor());
        assertEquals(formModel.getPublisher(), model.getPublisher());
        assertEquals(formModel.getISBN(), model.getISBN());
        assertEquals(formModel.getName(), model.getName());
        assertEquals(formModel.getInventory(), model.getInventory());
        assertEquals(formModel.getPrice(), model.getPrice(), 0.1);
    }

    @Test
    public void updateBook() {

        Book formModel = BookTestUtil.createModel(BookTestUtil.ID, BookTestUtil.NAME_UPDATED,
                BookTestUtil.DESCRIPTION_UPDATED, BookTestUtil.ISBN_UPDATED, BookTestUtil.PICTURE_UPDATED,
                BookTestUtil.AUTHOR_UPDATED, BookTestUtil.PUBLISHER_UPDATED, BookTestUtil.INVENTORY_UPDATED,
                BookTestUtil.PRICE_UPDATED);

        Book model = BookTestUtil.createModel(BookTestUtil.ID, BookTestUtil.NAME, BookTestUtil.DESCRIPTION,
                BookTestUtil.ISBN, BookTestUtil.PICTURE, BookTestUtil.AUTHOR, BookTestUtil.PUBLISHER,
                BookTestUtil.INVENTORY, BookTestUtil.PRICE);

        Optional<Book> optionalModel = Optional.of(model);
        when(repo.findById(formModel.getId())).thenReturn(optionalModel);

        Book actual = bookService.update(formModel);

        verify(repo, times(1)).save(formModel);
        verify(repo, times(1)).findById(formModel.getId());
        verifyNoMoreInteractions(repo);

        assertEquals(formModel.getDescription(), actual.getDescription());
        assertEquals(formModel.getAuthor(), actual.getAuthor());
        assertEquals(formModel.getPublisher(), actual.getPublisher());
        assertEquals(formModel.getISBN(), actual.getISBN());
        assertEquals(formModel.getName(), actual.getName());
        assertEquals(formModel.getInventory(), actual.getInventory());
        assertEquals(formModel.getPrice(), actual.getPrice(), 0.1);
    }

    @Test
    public void deletBook() {
        Book model = BookTestUtil.createModel(BookTestUtil.ID, BookTestUtil.NAME, BookTestUtil.DESCRIPTION,
                BookTestUtil.ISBN, BookTestUtil.PICTURE, BookTestUtil.AUTHOR, BookTestUtil.PUBLISHER,
                BookTestUtil.INVENTORY, BookTestUtil.PRICE);
        Optional<Book> optionalModel = Optional.of(model);

        when(repo.findById(BookTestUtil.ID)).thenReturn(optionalModel);

        Optional<Book> optionalActual = bookService.delete(BookTestUtil.ID);

        assertTrue("Book must be present", optionalActual.isPresent());

        Book actual = optionalActual.get();

        verify(repo, times(1)).findById(BookTestUtil.ID);
        verify(repo, times(1)).delete(model);
        verifyNoMoreInteractions(repo);

        assertEquals(model, actual);
    }
}
