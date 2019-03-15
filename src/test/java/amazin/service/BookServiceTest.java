package amazin.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import amazin.model.Book;
import amazin.repository.BookRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceTest {
    @InjectMocks
    BookService service;

    @Mock
    BookRepository repository;

    private static final Long ID = 1L;

    @Test
    public void findByIdTest() {
        service.findById(ID);
        verify(repository).findById(ID);
    }

    @Test
    public void getAllTest() {
        service.getAll();
        verify(repository).findAll();
    }

    @Test
    public void saveTest() {
        Book book = mock(Book.class);
        service.create(book);
        verify(repository).save(book);
    }

    @Test
    public void updateTest() {
        Book book = mock(Book.class);
        Optional<Book> optionalBook = Optional.of(book);
        when(book.getId()).thenReturn(ID);
        when(book.getAuthor()).thenReturn("Test Author");
        when(book.getName()).thenReturn("Test Name");
        when(book.getDescription()).thenReturn("Test Description");
        when(book.getISBN()).thenReturn("1111111111111");
        when(book.getInventory()).thenReturn(12);
        when(book.getPicture()).thenReturn("Test Picture");
        when(book.getPrice()).thenReturn(56.34);
        when(book.getPublisher()).thenReturn("Test Publisher");
        when(repository.findById(ID)).thenReturn(optionalBook);
        service.update(book);
        verify(repository).save(book);
    }

    @Test
    public void deleteTest() {
        service.delete(ID);
        verify(repository).deleteById(ID);
    }
}