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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private Pane BasePane;

    @FXML
    private Pane Alert;

    @FXML
    private Label alertText;

    @FXML
    public TextField usernameField;

    @FXML
    public TextField passwordField;

    @FXML
    public Button loginButton;

    @FXML
    public Button registerButton;

    @FXML
    public Button AlertButton;

    @FXML
    public Label usernameLabel;

    private final UserService userService = new UserService(new UserRepository());

    public void register(ActionEvent actionEvent) {
        try {
            if (!usernameField.getText().isBlank() && !passwordField.getText().isBlank()) {
                userService.saveUser(new User(usernameField.getText(), passwordField.getText()));
                BasePane.setDisable(true);
                BasePane.setOpacity(0.3);
                Alert.setVisible(true);
                alertText.setText("Sikeres regisztráció!");
            }
        }catch (Exception e){
            BasePane.setDisable(true);
            BasePane.setOpacity(0.3);
            Alert.setVisible(true);
            alertText.setText("Ez a felhasználó már foglalt!");
        }
    }

    @FXML
    void AlertButton(ActionEvent event) {
        BasePane.setDisable(false);
        BasePane.setOpacity(1);
        Alert.setVisible(false);
        alertText.setText("");
    }

    public void login(ActionEvent actionEvent) {

        try {
            if (!usernameField.getText().isBlank() && !passwordField.getText().isBlank()) {

                boolean isRegistered = userService.isRegistered(new User(usernameField.getText(), passwordField.getText()));

                if (isRegistered) {
                    System.out.println("User is registered!");

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
                    BasePane.setDisable(true);
                    BasePane.setOpacity(0.3);
                    Alert.setVisible(true);
                    alertText.setText("A felhaszáló nincs regisztrálva, kérlek kattints a regisztrálás gombra!");
                }
            }else if(usernameField.getText().isBlank()){
                BasePane.setDisable(true);
                BasePane.setOpacity(0.3);
                Alert.setVisible(true);
                alertText.setText("Nem adtál meg felhasználónevet!");
            } else if(passwordField.getText().isBlank()){
                BasePane.setDisable(true);
                BasePane.setOpacity(0.3);
                Alert.setVisible(true);
                alertText.setText("Nem adtál meg jelszót!");
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
