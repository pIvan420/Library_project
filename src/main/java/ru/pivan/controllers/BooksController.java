package ru.pivan.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.pivan.dao.BookDAO;
import ru.pivan.dao.PersonDAO;
import ru.pivan.models.Book;
import ru.pivan.models.Person;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping() // отправляется запрос на страницу с книгами
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}") // отправляется запрос на страницу с книгой
    public String show(Model model, @ModelAttribute("person") Person person, @PathVariable("id") int id){
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("reader", bookDAO.get_reader(id));
        model.addAttribute("people", personDAO.index());
        return "books/show";
    }

    @GetMapping("/new") // отправляется get запрос на страницу book/new с формой заполнения
    public String newBook(Model model){
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping() // из формы выполняется post запрос на страницу book и вызывается этот метод
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "books/new";
        }
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit") // выполняется запрос на страницу с формой по изменению данных
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}") // из формы выполняется запрос на изменение данных
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "books/edit";
        }
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}") // выполняется delete запрос на страницу с id, происходит удаление
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        bookDAO.release_book(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/add")
    public String add(@ModelAttribute("person") Person person, @PathVariable("id") int id){
        bookDAO.add(id, person.getId());
        return "redirect:/books";
    }
}

