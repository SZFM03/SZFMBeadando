package app.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LeckekonyvController {

    @FXML
    private AnchorPane leckekonyvPane;

    @FXML
    private Button visszabtn;

    @FXML
    private TabPane leckekonyvTabPane;

    @FXML
    private Tab leckekonyvTab;

    @FXML
    private GridPane leckekonyvTablazat;

    @FXML
    private TextArea neptunLeckekonyvText;

    @FXML
    private TextArea tantargyLeckekonyvText;

    @FXML
    private TextArea jegyLeckekonyvText;

    @FXML
    private TextArea felvetelekLeckekonyvText;

    @FXML
    private TextArea vizsgaLeckekonyvText;

    @FXML
    private TextArea felevLeckekonyvText;

    @FXML
    private Label neptunLeckekonyvLabel;

    @FXML
    private Label tantargyLeckekonyvLabel;

    @FXML
    private Label jegyLeckekonyvLabel;

    @FXML
    private Label felvetelekLeckekonyvLabel;

    @FXML
    private Label vizsgakLeckekonyvLabel;

    @FXML
    private Label felevLeckekonyvLabel;

    @FXML
    private Button leckekonyvbtn;


    public void vButtonAction(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == visszabtn) {

            try {

                Node node = (Node) mouseEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();

                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fooldal.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }


        }
    }

}

