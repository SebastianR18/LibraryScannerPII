package Trabajo_N9;

import java.util.ArrayList;
import java.util.List;

class User extends Person {
    public String address;
    public String phone;
    private List<Loan> loans;

    public User(String code, String name, String address, String phone) {
        super(code, name);
        this.address = address;
        this.phone = phone;
        this.loans = new ArrayList<>();
    }

    @Override
    public void validate() throws ValidationException {
        super.validate();
        if (phone == null || phone.trim().isEmpty()) {
            throw new ValidationException("Phone cannot be empty");
        }
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public List<Loan> getLoans() {
        return new ArrayList<>(loans);
    }
}

