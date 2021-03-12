package app.controller;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertS {

    public void alert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
