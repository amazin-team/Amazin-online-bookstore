package amazin.controller;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import amazin.model.Book;
import amazin.service.BookService;

@Controller
@SessionAttributes("book")
public class BookController {
    public static final String VIEW_CREATE_BOOK = "create-book";
    public static final String VIEW_UPDATE_BOOK = "update-book";
    public static final Object MODEL_ATTRIBUTE_BOOk = "book";

    @Autowired
    private final BookService bookService;

    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/addbook")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return VIEW_CREATE_BOOK;
    }

    @PostMapping("/addbook")
    public String addBook(@Valid @ModelAttribute Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return VIEW_CREATE_BOOK;
        }

        bookService.create(book);
        model.addAttribute("books", bookService.getAll());
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
        model.addAttribute("books", bookService.getAll());
        return "redirect:/";
    }

    @GetMapping("delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        bookService.delete(id);
        model.addAttribute("books", bookService.getAll());
        return "redirect:/";
    }

}
