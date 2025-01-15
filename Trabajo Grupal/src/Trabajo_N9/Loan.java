package Trabajo_N9;

import java.util.Date;

class Loan implements EntityOperations {
    private String code;
    public Date loanDate;
    public Date returnDate;
    private User user;
    private BookCopy copy;

    public Loan(String code, Date loanDate, User user, BookCopy copy) {
        this.code = code;
        this.loanDate = loanDate;
        this.user = user;
        this.copy = copy;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void validate() throws ValidationException {
        if (code == null || code.trim().isEmpty()) {
            throw new ValidationException("Loan code cannot be empty");
        }
        if (loanDate == null) {
            throw new ValidationException("Loan date cannot be empty");
        }
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
