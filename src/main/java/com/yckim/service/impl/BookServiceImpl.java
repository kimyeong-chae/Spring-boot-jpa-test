package com.yckim.service.impl;

import com.yckim.models.Author;
import com.yckim.models.Book;
import com.yckim.repository.AuthorRepository;
import com.yckim.repository.BookRepository;
import com.yckim.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transactionalTest() throws Exception {
        Author author = authorRepository.findById(1).get();
        Book book = new Book("이름", author);
        Book saveBook = bookRepository.save(book);
        saveBook.setTitle("이름1");

        innerTest(saveBook.getSeq());
    }

    private void innerTest(int seq) throws Exception {
        Book testBook = bookRepository.findById(seq).orElseThrow(Exception::new);
        System.out.println(testBook.toString());
    }
}
