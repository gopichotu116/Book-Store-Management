package com.bookStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookStore.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer>  {

	@Query("SELECT b FROM Book b WHERE b.name LIKE %:value%")
	List<Book> findByValue(String value);

}
