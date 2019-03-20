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

import amazin.model.Book;
import amazin.service.BookService;
import amazin.service.HibernateSearchService;

@Controller
@SessionAttributes("book")
public class BookController {
    public static final String VIEW_CREATE_BOOK = "create-book";
    public static final String VIEW_UPDATE_BOOK = "update-book";
    public static final String MODEL_ATTRIBUTE_BOOK = "book";
    public static final String REQUEST_MAPPING_Book = "/";

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
    public String addBook(@Valid @ModelAttribute Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return VIEW_CREATE_BOOK;
        }

        bookService.create(book);
        return createRedirectViewPath(REQUEST_MAPPING_Book);
    }

    @GetMapping("/edit/{id}")
    public String updateBookForm(@PathVariable("id") Long id, Model model) {
        Optional<Book> optionalBook = bookService.findById(id);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            model.addAttribute(MODEL_ATTRIBUTE_BOOK, book);
            return VIEW_UPDATE_BOOK;
        }
        return createRedirectViewPath(REQUEST_MAPPING_Book);
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Long id, @Valid Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            book.setId(id);
            return VIEW_UPDATE_BOOK;
        }

        bookService.update(book);
        return createRedirectViewPath(REQUEST_MAPPING_Book);
    }

    @GetMapping("delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        bookService.delete(id);
        return createRedirectViewPath(REQUEST_MAPPING_Book);
    }

    @GetMapping("/fuzzySearchName")
    public String fuzzySearchByBookName(@RequestParam(value = "keywords", required = false) String text, Model model) {
        List<Book> searchResults = null;
        try {
            searchResults = searchservice.fuzzySearch(text, "name");

        } catch (Exception ex) {
            // for now do nothing. Later we will throw an exception and display a message to
            // the user
        }
        model.addAttribute(MODEL_ATTRIBUTE_BOOK, searchResults);
        return createRedirectViewPath(REQUEST_MAPPING_Book);
    }

    @GetMapping("/keywordSearchName")
    public String keywordSearchByBookName(@RequestParam(value = "keywords", required = false) String text,
            Model model) {
        List<Book> searchResults = null;
        try {
            searchResults = searchservice.keywordSearch(text, "name");

        } catch (Exception ex) {
            // for now do nothing. Later we will throw an exception and display a message to
            // the user
        }
        model.addAttribute(MODEL_ATTRIBUTE_BOOK, searchResults);
        return createRedirectViewPath(REQUEST_MAPPING_Book);
    }

    @GetMapping("/wildcardSearchName")
    public String wildcardSearchByBookName(@RequestParam(value = "keywords", required = false) String text,
            Model model) {
        List<Book> searchResults = null;
        try {
            searchResults = searchservice.wildcardSearch(text, "name");

        } catch (Exception ex) {
            // for now do nothing. Later we will throw an exception and display a message to
            // the user
        }
        model.addAttribute(MODEL_ATTRIBUTE_BOOK, searchResults);
        return createRedirectViewPath(REQUEST_MAPPING_Book);
    }

    private String createRedirectViewPath(String requestMapping) {
        StringBuilder redirectViewPath = new StringBuilder();
        redirectViewPath.append("redirect:");
        redirectViewPath.append(requestMapping);
        return redirectViewPath.toString();
    }
}
