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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

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

    private final TantargyakService tantargyakService = new TantargyakService(new TantargyakRepository());

    private final KilepVisszalep kilepes = new KilepVisszalep();

    private final AlertS alert = new AlertS();

    public void vButtonAction(MouseEvent mouseEvent) {
        kilepes.kilepvisszalep(mouseEvent, visszabtn, "/fooldal.fxml");
    }

    public void registertantargy(ActionEvent actionEvent) {
        try {
            if (!targykodAddText.getText().isBlank() && !targyneveAddText.getText().isBlank() && !kreditAddText.getText().isBlank()) {
                tantargyakService.saveTantargyak(new Tantargy(targyneveAddText.getText(), targykodAddText.getText(), kreditAddText.getText()));

                alert.alert("Tantárgy hozzáadása információ", "Sikeresen hozzáadtál egy tantárgyat!");

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
        targykodAddText.clear();
        targyneveAddText.clear();
        kreditAddText.clear();
    }

    public void lekerdezMindenTantargy(ActionEvent actionEvent) {
        try {
            ObservableList<Tantargy> getTantargy = FXCollections.observableArrayList();
            List<Tantargy> tantargy = tantargyakService.MindenTantargy();
            getTantargy.addAll(tantargy);
            targynTantargyColumn.setCellValueFactory(new PropertyValueFactory<>("nev"));
            targykTantargyColumn.setCellValueFactory(new PropertyValueFactory<>("kreditszam"));
            kreditTantargyColumn.setCellValueFactory(new PropertyValueFactory<>("kod"));
            tantargyTable.setItems(getTantargy);
            if(getTantargy.isEmpty()){
                alert.alert("Minden tantárgy információ", "Nincs az adatbázisban egy tantárgy sem!");
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