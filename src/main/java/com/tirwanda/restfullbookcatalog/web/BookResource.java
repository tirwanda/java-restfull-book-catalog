package com.tirwanda.restfullbookcatalog.web;

import com.tirwanda.restfullbookcatalog.dto.BookCreateRequestDTO;
import com.tirwanda.restfullbookcatalog.dto.BookDetailResponseDTO;
import com.tirwanda.restfullbookcatalog.dto.BookListResponseDTO;
import com.tirwanda.restfullbookcatalog.dto.BookUpdateRequestDTO;
import com.tirwanda.restfullbookcatalog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class BookResource {

    @Autowired
    private BookService bookService;

    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<BookDetailResponseDTO> findBookDetail(@PathVariable("bookId") String bookId) {
        BookDetailResponseDTO dto = bookService.findBookDetail(bookId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/book")
    public ResponseEntity<Void> createNewBook(@RequestBody BookCreateRequestDTO dto) throws URISyntaxException {
        bookService.createNewBook(dto);
        return ResponseEntity.created(new URI("/book")).build();
    }

    @GetMapping("/book")
    public ResponseEntity<List<BookListResponseDTO>> findBookAll() {
        List<BookListResponseDTO> dtos = bookService.findBookAll();
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/book/{bookId}")
    public ResponseEntity<Void> updateBook(@PathVariable("bookId") String bookId, @RequestBody BookUpdateRequestDTO dto) {
        bookService.updateBook(bookId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("book/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") String bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }


}
