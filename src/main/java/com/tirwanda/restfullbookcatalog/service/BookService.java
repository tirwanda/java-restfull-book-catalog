package com.tirwanda.restfullbookcatalog.service;

import com.tirwanda.restfullbookcatalog.dto.BookCreateRequestDTO;
import com.tirwanda.restfullbookcatalog.dto.BookDetailResponseDTO;
import com.tirwanda.restfullbookcatalog.dto.BookListResponseDTO;
import com.tirwanda.restfullbookcatalog.dto.BookUpdateRequestDTO;

import java.util.List;


public interface BookService {
    public List<BookListResponseDTO> findBookAll(String title);
    public void createNewBook(BookCreateRequestDTO dto);
    public BookDetailResponseDTO findBookDetail(Long bookId);
    public void updateBook(Long bookId ,BookUpdateRequestDTO dto);
    public void deleteBook(Long bookId);
}
