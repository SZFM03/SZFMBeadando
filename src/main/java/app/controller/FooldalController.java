package app.controller;

import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FooldalController{

    @FXML
    private Button hallgbtn;

    @FXML
    private Button targybtn;

    @FXML
    private Button leckekonyvbtn;

    @FXML
    private Button kilep;

    private final KilepVisszalep oldalLeptetes = new KilepVisszalep();

    public void hButtonAction(MouseEvent mouseEvent) {
        oldalLeptetes.kilepvisszalep(mouseEvent, hallgbtn, "/hallgato.fxml");
    }

    public void tButtonAction(MouseEvent mouseEvent) {
        oldalLeptetes.kilepvisszalep(mouseEvent, targybtn, "/tantargyak.fxml");
    }

    public void lButtonAction(MouseEvent mouseEvent) {
        oldalLeptetes.kilepvisszalep(mouseEvent, leckekonyvbtn, "/leckekonyv.fxml");
    }

    public void logout(MouseEvent mouseEvent){
        oldalLeptetes.kilepvisszalep(mouseEvent, kilep, "/home.fxml");
    }

}
