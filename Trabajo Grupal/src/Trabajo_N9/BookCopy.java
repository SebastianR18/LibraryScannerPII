package Trabajo_N9;

import java.util.ArrayList;
import java.util.List;

class BookCopy implements EntityOperations {
    private String code;
    public String location;
    private Book book;
    private List<Loan> loans;

    public BookCopy(String code, String location, Book book) {
        this.code = code;
        this.location = location;
        this.book = book;
        this.loans = new ArrayList<>();
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void validate() throws ValidationException {
        if (code == null || code.trim().isEmpty()) {
            throw new ValidationException("Copy code cannot be empty");
        }
        if (location == null || location.trim().isEmpty()) {
            throw new ValidationException("Location cannot be empty");
        }
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public List<Loan> getLoans() {
        return new ArrayList<>(loans);
    }
}
