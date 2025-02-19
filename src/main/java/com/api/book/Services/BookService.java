package com.api.book.Services;

import java.util.ArrayList;
import java.util.Collections;

//******************** DATABSE USED HERE IS : springbootjpa ****************************************

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.book.Entities.Book;
import com.api.book.dao.BookRepository;

@Component // you can also use @Service
public class BookService {

	@Autowired
	private BookRepository bookRepo;

	public List<Book> getAllBooks() {
		List<Book> books = (List<Book>) this.bookRepo.findAll();
		return books;
	}

	// get a particular book by it's id
	public Optional<Book> getBookById(int id) {
		Optional<Book> book = null;
		try {
			book = this.bookRepo.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	// get one or more book(s) by price
	public List<Book> getBooksByPrice(Long price) {
		return bookRepo.findByPrice(price).orElse(Collections.emptyList());
	}

	// get books within a certain price range
	public List<Book> getBooksByPriceRange(Long minPrice, Long maxPrice) {
		Optional<List<Book>> books = bookRepo.findBooksInRange(minPrice, maxPrice);
		return books.orElse(Collections.emptyList()); // Return empty list if no books are found
	}

	//------------------------------------------------------------------------------------
	
	// get books above a certain price
	public List<Book> getBooksAbovePrice(Long minPrice) {
		Optional<List<Book>> booksAbovePrice = bookRepo.findBooksAbovePrice(minPrice);
		return booksAbovePrice.orElse(Collections.emptyList()); // Return empty list if no books are found
	}
	
	// get books below a certain price
		public List<Book> getBooksBelowPrice(Long maxPrice) {
			Optional<List<Book>> booksBelowPrice = bookRepo.findBooksBelowPrice(maxPrice);
			return booksBelowPrice.orElse(Collections.emptyList()); // Return empty list if no books are found
		}

	// add a book into inventory
	public void addBook(Book b) {
		bookRepo.save(b);
	}

	// add more than one book into inventory
	public List<Book> addBooks(List<Book> bookList) {
		bookRepo.saveAll(bookList);
		List<Book> books = (List<Book>) this.bookRepo.findAll();
		return books;
	}

	// delete a book
	public void deleteBook(int id) {
		bookRepo.deleteById(id);
		// bookRepo.deleteAll(); //to delete all the books from inventory
	}

	public Book updateBook(Book book, int bid) {
		book.setId(bid); // in order to make sure that the respective book with it's id gets saved(or you
							// can say updated)
		bookRepo.save(book);
		return book;
	}
}
