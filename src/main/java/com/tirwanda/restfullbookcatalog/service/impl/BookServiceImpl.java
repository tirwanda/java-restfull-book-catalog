package com.tirwanda.restfullbookcatalog.service.impl;

import antlr.StringUtils;
import com.tirwanda.restfullbookcatalog.dto.BookCreateRequestDTO;
import com.tirwanda.restfullbookcatalog.dto.BookDetailResponseDTO;
import com.tirwanda.restfullbookcatalog.dto.BookListResponseDTO;
import com.tirwanda.restfullbookcatalog.dto.BookUpdateRequestDTO;
import com.tirwanda.restfullbookcatalog.entity.Book;
import com.tirwanda.restfullbookcatalog.exception.ResourceNotFoundException;
import com.tirwanda.restfullbookcatalog.repository.BookRepository;
import com.tirwanda.restfullbookcatalog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookListResponseDTO> findBookAll(String title) {
        title = ObjectUtils.isEmpty(title) ? "%" : "%" + title + "%";

        List<Book> bookResponses = bookRepository.findAllByTitleLike(title);

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
