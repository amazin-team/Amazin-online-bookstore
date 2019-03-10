package amazin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragmentController {

    // Autowire your CrudRepo of book
    @Autowired
    BookRepository repository;

    @GetMapping("/bookbrowser")
    public String bookPage() {
        return "fragments/_bookBrowser.html";
    }

}
