package amazin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amazin.model.Book;
import amazin.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository repository;

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

    public void create(Book book) {
        repository.save(book);
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
            return book;
        }
        return null;
    }

    public Optional<Book> delete(Long id) {
        Optional<Book> deleted = findById(id);
        if (deleted.isPresent()) {
            repository.delete(deleted.get());
        }

        return deleted;
    }
}