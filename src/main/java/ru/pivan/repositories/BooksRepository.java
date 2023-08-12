package ru.pivan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pivan.models.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
}
