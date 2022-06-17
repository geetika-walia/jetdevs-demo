package com.jetdevs.repository;

import com.jetdevs.model.UserFiles;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends PagingAndSortingRepository<UserFiles, Long> {

}
