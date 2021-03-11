package app.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FooldalController{

    @FXML
    private Button hallgbtn;

    @FXML
    private Button targybtn;

    @FXML
    private Button leckekonyvbtn;

    public void hButtonAction(MouseEvent mouseEvent) {

        if (mouseEvent.getSource() == hallgbtn) {

            try {


                    Node node = (Node) mouseEvent.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/hallgato.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }


        }


    }

    public void tButtonAction(MouseEvent mouseEvent) {

        if (mouseEvent.getSource() == targybtn) {

            try {


                Node node = (Node) mouseEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();

                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/tantargyak.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }


        }
    }

    public void lButtonAction(MouseEvent mouseEvent) {

        if (mouseEvent.getSource() == leckekonyvbtn) {

            try {


                Node node = (Node) mouseEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();

                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/leckekonyv.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }


        }
    }


}
