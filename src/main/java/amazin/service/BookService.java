package amazin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amazin.model.Book;
import amazin.repository.BookRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class BookService {

    private final BookRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Optional<Book> findById(Long id) {
        return repository.findById(id);
    }

    public List<Book> getAll() {
        return (List<Book>) repository.findAll();
    }

    public Book create(Book book) {
        repository.save(book);
        logger.debug(String.format("Book: %s created successfully!", book.getName()));
        return book;
    }

    public Book update(Book updatedBook) {
        Optional<Book> bookToBeUpdated = repository.findById(updatedBook.getId());

        if (bookToBeUpdated.isPresent()) {
            Book book = bookToBeUpdated.get();
            book.setAuthor(updatedBook.getAuthor());
            book.setDescription(updatedBook.getDescription());
            book.setISBN(updatedBook.getISBN());
            book.setInventory(updatedBook.getInventory());
            book.setName(updatedBook.getName());
            book.setPicture(updatedBook.getPicture());
            book.setPrice(updatedBook.getPrice());
            book.setPublisher(updatedBook.getPublisher());
            repository.save(book);
            logger.debug(String.format("Book: %s updated successfully!", book.getName()));
            return book;
        }
        return null;
    }

    public Optional<Book> delete(Long id) {
        Optional<Book> deleted = findById(id);
        if (deleted.isPresent()) {
            repository.delete(deleted.get());
            logger.debug(String.format("Book: %s deleted successfully!", deleted.get().getName()));
        }

        return deleted;
    }
}