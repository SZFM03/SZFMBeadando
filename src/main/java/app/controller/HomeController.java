package app.controller;

import app.entity.User;
import app.repository.UserRepository;
import app.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    public TextField usernameField;

    @FXML
    public TextField passwordField;

    @FXML
    public Button loginButton;

    @FXML
    public Button registerButton;

    @FXML
    public Label usernameLabel;

    private final UserService userService = new UserService(new UserRepository());

    public void register(ActionEvent actionEvent) {
        try {
            if (!usernameField.getText().isBlank() && !passwordField.getText().isBlank()) {
                userService.saveUser(new User(usernameField.getText(), passwordField.getText()));

                alert("Sikeres regisztráció!");

            }
        }catch (Exception e){

            Alert alert6=new Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
            alert6.setTitle("Belépési információ");
            alert6.setHeaderText(null);
            alert6.setContentText("Ez a felhasználónév már foglalt!");
            alert6.showAndWait();

           }
    }

    public void alert(String message){
        Alert alert6=new Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert6.setTitle("Belépési információ");
        alert6.setHeaderText(null);
        alert6.setContentText(message);
        alert6.showAndWait();
    }

    public void login(ActionEvent actionEvent) {

        try {
            if (!usernameField.getText().isBlank() && !passwordField.getText().isBlank()) {

                boolean isRegistered = userService.isRegistered(new User(usernameField.getText(), passwordField.getText()));

                if (isRegistered) {
                    alert("Sikeres belépés");

                    if (actionEvent.getSource() == loginButton) {

                        try {
                            Node node = (Node) actionEvent.getSource();
                            Stage stage = (Stage) node.getScene().getWindow();
                            stage.close();

                            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fooldal.fxml")));
                            stage.setScene(scene);
                            stage.show();

                        } catch (IOException ex) {
                            System.err.println(ex.getMessage());
                        }
                    }
                } else {

                    alert("A felhaszáló nincs regisztrálva, kérlek regisztrálj a belépéshez!");

                    usernameField.clear();
                    passwordField.clear();

                }
            }else if(usernameField.getText().isBlank()){
                alert("Nem adtál meg felhasználónevet!");

            } else if(passwordField.getText().isBlank()){

               alert("Nem adtál meg jelszót!");

            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
