package amazin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    // Autowire your CrudRepo of book
    @Autowired
    BookRepository repository;

    @GetMapping("/addbook")
    public String bookForm(Model model) {
        model.addAttribute("book", new Book());
        return "create-book";
    }

    @PostMapping("/addbook")
    public String addBook(@Valid @ModelAttribute Book book, Model model, BindingResult result) {
        if (result.hasErrors()) {
            return "create-book";
        }
        Book new_Book = new Book(book.getName(), book.getDescription(), book.getISBN(), book.getPicture(),
                book.getAuthor(), book.getPublisher(), book.getSKU(), book.getInventory(), book.getPrice());
        repository.save(new_Book);
        model.addAttribute("new_book", new_Book);
        return "bookResult";
    }

    @GetMapping("/book/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Book book = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));

        model.addAttribute("book", book);
        return "update-book";
    }

    @PostMapping("/book/update/{id}")
    public String updateBook(@PathVariable("id") long id, @Valid Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            book.setId(id);
            return "update-book";
        }

        repository.save(book);
        model.addAttribute("books", repository.findAll());
        return "index";
    }

    @GetMapping("book/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        Book book = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        repository.delete(book);
        model.addAttribute("books", repository.findAll());
        return "index";
    }

}