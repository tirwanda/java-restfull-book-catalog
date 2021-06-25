package com.tirwanda.restfullbookcatalog.service;

import com.tirwanda.restfullbookcatalog.dto.BookCreateRequestDTO;
import com.tirwanda.restfullbookcatalog.dto.BookDetailResponseDTO;
import com.tirwanda.restfullbookcatalog.dto.BookListResponseDTO;
import com.tirwanda.restfullbookcatalog.model.Book;

import java.util.List;


public interface BookService {
    public List<BookListResponseDTO> findBookAll();
    public void createNewBook(BookCreateRequestDTO dto);
    public BookDetailResponseDTO findBookDetail(String bookId);
}
