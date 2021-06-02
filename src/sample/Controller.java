package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import validation.MyPattern;
import validation.MyPatternValidator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class Controller {

    @FXML Button confirmButton;
    @FXML VBox validationVBox;

    // Lista kontrolek uzywanych przy walidacji.
    private List<VinputText> inputTextsList;

    @FXML
    public void initialize() throws ClassNotFoundException {
        Class<?> c = Class.forName("validation.Place");
        createFieldsControls(c);
    }

    // Metoda do tworzenia kontrolki dla danej klasy
    private void createFieldsControls(Class<?> c) {
        inputTextsList = new ArrayList<>();

        for (Field field : c.getDeclaredFields()) {

            MyPattern pattern = field.getAnnotation(MyPattern.class);     // Wyciaganie adnotacji
            VinputText vinputText = new VinputText(this, " <<< " + field.getName());    // Tworzenie pola
            MyPatternValidator validator = new MyPatternValidator(pattern.regex(), pattern.message());  // Tworzenie walidatora
            vinputText.registerValidator(validator);    // Dodajemy walidator do pola

            validationVBox.getChildren().add(vinputText);   // Dodaie pole
            inputTextsList.add(vinputText); // Dodanie pole do listy
        }
    }

    // Metody do blokowania i odblokowania przycisku
    public void unlockButton() { confirmButton.setDisable(false); }
    public void lockButton() { confirmButton.setDisable(true); }

    // Wyswietla aletr po wcisnieciu przycisku
    public void confirmButton_Click(ActionEvent e) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText("Walidacja poprawna");
        a.show();
    }
}
