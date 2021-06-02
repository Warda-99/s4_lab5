package validation;

public interface Validator {
    void validate(String value);
    boolean isValid();
    String getMessage();
}
