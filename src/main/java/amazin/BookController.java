package amazin;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.ModelAttribute;
        import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    //Autowire your CrudRepo of book
    @Autowired
    BookRepository repository;

    @GetMapping("/book")
    public String bookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book";
    }

    @PostMapping("/book")
    public String bookSubmit(@ModelAttribute Book book, Model model) {
        System.out.println(book.toString());
        Book new_Book = new Book();
        repository.save(new_Book);
        model.addAttribute("new_book", new_Book);
        return "BookResult";
    }

}