package amazin.controller;

import java.io.File;
import java.util.Optional;

import javax.validation.Valid;

import amazin.service.AmazonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import amazin.model.Book;
import amazin.service.BookService;
import org.springframework.web.multipart.MultipartFile;

@Controller
@SessionAttributes("book")
public class BookController {
    public static final String VIEW_CREATE_BOOK = "create-book";
    public static final String VIEW_UPDATE_BOOK = "update-book";
    public static final Object MODEL_ATTRIBUTE_BOOK = "book";

    @Autowired
    private final BookService bookService;

    @Autowired
    private final AmazonService amazonService;

    public BookController(final BookService bookService, final AmazonService amazonService) {
        this.bookService = bookService;
        this.amazonService = amazonService;
    }

    @GetMapping("/addbook")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return VIEW_CREATE_BOOK;
    }

    @PostMapping("/addbook")
    public String addBook(@RequestParam(name = "picture") MultipartFile picture, @Valid @ModelAttribute Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return VIEW_CREATE_BOOK;
        }
        book.setPicture_url(uploadFile(picture));
        bookService.create(book);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String updateBookForm(@PathVariable("id") Long id, Model model) {
        Optional<Book> optionalBook = bookService.findById(id);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            model.addAttribute("book", book);
            return VIEW_UPDATE_BOOK;
        }
        return "redirect:/";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Long id, @Valid Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            book.setId(id);
            return VIEW_UPDATE_BOOK;
        }
        bookService.update(book);
        return "redirect:/";
    }

    @GetMapping("delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        Optional<Book> bookToBeDeleted = bookService.findById(id);
        if (bookToBeDeleted.isPresent()) {
            Book book = bookToBeDeleted.get();
            deleteFile(book.getPicture_url());
        }
        bookService.delete(id);
        return "redirect:/";
    }

    @PostMapping("/storage/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
        return this.amazonService.uploadFile(file);
    }

    @DeleteMapping("/storage/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonService.deleteFileFromS3Bucket(fileUrl);
    }

}
