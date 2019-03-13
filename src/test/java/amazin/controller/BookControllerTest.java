package amazin.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

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

        Book formObject = (Book) model.asMap().get(BookController.MODEL_ATTRIBUTE_BOOk);

        assertNull(formObject.getId());
        assertNull(formObject.getDescription());
        assertNull(formObject.getAuthor());
        assertNull(formObject.getPublisher());
        assertNull(formObject.getISBN());
        assertNull(formObject.getName());
        assertEquals(formObject.getInventory(), 0);
        assertEquals(formObject.getPrice(), 0, 0.1);
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

        Book formObject = (Book) model.asMap().get(BookController.MODEL_ATTRIBUTE_BOOk);

        assertEquals(updated.getId(), formObject.getId());
        assertEquals(updated.getDescription(), formObject.getDescription());
        assertEquals(updated.getAuthor(), formObject.getAuthor());
        assertEquals(updated.getPublisher(), formObject.getPublisher());
        assertEquals(updated.getISBN(), formObject.getISBN());
        assertEquals(updated.getName(), formObject.getName());
        assertEquals(updated.getInventory(), formObject.getInventory());
        assertEquals(updated.getPrice(), formObject.getPrice(), 0.1);
    }

    @Test
    public void updateBook() {
        Book book = BookTestUtil.createModel(BookTestUtil.ID, BookTestUtil.NAME, BookTestUtil.DESCRIPTION,
                BookTestUtil.ISBN, BookTestUtil.PICTURE, BookTestUtil.AUTHOR, BookTestUtil.PUBLISHER,
                BookTestUtil.INVENTORY, BookTestUtil.PRICE);

        Optional<Book> optionalBook = Optional.of(book);
        when(bookService.findById(BookTestUtil.ID)).thenReturn(optionalBook);

        Book formObject = BookTestUtil.createModel(BookTestUtil.ID, BookTestUtil.NAME_UPDATED,
                BookTestUtil.DESCRIPTION_UPDATED, BookTestUtil.ISBN_UPDATED, BookTestUtil.PICTURE_UPDATED,
                BookTestUtil.AUTHOR_UPDATED, BookTestUtil.PUBLISHER_UPDATED, BookTestUtil.INVENTORY_UPDATED,
                BookTestUtil.PRICE_UPDATED);

        MockHttpServletRequest mockRequest = new MockHttpServletRequest("POST", "/update/" + BookTestUtil.ID);
        BindingResult result = bind(mockRequest, formObject);

        RedirectAttributesModelMap attributes = new RedirectAttributesModelMap();
        String view = controller.updateBook(BookTestUtil.ID, formObject, result, attributes);

        verify(repo, times(1)).save(formObject);
        verify(repo, times(1)).findAll();
        verify(repo, times(1)).findById(BookTestUtil.ID);
        verifyNoMoreInteractions(repo);

        String expectedView = "redirect:/";
        assertEquals(expectedView, view);
    }

    @Test
    public void deleteBook() {
        RedirectAttributesModelMap attributes = new RedirectAttributesModelMap();

        String view = controller.deleteBook(BookTestUtil.ID, attributes);

        verify(repo, times(1)).deleteById(BookTestUtil.ID);
        verify(repo, times(1)).findAll();
        verifyNoMoreInteractions(repo);

        String expectedView = "redirect:/";
        assertEquals(expectedView, view);
    }

    private BindingResult bind(HttpServletRequest request, Object formObject) {
        WebDataBinder binder = new WebDataBinder(formObject);
        binder.bind(new MutablePropertyValues(request.getParameterMap()));
        return binder.getBindingResult();
    }
}
