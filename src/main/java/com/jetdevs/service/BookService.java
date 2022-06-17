package com.jetdevs.service;

import java.util.List;
import com.jetdevs.model.Book;
import com.jetdevs.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BookService extends BaseService {

    private BookRepository bookRepository;

    @Transactional
    public Book save(final Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public void saveAll(final List<Book> book) {
        bookRepository.saveAll(book);
    }

    @Transactional(readOnly = true)
    public List<Book> listAllByFile(final long id) {
        return bookRepository.findByFileId(id);
    }

}