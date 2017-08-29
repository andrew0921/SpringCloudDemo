package com.andrew;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@Repository
@RepositoryRestResource(path = "book", collectionResourceRel = "book")
public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {
	Book findByBookid(@Param("bookid") Integer bookid);
}
