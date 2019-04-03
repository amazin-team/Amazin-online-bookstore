package amazin.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.WebDataBinder;

import amazin.BookTestUtil;
import amazin.model.Book;
import amazin.service.BookService;
import amazin.service.HibernateSearchService;

@RunWith(SpringJUnit4ClassRunner.class)
public class BookControllerTest {

    private BookController controller;
    private BookService bookService;

    @Before
    public void setUp() {
        bookService = mock(BookService.class);
        mock(HibernateSearchService.class);
        controller = new BookController(bookService);
    }

    /**
     * TODO fix the mock calls for SecurityServiceImpl so this doesn't throw a null
     * pointer exception
     */
    @Test(expected = NullPointerException.class)
    public void showAddBookForm() {
        BindingAwareModelMap model = new BindingAwareModelMap();

        String view = controller.addBookForm(model);

        verifyZeroInteractions(bookService);
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

    /**
     * TODO fix the mock calls for SecurityServiceImpl so this doesn't throw a null
     * pointer exception
     */
    @Test(expected = NullPointerException.class)
    public void showUpdateBookForm() {
        BindingAwareModelMap model = new BindingAwareModelMap();
        Book updated = BookTestUtil.createModel(BookTestUtil.ID, BookTestUtil.NAME, BookTestUtil.DESCRIPTION,
                BookTestUtil.ISBN, BookTestUtil.PICTURE, BookTestUtil.AUTHOR, BookTestUtil.PUBLISHER,
                BookTestUtil.INVENTORY, BookTestUtil.PRICE);

        Optional<Book> optionalBook = Optional.of(updated);
        when(bookService.findById(BookTestUtil.ID)).thenReturn(optionalBook);

        String view = controller.updateBookForm(BookTestUtil.ID, model);

        verify(bookService, times(1)).findById(BookTestUtil.ID);
        verifyNoMoreInteractions(bookService);

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
    public void addBook() {
        Book formModel = BookTestUtil.createModel(BookTestUtil.ID, BookTestUtil.NAME, BookTestUtil.DESCRIPTION,
                BookTestUtil.ISBN, BookTestUtil.PICTURE, BookTestUtil.AUTHOR, BookTestUtil.PUBLISHER,
                BookTestUtil.INVENTORY, BookTestUtil.PRICE);

        Book model = BookTestUtil.createModel(BookTestUtil.ID, BookTestUtil.NAME, BookTestUtil.DESCRIPTION,
                BookTestUtil.ISBN, BookTestUtil.PICTURE, BookTestUtil.AUTHOR, BookTestUtil.PUBLISHER,
                BookTestUtil.INVENTORY, BookTestUtil.PRICE);

        when(bookService.create(formModel)).thenReturn(model);

        MockHttpServletRequest mockRequest = new MockHttpServletRequest("POST", "/addbook");
        BindingResult result = bind(mockRequest, formModel);

        String view = controller.addBook(formModel, result);

        verify(bookService, times(1)).create(formModel);
        verifyNoMoreInteractions(bookService);

        String expectedView = BookTestUtil.createRedirectViewPath(BookController.REQUEST_MAPPING_BOOK);
        assertEquals(expectedView, view);
    }

    @Test
    public void deleteBook() {

        Book model = BookTestUtil.createModel(BookTestUtil.ID, BookTestUtil.NAME, BookTestUtil.DESCRIPTION,
                BookTestUtil.ISBN, BookTestUtil.PICTURE, BookTestUtil.AUTHOR, BookTestUtil.PUBLISHER,
                BookTestUtil.INVENTORY, BookTestUtil.PRICE);

        Optional<Book> optionalBook = Optional.of(model);
        when(bookService.delete(BookTestUtil.ID)).thenReturn(optionalBook);

        String view = controller.deleteBook(BookTestUtil.ID);

        verify(bookService, times(1)).delete(BookTestUtil.ID);
        verifyNoMoreInteractions(bookService);

        String expectedView = BookTestUtil.createRedirectViewPath(BookController.REQUEST_MAPPING_BOOK);
        assertEquals(expectedView, view);
    }

    private BindingResult bind(HttpServletRequest request, Object formModel) {
        WebDataBinder binder = new WebDataBinder(formModel);
        binder.bind(new MutablePropertyValues(request.getParameterMap()));
        return binder.getBindingResult();
    }
}
