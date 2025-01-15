package Trabajo_N9;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class LibrarySystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Author> authors = new ArrayList<>();
    private static List<Book> books = new ArrayList<>();
    private static List<BookCopy> copies = new ArrayList<>();
    private static List<User> users = new ArrayList<>();
    private static List<Loan> loans = new ArrayList<>();

    public static void main(String[] args) {
        try {
            boolean running = true;
            while (running) {
                System.out.println("\n=== SISTEMA DE BIBLIOTECA ===");
                System.out.println("1. Registrar Autor");
                System.out.println("2. Registrar Libro");
                System.out.println("3. Registrar Ejemplar");
                System.out.println("4. Registrar Usuario");
                System.out.println("5. Registrar Préstamo");
                System.out.println("6. Visualizar información del sistema");
                System.out.println("7. Salir");
                System.out.print("Seleccione una opción: ");

                int option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1:
                        registerAuthor();
                        break;
                    case 2:
                        registerBook();
                        break;
                    case 3:
                        registerCopy();
                        break;
                    case 4:
                        registerUser();
                        break;
                    case 5:
                        registerLoan();
                        break;
                    case 6:
                        displaySystemInfo();
                        break;
                    case 7:
                        running = false;
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
            }
        } catch (Exception e) {
            System.err.println("Error en el sistema: " + e.getMessage());
        }
    }

    private static void registerAuthor() throws ValidationException {
        System.out.println("\n=== REGISTRO DE AUTOR ===");
        System.out.print("Código del autor: ");
        String code = scanner.nextLine();
        System.out.print("Nombre del autor: ");
        String name = scanner.nextLine();

        Author author = new Author(code, name);
        author.validate();
        authors.add(author);
        System.out.println("Autor registrado exitosamente!");
    }

    private static void registerBook() throws ValidationException {
        if (authors.isEmpty()) {
            System.out.println("Debe registrar al menos un autor primero!");
            return;
        }

        System.out.println("\n=== REGISTRO DE LIBRO ===");
        System.out.println("Autores disponibles:");
        for (Author author : authors) {
            System.out.println("Código: " + author.getCode() + " - Nombre: " + author.name);
        }

        System.out.print("Código del autor: ");
        String authorCode = scanner.nextLine();
        Author selectedAuthor = authors.stream()
                .filter(a -> a.getCode().equals(authorCode))
                .findFirst()
                .orElseThrow(() -> new ValidationException("Autor no encontrado"));

        System.out.print("Código del libro: ");
        String code = scanner.nextLine();
        System.out.print("Título: ");
        String title = scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Editorial: ");
        String editorial = scanner.nextLine();
        System.out.print("Número de páginas: ");
        int pageCount = Integer.parseInt(scanner.nextLine());

        Book book = new Book(code, title, isbn, editorial, pageCount, selectedAuthor);
        book.validate();
        selectedAuthor.addBook(book);
        books.add(book);
        System.out.println("Libro registrado exitosamente!");
    }

    private static void registerCopy() throws ValidationException {
        if (books.isEmpty()) {
            System.out.println("Debe registrar al menos un libro primero!");
            return;
        }

        System.out.println("\n=== REGISTRO DE EJEMPLAR ===");
        System.out.println("Libros disponibles:");
        for (Book book : books) {
            System.out.println("Código: " + book.getCode() + " - Título: " + book.title);
        }

        System.out.print("Código del libro: ");
        String bookCode = scanner.nextLine();
        Book selectedBook = books.stream()
                .filter(b -> b.getCode().equals(bookCode))
                .findFirst()
                .orElseThrow(() -> new ValidationException("Libro no encontrado"));

        System.out.print("Código del ejemplar: ");
        String code = scanner.nextLine();
        System.out.print("Localización: ");
        String location = scanner.nextLine();

        BookCopy copy = new BookCopy(code, location, selectedBook);
        copy.validate();
        selectedBook.addCopy(copy);
        copies.add(copy);
        System.out.println("Ejemplar registrado exitosamente!");
    }

    private static void registerUser() throws ValidationException {
        System.out.println("\n=== REGISTRO DE USUARIO ===");
        System.out.print("Código del usuario: ");
        String code = scanner.nextLine();
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Dirección: ");
        String address = scanner.nextLine();
        System.out.print("Teléfono: ");
        String phone = scanner.nextLine();

        User user = new User(code, name, address, phone);
        user.validate();
        users.add(user);
        System.out.println("Usuario registrado exitosamente!");
    }

    private static void registerLoan() throws ValidationException {
        if (users.isEmpty() || copies.isEmpty()) {
            System.out.println("Debe haber usuarios y ejemplares registrados!");
            return;
        }

        System.out.println("\n=== REGISTRO DE PRÉSTAMO ===");
        System.out.println("Usuarios disponibles:");
        for (User user : users) {
            System.out.println("Código: " + user.getCode() + " - Nombre: " + user.name);
        }

        System.out.print("Código del usuario: ");
        String userCode = scanner.nextLine();
        User selectedUser = users.stream()
                .filter(u -> u.getCode().equals(userCode))
                .findFirst()
                .orElseThrow(() -> new ValidationException("Usuario no encontrado"));

        System.out.println("\nEjemplares disponibles:");
        for (BookCopy copy : copies) {
            System.out.println("Código: " + copy.getCode() + " - Localización: " + copy.location);
        }

        System.out.print("Código del ejemplar: ");
        String copyCode = scanner.nextLine();
        BookCopy selectedCopy = copies.stream()
                .filter(c -> c.getCode().equals(copyCode))
                .findFirst()
                .orElseThrow(() -> new ValidationException("Ejemplar no encontrado"));

        String loanCode = "L" + (loans.size() + 1);
        Loan loan = new Loan(loanCode, new Date(), selectedUser, selectedCopy);
        loan.validate();
        selectedUser.addLoan(loan);
        selectedCopy.addLoan(loan);
        loans.add(loan);
        System.out.println("Préstamo registrado exitosamente!");
    }

    private static void displaySystemInfo() {
        System.out.println("\n=== INFORMACIÓN DEL SISTEMA ===");

        System.out.println("\nAUTORES REGISTRADOS:");
        for (Author author : authors) {
            System.out.println("\nAutor: " + author.name + " (Código: " + author.getCode() + ")");
            System.out.println("Libros escritos:");
            for (Book book : author.getBooks()) {
                System.out.println("- " + book.title + " (ISBN: " + book.isbn + ")");
            }
        }

        System.out.println("\nLIBROS REGISTRADOS:");
        for (Book book : books) {
            System.out.println("\nLibro: " + book.title);
            System.out.println("ISBN: " + book.isbn);
            System.out.println("Editorial: " + book.editorial);
            System.out.println("Páginas: " + book.pageCount);
            System.out.println("Ejemplares:");
            for (BookCopy copy : book.getCopies()) {
                System.out.println("- Código: " + copy.getCode() + ", Localización: " + copy.location);
            }
        }

        System.out.println("\nUSUARIOS REGISTRADOS:");
        for (User user : users) {
            System.out.println("\nUsuario: " + user.name);
            System.out.println("Código: " + user.getCode());
            System.out.println("Dirección: " + user.address);
            System.out.println("Teléfono: " + user.phone);
            System.out.println("Préstamos activos:");
            for (Loan loan : user.getLoans()) {
                System.out.println("- Préstamo código: " + loan.getCode() +
                        ", Fecha: " + loan.loanDate);
            }
        }

        System.out.println("\nPRÉSTAMOS REGISTRADOS:");
        for (Loan loan : loans) {
            System.out.println("\nCódigo de préstamo: " + loan.getCode());
            System.out.println("Fecha de préstamo: " + loan.loanDate);
            if (loan.returnDate != null) {
                System.out.println("Fecha de devolución: " + loan.returnDate);
            }
        }
    }
}