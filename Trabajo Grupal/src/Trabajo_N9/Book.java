package Trabajo_N9;

import java.util.ArrayList;
import java.util.List;

class Book implements EntityOperations {
    private String code;
    public String title;
    public String isbn;
    public String editorial;
    public int pageCount;
    private List<BookCopy> copies;
    private Author author;

    public Book(String code, String title, String isbn, String editorial, int pageCount, Author author) {
        this.code = code;
        this.title = title;
        this.isbn = isbn;
        this.editorial = editorial;
        this.pageCount = pageCount;
        this.author = author;
        this.copies = new ArrayList<>();
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void validate() throws ValidationException {
        if (code == null || code.trim().isEmpty()) {
            throw new ValidationException("Book code cannot be empty");
        }
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new ValidationException("ISBN cannot be empty");
        }
    }

    public void addCopy(BookCopy copy) {
        copies.add(copy);
    }

    public List<BookCopy> getCopies() {
        return new ArrayList<>(copies);
    }
}