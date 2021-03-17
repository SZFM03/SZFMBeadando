package app.controller;

import app.entity.User;
import app.repository.UserRepository;
import app.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    public static final String BELÉPÉSI_INFORMÁCIÓ = "Belépési információ";
    private final UserService userService = new UserService(new UserRepository());
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

    private final KilepVisszalep kilepes = new KilepVisszalep();

    private final AlertS alert = new AlertS();

    public void register(ActionEvent actionEvent) {
        try {
            if (!usernameField.getText().isBlank() && !passwordField.getText().isBlank()) {
                userService.saveUser(new User(usernameField.getText(), passwordField.getText()));

                alert.alert(BELÉPÉSI_INFORMÁCIÓ,"Sikeres regisztráció");

            }

        } catch (Exception e) {

            alert.alert(BELÉPÉSI_INFORMÁCIÓ,"A felhasználónév már foglalt!");
        }
    }

    public void login(ActionEvent actionEvent) {

        try {
            if (!usernameField.getText().isBlank() && !passwordField.getText().isBlank()) {

                boolean isRegistered = userService.isRegistered(new User(usernameField.getText(), passwordField.getText()));

                if (isRegistered) {


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

                    alert.alert(BELÉPÉSI_INFORMÁCIÓ,"A felhaszáló nincs regisztrálva, kérlek regisztrálj a belépéshez!");

                   // usernameField.clear();
                   // passwordField.clear();

                }
            } else if (usernameField.getText().isBlank()) {
                alert.alert(BELÉPÉSI_INFORMÁCIÓ,"Nem adtál meg felhasználónevet!");

            } else if (passwordField.getText().isBlank()) {

                alert.alert(BELÉPÉSI_INFORMÁCIÓ,"Nem adtál meg jelszót!");

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
