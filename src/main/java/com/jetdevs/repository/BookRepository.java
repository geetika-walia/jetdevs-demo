package com.jetdevs.repository;

import java.util.List;
import com.jetdevs.model.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    List<Book> findByFileId(long id);

}
