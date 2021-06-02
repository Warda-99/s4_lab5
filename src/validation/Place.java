package validation;

public class Place {
    @MyPattern(regex = "^[0-9]{2}-[0-9]{3}$", message = "Kod pocztowy ma niepoprawny format")
    private String zipCode;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
