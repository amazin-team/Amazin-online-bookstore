package amazin.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import amazin.model.User;
import amazin.repository.UserRepository;
import amazin.service.SecurityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import amazin.model.Book;
import amazin.service.BookService;
import amazin.service.HibernateSearchService;

import java.io.File;
import amazin.service.AmazonService;
import org.springframework.web.multipart.MultipartFile;

@Controller
@SessionAttributes("books")
public class BookController {
    public static final String VIEW_CREATE_BOOK = "create-book";
    public static final String VIEW_UPDATE_BOOK = "update-book";
    public static final String MODEL_ATTRIBUTE_BOOK = "books";
    public static final String PARAMETER_BOOK_ID = "id";
    public static final String REQUEST_MAPPING_BOOK = "/";
    public static final String VIEW_BOOK = "view-book";

    @Autowired
    private HibernateSearchService searchservice;

    @Autowired
    private final BookService bookService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SecurityServiceImpl securityService;

    @Autowired
    private final AmazonService amazonService;

    public BookController(final BookService bookService, final AmazonService amazonService) {
        this.bookService = bookService;
        this.amazonService = amazonService;
    }

    @GetMapping("/addbook")
    public String addBookForm(Model model) {
        User currentUser = userRepository.findByEmail(securityService.findLoggedInEmail());
        model.addAttribute(UserController.MODEL_ATTRIBUTE_USER, currentUser);
        model.addAttribute(MODEL_ATTRIBUTE_BOOK, new Book());
        return VIEW_CREATE_BOOK;
    }

    @PostMapping("/addbook")
    public String addBook(@RequestParam(name = "picture") MultipartFile picture, @Valid @ModelAttribute(MODEL_ATTRIBUTE_BOOK) Book book, BindingResult result) {
        if (result.hasErrors()) {
            return VIEW_CREATE_BOOK;
        }
        book.setPicture_url(uploadFile(picture));
        bookService.create(book);
        return createRedirectViewPath(REQUEST_MAPPING_BOOK);
    }

    @GetMapping("/book/{id}")
    public String getBookForm(@PathVariable("id") Long id, Model model) {
        Optional<Book> optionalBook = bookService.findById(id);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            model.addAttribute(MODEL_ATTRIBUTE_BOOK, book);
            return VIEW_BOOK;
        }
        return createRedirectViewPath(REQUEST_MAPPING_BOOK);
    }

    @GetMapping("/edit/{id}")
    public String updateBookForm(@PathVariable("id") Long id, Model model) {
        Optional<Book> optionalBook = bookService.findById(id);
        User currentUser = userRepository.findByEmail(securityService.findLoggedInEmail());
        model.addAttribute(UserController.MODEL_ATTRIBUTE_USER, currentUser);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            model.addAttribute(MODEL_ATTRIBUTE_BOOK, book);
            return VIEW_UPDATE_BOOK;
        }
        return createRedirectViewPath(REQUEST_MAPPING_BOOK);
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Long id, @Valid @ModelAttribute(MODEL_ATTRIBUTE_BOOK) Book book,
            BindingResult result) {
        if (result.hasErrors()) {
            book.setId(id);
            return VIEW_UPDATE_BOOK;
        }

        bookService.update(book);

        return createRedirectViewPath(REQUEST_MAPPING_BOOK);
    }

    @PostMapping("delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        Optional<Book> deletedBook = bookService.delete(id);

        if (deletedBook.isPresent()) {
            Book book = deletedBook.get();
            deleteFile(book.getPicture_url());
        }

        return createRedirectViewPath(REQUEST_MAPPING_BOOK);
    }

    @PostMapping("/storage/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
        return this.amazonService.uploadFile(file);
    }

    @DeleteMapping("/storage/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonService.deleteFileFromS3Bucket(fileUrl);
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
