package com.yckim;

import com.yckim.models.Author;
import com.yckim.models.Book;
import com.yckim.repository.AuthorRepository;
import com.yckim.repository.BookRepository;
import com.yckim.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Slf4j
@RestController
@SpringBootApplication
public class JpaTestApplication implements CommandLineRunner  {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    EntityManagerFactory emf;

    @Autowired
    BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(JpaTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Author author = authorRepository.save(new Author("yckim", 28));
        Book book1 = bookRepository.save(new Book("first book", author));
        Book book2 = bookRepository.save(new Book("second book", author));
        Book book3 = bookRepository.save(new Book("third book", author));
    }

    @GetMapping(path = "/authors")
    public List<Author> getAuthors()  {
        return authorRepository.findAll();
    }

    @PostMapping(path = "/authors", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public Author author(@RequestBody Author author) {
        Author saveAuthor = authorRepository.save(author);
        saveAuthor.setName(saveAuthor.getName()+"12");
        return saveAuthor;
    }

    @GetMapping(path = "/books")
    public List<Book> getBooks()  {
        return bookRepository.findAll();
    }

    @PostMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book book(@RequestBody Book book) {
        Book saveBook = bookRepository.save(book);
        return saveBook;
    }

    @GetMapping(path = "/test1")
    @Transactional
    public void writeTest() {
        long start = System.currentTimeMillis();
        for (int i = 1; i<=10000; i++) {
            Author author = new Author("TEST"+i, 100);
            authorRepository.save(author);
        }
        long end = System.currentTimeMillis();
        System.out.println( "실행 시간 : " + ( end - start )/1000.0 +"초");
    }

    @GetMapping(path = "/test2")
    public void readTest() {
        Author author1 = authorRepository.findById(1).get();
        Author author2 = authorRepository.findById(1).get();

        System.out.println(author1 == author2);
    }

    @GetMapping(path = "/test3")
    @Transactional
    public void updateTest() {
        Author author1 = authorRepository.findById(1).get();
        author1.setAge(11);
    }
    @GetMapping(path = "/test4")
    @Transactional
    public void transactionalTest() throws Exception {
        bookService.transactionalTest();
    }

}
