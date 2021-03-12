package app.controller;

import app.entity.Tantargyak;
import app.repository.TantargyakRepository;
import app.service.TantargyakService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class TantargyakController {

    @FXML
    private Button visszabtn;
    @FXML
    private Button hvisszabtn;

    @FXML
    private AnchorPane tantargyakPane;

    @FXML
    private TabPane tantargyakTabPane;

    @FXML
    private Tab tantargyakTab;

    @FXML
    private TextArea targykodText;

    @FXML
    private TextArea targyneveText;

    @FXML
    private TextArea kreditText;

    @FXML
    private Label targykodTantargyakLabel;

    @FXML
    private Label targyneveTantargyakLabel;

    @FXML
    private Label kreditLabel;

    @FXML
    private Button loadTantargyakButton;

    @FXML
    private Tab tantargyAddTab;

    @FXML
    private Button loadTantagyAddButton;

    @FXML
    private GridPane addTantargyTablazat;

    @FXML
    private ColumnConstraints tantargyAdd0oszlop;

    @FXML
    private ColumnConstraints tantargyAdd1oszlop;

    @FXML
    private ColumnConstraints tantargyAdd2oszlop;

    @FXML
    private RowConstraints tantargyAdd0sor;

    @FXML
    private RowConstraints tantargyAdd1sor;

    @FXML
    private RowConstraints tantargyAdd2sor;

    @FXML
    private RowConstraints tantargyAdd3Sor;

    @FXML
    private Label targykodAddLabel;

    @FXML
    private Label targyneveAddLabel;

    @FXML
    private Label kreditAddLabel;

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

    @FXML
    private Button hkilep;

    private final TantargyakService tantargyakService = new TantargyakService(new TantargyakRepository());

    private final KilepVisszalep kilepes = new KilepVisszalep();

    private final AlertS alert = new AlertS();

    public void vButtonAction(MouseEvent mouseEvent) {
        kilepes.kilepvisszalep(mouseEvent, visszabtn, "/fooldal.fxml");
    }

    public void registertantargy(ActionEvent actionEvent) {
        try {
            if (!targykodAddText.getText().isBlank() && !targyneveAddText.getText().isBlank() && !kreditAddText.getText().isBlank()) {
                tantargyakService.saveTantargyak(new Tantargyak(targyneveAddText.getText(), targykodAddText.getText(), kreditAddText.getText()));

                alert.alert("Tantárgy hozzáadása információ", "Sikeresen hozzáadtál egy tantárgyat!");

                targykodAddText.clear();
                targyneveAddText.clear();
                kreditAddText.clear();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } if (targykodAddText.getText().isBlank()){
            alert.alert("Tantárgy hozzáadása információ", "Nem adtál meg Tárgykódot!");
        } else if(targyneveAddText.getText().isBlank()){
            alert.alert("Tantárgy hozzáadása információ", "Nem adtad meg a tárgy nevét!");
        } else if (kreditAddText.getText().isBlank()){
            alert.alert("Tantárgy hozzáadása információ", "Nem adtad meg a kreditszámot!");
        }
    }

    public void logout(MouseEvent mouseEvent){
        kilepes.kilepvisszalep(mouseEvent, kilep, "/home.fxml");
    }

}