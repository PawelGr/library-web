package com.pg.library.rest;

import com.pg.library.model.Author;
import com.pg.library.model.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookDto {

    private String title;
    private String isbn;
    private int numberOfPages;
    private boolean hardCover;
    private Author author;

    public static BookDto fromBook(Book book) {
        BookDto dto = new BookDto();
        Author author = book.getAuthor();
        dto.setTitle(book.getTitle());
        dto.setIsbn(book.getIsbn());
        dto.setNumberOfPages(book.getNumberOfPages());
        dto.setHardCover(book.isHardCover());
        dto.setAuthor(author);
        return dto;
    }

    public static Book fromDto(BookDto dto) {
        Book b = new Book();
        Author author = dto.getAuthor();
        b.setTitle(dto.getTitle());
        b.setIsbn(dto.getIsbn());
        b.setNumberOfPages(dto.getNumberOfPages());
        b.setHardCover(dto.isHardCover());
        b.setAuthor(author);
        return b;
    }

}
