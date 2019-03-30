package amazin.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import amazin.model.Book;
import amazin.service.BookService;
import amazin.service.HibernateSearchService;

@Controller
@SessionAttributes("books")
public class BookController {
    public static final String VIEW_CREATE_BOOK = "create-book";
    public static final String VIEW_UPDATE_BOOK = "update-book";
    public static final String MODEL_ATTRIBUTE_BOOK = "books";
    public static final String PARAMETER_BOOK_ID = "id";
    public static final String REQUEST_MAPPING_BOOK = "/";

    @Autowired
    private HibernateSearchService searchservice;

    @Autowired
    private final BookService bookService;

    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/addbook")
    public String addBookForm(Model model) {
        model.addAttribute(MODEL_ATTRIBUTE_BOOK, new Book());
        return VIEW_CREATE_BOOK;
    }

    @PostMapping("/addbook")
    public String addBook(@Valid @ModelAttribute(MODEL_ATTRIBUTE_BOOK) Book book, BindingResult result,
            RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return VIEW_CREATE_BOOK;
        }

        Book addedBook = bookService.create(book);

        attributes.addAttribute(PARAMETER_BOOK_ID, addedBook.getId());

        return createRedirectViewPath(REQUEST_MAPPING_BOOK);
    }

    @GetMapping("/edit/{id}")
    public String updateBookForm(@PathVariable("id") Long id, Model model) {
        Optional<Book> optionalBook = bookService.findById(id);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            model.addAttribute(MODEL_ATTRIBUTE_BOOK, book);
            return VIEW_UPDATE_BOOK;
        }
        return createRedirectViewPath(REQUEST_MAPPING_BOOK);
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Long id, @Valid @ModelAttribute(MODEL_ATTRIBUTE_BOOK) Book book,
            BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            book.setId(id);
            return VIEW_UPDATE_BOOK;
        }

        Book updatedBook = bookService.update(book);

        attributes.addAttribute(PARAMETER_BOOK_ID, updatedBook.getId());

        return createRedirectViewPath(REQUEST_MAPPING_BOOK);
    }

    @GetMapping("delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, RedirectAttributes attributes) {
        Optional<Book> deletedBook = bookService.delete(id);

        if (deletedBook.isPresent()) {
            attributes.addAttribute(PARAMETER_BOOK_ID, deletedBook.get().getId());
        }

        return createRedirectViewPath(REQUEST_MAPPING_BOOK);
    }

    @GetMapping("/search")
    public String searchByBookName(@RequestParam(value = "keywords", required = true) String text, Model model) {

        if (text.isEmpty())
            return createRedirectViewPath(REQUEST_MAPPING_BOOK);

        String[] fieldNames = { Book.BOOK_NAME_FIELD, Book.BOOK_DESCRIPTION_FIELD, Book.BOOK_ISBN_FIELD,
                Book.BOOK_AUTHOR_FIELD, Book.BOOK_PUBLISHER_FIELD };

        List<Book> searchResults = null;
        try {
            searchResults = searchservice.fuzzySearch(text, fieldNames);
        } catch (Exception ex) {
            /** TODO: handle this exception properly and display message to user */
            ex.printStackTrace();
        }
        if (searchResults != null && searchResults.isEmpty())
            model.addAttribute(MODEL_ATTRIBUTE_BOOK, null);
        else
            model.addAttribute(MODEL_ATTRIBUTE_BOOK, searchResults);
        return "index";
    }

    private String createRedirectViewPath(String requestMapping) {
        StringBuilder redirectViewPath = new StringBuilder();
        redirectViewPath.append("redirect:");
        redirectViewPath.append(requestMapping);
        return redirectViewPath.toString();
    }
}
