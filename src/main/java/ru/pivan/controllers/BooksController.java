package ru.pivan.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.pivan.models.Book;
import ru.pivan.models.Person;
import ru.pivan.services.BooksServices;
import ru.pivan.services.PeopleServices;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksServices booksServices;
    private final PeopleServices peopleServices;

    @Autowired
    public BooksController(BooksServices booksServices, PeopleServices peopleServices) {
        this.booksServices = booksServices;
        this.peopleServices = peopleServices;
    }

    @GetMapping() // отправляется запрос на страницу с книгами
    public String index(Model model){
        model.addAttribute("books", booksServices.findAll());
        return "books/index";
    }

    @GetMapping("/{id}") // отправляется запрос на страницу с книгой
    public String show(Model model, @ModelAttribute("person") Person person, @PathVariable("id") int id){
        model.addAttribute("book", booksServices.findOne(id));
        model.addAttribute("people", peopleServices.findAll());
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
        booksServices.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit") // выполняется запрос на страницу с формой по изменению данных
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("book", booksServices.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}") // из формы выполняется запрос на изменение данных
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "books/edit";
        }
        booksServices.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}") // выполняется delete запрос на страницу с id, происходит удаление
    public String delete(@PathVariable("id") int id){
        booksServices.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        booksServices.releaseBook(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/add")
    public String add(@ModelAttribute("person") Person person, @PathVariable("id") int id){
        booksServices.add(id, person.getId());
        return "redirect:/books";
    }
}

