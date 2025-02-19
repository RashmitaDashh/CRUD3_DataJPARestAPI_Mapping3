package com.api.book.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.book.Entities.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	// Custom method to find books(s) by id
	public Optional<List<Book>> findByPrice(Long price);

	// Native SQL query
	@Query(value = "SELECT * FROM book_details WHERE price BETWEEN :minPrice AND :maxPrice", nativeQuery = true)
	public Optional<List<Book>> findBooksInRange(@Param("minPrice") Long minPrice, @Param("maxPrice") Long maxPrice);

	// JPQL query
	@Query("SELECT b FROM Book b WHERE b.price > :price")
	public Optional<List<Book>> findBooksAbovePrice(@Param("price") Long price);

	// JPQL query
	// Find books with price less than a given value
	@Query("SELECT b FROM Book b WHERE b.price < :price")
	public Optional<List<Book>> findBooksBelowPrice(@Param("price") Long price);

}
