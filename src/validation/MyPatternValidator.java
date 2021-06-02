package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyPatternValidator implements Validator {
    private final String regex;
    private final String message;
    private boolean valid;

    public MyPatternValidator(String regex, String message) {
        this.regex = regex;
        this.message = message;
    }

    @Override
    public void validate(String value) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        this.valid = matcher.matches();
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
