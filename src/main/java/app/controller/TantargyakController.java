package app.controller;

import app.entity.Tantargy;
import app.repository.TantargyakRepository;
import app.service.TantargyakService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TantargyakController implements Initializable {

    public Button loadTantargyakButton;
    public TableView tantargyTable;
    public TableColumn targykTantargyColumn;
    public TableColumn targynTantargyColumn;
    public TableColumn kreditTantargyColumn;

    @FXML
    private Button visszabtn;

    @FXML
    private Button tantargyAddButton;

    @FXML
    private TextField targykodAddText;

    @FXML
    private TextField targyneveAddText;

    @FXML
    private TextField kreditAddText;

    @FXML
    private Button kilep;

    private final TantargyakService tantargyakService = new TantargyakService(new TantargyakRepository());

    private final KilepVisszalep kilepes = new KilepVisszalep();

    private final AlertS alert = new AlertS();

    public void vButtonAction(MouseEvent mouseEvent) {
        kilepes.kilepvisszalep(mouseEvent, visszabtn, "/fooldal.fxml");
    }

    public void registertantargy(ActionEvent actionEvent) {
        boolean isNegativ = false;
        try {

            if (!targykodAddText.getText().isBlank() && !targyneveAddText.getText().isBlank() && !kreditAddText.getText().isBlank()) {
                Tantargy tantargy = new Tantargy(targyneveAddText.getText(), targykodAddText.getText(), Integer.parseInt(kreditAddText.getText()));
                isNegativ = tantargyakService.pozitivKredit(tantargy);
                if (!isNegativ) {
                    tantargyakService.saveTantargyak(tantargy);
                    alert.alert("Tant??rgy hozz??ad??sa inform??ci??", "Sikeresen hozz??adt??l egy tant??rgyat!");
                }
            }
        } catch (Exception e) {
            if(kreditAddText.getText().matches("[0-9]+") && kreditAddText.getText().length() >= 1) {
                alert.alert("Tant??rgy hozz??ad??sa inform??ci??", "A megadott t??rgyk??d m??r foglalt!");
            }
        } if (targykodAddText.getText().isBlank()){
            alert.alert("Tant??rgy hozz??ad??sa inform??ci??", "Nem adt??l meg T??rgyk??dot!");
        } if(targyneveAddText.getText().isBlank()){
            alert.alert("Tant??rgy hozz??ad??sa inform??ci??", "Nem adtad meg a t??rgy nev??t!");
        } if (kreditAddText.getText().isBlank()){
            alert.alert("Tant??rgy hozz??ad??sa inform??ci??", "Nem adtad meg a kreditsz??mot!");
        }if(isNegativ){
            alert.alert("Tant??rgy hozz??ad??sa inform??ci??", "A kreditsz??m nem lehet negat??v!");
            kreditAddText.clear();
        } else if (!kreditAddText.getText().matches("[0-9]+") && kreditAddText.getText().length() >= 1) {
                alert.alert("Tant??rgy hozz??ad??sa inform??ci??", "Kreditnek csak sz??mot lehet megadni!");
                kreditAddText.clear();
            }
         else {
            targykodAddText.clear();
            targyneveAddText.clear();
            kreditAddText.clear();
        }
    }

    public void lekerdezMindenTantargy(ActionEvent actionEvent) {
        try {

            ObservableList<Tantargy> getTantargy = FXCollections.observableArrayList();
            List<Tantargy> tantargy = tantargyakService.MindenTantargy();
            getTantargy.addAll(tantargy);
            targynTantargyColumn.setCellValueFactory(new PropertyValueFactory<>("nev"));
            targykTantargyColumn.setCellValueFactory(new PropertyValueFactory<>("kod"));
            kreditTantargyColumn.setCellValueFactory(new PropertyValueFactory<>("kreditszam"));
            tantargyTable.setItems(getTantargy);
            if(getTantargy.isEmpty()){
                alert.alert("Minden tant??rgy inform??ci??", "Nincs az adatb??zisban egy tant??rgy sem!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void logout(MouseEvent mouseEvent){
        kilepes.kilepvisszalep(mouseEvent, kilep, "/home.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tantargyAddButton.setDefaultButton(true);
    }
}