package Trabajo_N9;

import java.util.ArrayList;
import java.util.List;

class Author extends Person {
    private List<Book> books;

    public Author(String code, String name) {
        super(code, name);
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }
}
