package ru.pivan.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pivan.models.Book;
import ru.pivan.repositories.BooksRepository;
import ru.pivan.repositories.PeopleRepository;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class BooksServices {

    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;

    public BooksServices(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll(){
        return booksRepository.findAll();
    }

    public Book findOne(int id){
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book){
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook){
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);
    }

    @Transactional
    public void releaseBook(int id){
        booksRepository.findById(id).ifPresent(book -> {
            book.setPerson(null);
        });
    }

    @Transactional
    public void add(int id, int person_id){
        booksRepository.findById(id).ifPresent(book -> {
            book.setPerson(peopleRepository.findById(person_id).orElse(null));
        });
    }

}
