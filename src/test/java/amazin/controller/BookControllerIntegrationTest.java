package amazin.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
import org.springframework.web.bind.WebDataBinder;

import amazin.BookTestUtil;
import amazin.model.Book;
import amazin.repository.BookRepository;
import amazin.repository.TagRepository;
import amazin.service.BookService;
import amazin.service.AmazonService;

@RunWith(SpringJUnit4ClassRunner.class)
public class BookControllerIntegrationTest {

    private BookController controller;
    private BookService bookService;
    private AmazonService amazonService;

    @Mock
    private BookRepository repo;

    @Mock
    private TagRepository tagRepo;

    @Before
    public void setUp() {
        BookTestUtil.setup();
        bookService = new BookService(repo, tagRepo);
        amazonService = new AmazonService();
        controller = new BookController(bookService, amazonService);
    }

    @Test
    public void updateBook() {
        Book book = BookTestUtil.createModel(BookTestUtil.ID, BookTestUtil.NAME, BookTestUtil.DESCRIPTION,
                BookTestUtil.ISBN, BookTestUtil.PICTURE, BookTestUtil.AUTHOR, BookTestUtil.PUBLISHER,
                BookTestUtil.INVENTORY, BookTestUtil.PRICE, BookTestUtil.TAGS);

        Optional<Book> optionalBook = Optional.of(book);
        when(bookService.findById(BookTestUtil.ID)).thenReturn(optionalBook);

        Book formObject = BookTestUtil.createModel(BookTestUtil.ID, BookTestUtil.NAME_UPDATED,
                BookTestUtil.DESCRIPTION_UPDATED, BookTestUtil.ISBN_UPDATED, BookTestUtil.PICTURE_UPDATED,
                BookTestUtil.AUTHOR_UPDATED, BookTestUtil.PUBLISHER_UPDATED, BookTestUtil.INVENTORY_UPDATED,
                BookTestUtil.PRICE_UPDATED, BookTestUtil.TAGS_UPDATED);

        MockHttpServletRequest mockRequest = new MockHttpServletRequest("POST", "/update/" + BookTestUtil.ID);
        BindingResult result = bind(mockRequest, formObject);

        String view = controller.updateBook(BookTestUtil.ID, formObject, result);

        verify(repo, times(1)).save(formObject);
        verify(repo, times(1)).findById(BookTestUtil.ID);
        verifyNoMoreInteractions(repo);

        assertNotEquals(BookTestUtil.NAME, BookTestUtil.NAME_UPDATED);

        String expectedView = "redirect:/";
        assertEquals(expectedView, view);
    }

    private BindingResult bind(HttpServletRequest request, Object formObject) {
        WebDataBinder binder = new WebDataBinder(formObject);
        binder.bind(new MutablePropertyValues(request.getParameterMap()));
        return binder.getBindingResult();
    }
}
