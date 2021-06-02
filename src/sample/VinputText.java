package sample;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import sample.Controller;
import validation.Validator;

public class VinputText extends HBox {
    private Controller controller;
    private TextField textField;
    private Label label;
    private ImageView imageView;
    private Image imgV = new Image("/obrazy/v.png");
    private Image imgX = new Image("/obrazy/x.png");
    private Validator validator;

    public VinputText(Controller controller, String name) {
        this.controller = controller;

        textField = new TextField();
        // Dodanie sluchacza do pola textowego, ktory sprawdza zmiany
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            textFieldListener(observable, oldValue, newValue);
        });

        // Dodanie elemetow
        getChildren().add(imageView = new ImageView());
        getChildren().add(textField);
        getChildren().add(label = new Label(name));
    }

    public void registerValidator(Validator validator) {
        this.validator = validator;
        validate(textField.getText());
    }

    private void textFieldListener(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        validate(newValue);
    }

    public boolean isValid() {
        return validator.isValid();
    }

    private void validate(String value) {
        if (validator == null) {
            return;
        }

        validator.validate(value);

        if (validator.isValid()) {
            imageView.setImage(imgV);
            Tooltip.install(imageView, null);
            controller.unlockButton();
        } else {
            imageView.setImage(imgX);
            Tooltip.install(imageView, new Tooltip(validator.getMessage()));
            controller.lockButton();
        }
    }
}
