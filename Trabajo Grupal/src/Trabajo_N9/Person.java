package Trabajo_N9;

abstract class Person implements EntityOperations {
    protected String code;
    protected String name;

    public Person(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void validate() throws ValidationException {
        if (code == null || code.trim().isEmpty()) {
            throw new ValidationException("Code cannot be empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Name cannot be empty");
        }
    }
}