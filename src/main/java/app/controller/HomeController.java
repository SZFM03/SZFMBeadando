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

                Alert alert5=new Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
                alert5.setTitle("Belépési információ");
                alert5.setHeaderText(null);
                alert5.setContentText("Sikeres regisztráció!");
                alert5.showAndWait();

            }
        }catch (Exception e){

            Alert alert6=new Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
            alert6.setTitle("Belépési információ");
            alert6.setHeaderText(null);
            alert6.setContentText("Ez a felhasználónév már foglalt!");
            alert6.showAndWait();

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

                    Alert alert4=new Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
                    alert4.setTitle("Belépési információ");
                    alert4.setHeaderText(null);
                    alert4.setContentText("Sikeres belépés");
                    alert4.showAndWait();

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

                    Alert alert=new Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
                    alert.setTitle("Belépési információ");
                    alert.setHeaderText(null);
                    alert.setContentText("A felhaszáló nincs regisztrálva, kérlek regisztrálj a belépéshez!");
                    alert.showAndWait();

                    usernameField.clear();
                    passwordField.clear();

                }
            }else if(usernameField.getText().isBlank()){

                Alert alert2=new Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
                alert2.setTitle("Belépési információ");
                alert2.setHeaderText(null);
                alert2.setContentText("Nem adtál meg felhasználónevet!");
                alert2.showAndWait();

            } else if(passwordField.getText().isBlank()){

                Alert alert3=new Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
                alert3.setTitle("Belépési információ");
                alert3.setHeaderText(null);
                alert3.setContentText("Nem adtál meg jelszót!");
                alert3.showAndWait();

            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
