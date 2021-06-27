package com.tirwanda.restfullbookcatalog.service.impl;

import com.tirwanda.restfullbookcatalog.dto.BookCreateRequestDTO;
import com.tirwanda.restfullbookcatalog.dto.BookDetailResponseDTO;
import com.tirwanda.restfullbookcatalog.dto.BookListResponseDTO;
import com.tirwanda.restfullbookcatalog.dto.BookUpdateRequestDTO;
import com.tirwanda.restfullbookcatalog.entity.Book;
import com.tirwanda.restfullbookcatalog.exception.ResourceNotFoundException;
import com.tirwanda.restfullbookcatalog.repository.BookRepository;
import com.tirwanda.restfullbookcatalog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

//    public BookServiceImpl() {
//        Book book1 = new Book();
//        book1.setId("1");
//        book1.setTitle("Java Spring Boot");
//        book1.setAuthor("Edho Dwi");
//        book1.setDescription("Learn Java Spring Boot");
//
//        Book book2 = new Book();
//        book2.setId("2");
//        book2.setTitle("Java Data JPA");
//        book2.setAuthor("Edho Dwi");
//        book2.setDescription("Learn Java Data Jpa");
//
//        books.put(book1.getId(), book1);
//        books.put(book2.getId(), book2);
//    }

    @Override
    public List<BookListResponseDTO> findBookAll() {
        List<Book> bookResponses = bookRepository.findAll();

        return bookResponses.stream().map((book) -> {
            BookListResponseDTO dto = new BookListResponseDTO();
            dto.setId(book.getId());
            dto.setTitle(book.getTitle());
            dto.setAuthor(book.getAuthor());
            dto.setDescription(book.getDescription());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void createNewBook(BookCreateRequestDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setDescription(dto.getDescription());

        bookRepository.save(book);
    }

    @Override
    public BookDetailResponseDTO findBookDetail(Long bookId) {
        Book book = bookRepository.findById(bookId).
                orElseThrow(() -> new ResourceNotFoundException("Book not Found"));
        BookDetailResponseDTO dto = new BookDetailResponseDTO();

        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setDescription(book.getDescription());

        return dto;
    }

    @Override
    public void updateBook(Long bookId, BookUpdateRequestDTO dto) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not Found"));
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setDescription(dto.getDescription());

        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

}
