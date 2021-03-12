package app.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class KilepVisszalep {


    public void kilepvisszalep(MouseEvent mouseEvent, Button kilep, String s) {
        kilep(mouseEvent, kilep, s);
    }

    private void kilep(MouseEvent mouseEvent, Button kilep, String s) {
        if (mouseEvent.getSource() == kilep) {

            try {

                Node node = (Node) mouseEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();

                Scene scene = new Scene(FXMLLoader.load(getClass().getResource(s)));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());

            }


        }
    }


}
