package app.controller;

import app.entity.Hallgato;
import app.repository.HallgatoRepository;
import app.service.HallgatoService;
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

public class HallgatoController implements Initializable {

    public Button hallgatobetoltes;
    public TableView hallgatotabla;
    public TableColumn oszlopnev;
    public TableColumn neptunoszlop;
    public TableColumn szuletesievoszlop;
    @FXML
    private Button visszabtn;
    @FXML
    private AnchorPane hallgatoPane;

    @FXML
    private TabPane hallgatoTabPane;

    @FXML
    private Tab keresoTab;

    @FXML
    private GridPane keresoTablazat;

    @FXML
    private ColumnConstraints kereso0oszlopTablazat;

    @FXML
    private ColumnConstraints kersso1oszlopTablazat;

    @FXML
    private ColumnConstraints kereso2oszlopTablazat;

    @FXML
    private RowConstraints kereso0SorTablazat;

    @FXML
    private RowConstraints kereso1SorTablazat;

    @FXML
    private RowConstraints kereso2SorTablazat;

    @FXML
    private RowConstraints kereso3SorTablazat;

    @FXML
    private Button leckekonyvKeresoButton;

    @FXML
    private Label nevKeresoLabel;

    @FXML
    private Label neptunKeresoLabel;

    @FXML
    private Label szuletesiEvKeresoLabel;

    @FXML
    private TextField nevKeresoText;

    @FXML
    private TextField neptunKeresoText;

    @FXML
    private TextField szuletesiEvKeresoText;

    @FXML
    private TextField keresoText;

    @FXML
    private Label neptunLabel;

    @FXML
    private Button loadKeresoButton;

    @FXML
    private Tab addTab;

    @FXML
    private GridPane addTablazat;

    @FXML
    private ColumnConstraints adat0oszlopTablazat;

    @FXML
    private ColumnConstraints adat1oszlopTablazat2;

    @FXML
    private ColumnConstraints adat2oszlopTablazat;

    @FXML
    private RowConstraints add0SorTalazat;

    @FXML
    private RowConstraints add1SorTablazat;

    @FXML
    private RowConstraints add2SorTablazat;

    @FXML
    private RowConstraints add3SorTablazat;

    @FXML
    private Label nevAddLabel;

    @FXML
    private Label neptunAddLabel;

    @FXML
    private Label szuletesiEvAddLabel;

    @FXML
    private Button addButton;

    @FXML
    private TextField nevAddText;

    @FXML
    private TextField neptunAddText;

    @FXML
    private TextField szuletesiEvAddText;

    @FXML
    private Button loadAddButton;

    @FXML
    private Tab torlesTab;

    @FXML
    private GridPane torlesTablazat;

    @FXML
    private ColumnConstraints torles0oszlopTablazat;

    @FXML
    private ColumnConstraints torles1oszlopTablazat;

    @FXML
    private ColumnConstraints torles2oszlopTablazat;

    @FXML
    private RowConstraints torles0SorTablazat;

    @FXML
    private RowConstraints torles1SorTablazat;

    @FXML
    private RowConstraints torles2SorTablazat;

    @FXML
    private Button deleteTorlesButton;

    @FXML
    private Label neptunTorlesLabel;

    @FXML
    private TextField neptunTorlesText;

    @FXML
    private Button loadTorlesButton;

    @FXML
    private Tab adatTab;

    @FXML
    private GridPane adatTabkazat;

    @FXML
    private ColumnConstraints adat1oszlopTablazat;

    @FXML
    private RowConstraints adat0SorTablazat;

    @FXML
    private RowConstraints adat1SorTablazat;

    @FXML
    private RowConstraints adat2SorTablazat;

    @FXML
    private RowConstraints adat3SorTablazat;

    @FXML
    private Label nevAdatLabel;

    @FXML
    private Label neptunAdatLabel;

    @FXML
    private Label szuletesiEvAdatLabel;

    @FXML
    private TextField nevAdatText;

    @FXML
    private TextField neptunAdatText;

    @FXML
    private TextField szuletesiEvAdatText;

    @FXML
    private Button saveAdatButton;

    @FXML
    private Button loadAdatButton;

    @FXML
    private TextField neptunbevitelText;

    @FXML
    private TextField neptunadatText;

    @FXML
    private TextField nevadatText;

    @FXML
    private TextField szuletesievadatText;

    @FXML
    private Button kilep;

    @FXML
    void addAStudent(ActionEvent event) {

    }

    @FXML
    void deleteAStudent(ActionEvent event) {

    }

    @FXML
    void saveChanges(ActionEvent event) {

    }

    private final KilepVisszalep oldalLeptetes = new KilepVisszalep();

    public void lButtonAction(MouseEvent mouseEvent) {
        oldalLeptetes.kilepvisszalep(mouseEvent, leckekonyvKeresoButton, "/leckekonyv.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private final HallgatoService hallgatoService = new HallgatoService(new HallgatoRepository());

    private final KilepVisszalep kilepes = new KilepVisszalep();

    private final AlertS alert = new AlertS();

    public void vButtonAction(MouseEvent mouseEvent) {
        kilepes.kilepvisszalep(mouseEvent, visszabtn, "/fooldal.fxml");
    }

    public void register(ActionEvent actionEvent) {
        try {
            if (!nevAddText.getText().isBlank() && !neptunAddText.getText().isBlank() && !szuletesiEvAddText.getText().isBlank()) {
                hallgatoService.saveHallgato(new Hallgato(nevAddText.getText(), szuletesiEvAddText.getText(), neptunAddText.getText()));

                alert.alert("Regisztrációs információ", "Sikeresen regisztráltál egy hallgatót!");

            }
        }catch (Exception e){
            alert.alert("Regisztrációs információ", "Ez a Neptun-kód már foglalt!");
        } if(nevAddText.getText().isBlank()){
            alert.alert("Regisztrációs információ", "Nem adtál meg Nevet!");
        } else if (szuletesiEvAddText.getText().isBlank()){
            alert.alert("Regisztrációs információ","Nem adtál meg születési évet!");
        } else if(neptunAddText.getText().isBlank()){
            alert.alert("Regisztrációs informáicó", "Nem adtál meg Neptun-kódot!");
        }
        nevAddText.clear();
        neptunAddText.clear();
        szuletesiEvAddText.clear();
    }

    public void deleteTorles(ActionEvent actionEvent) throws InterruptedException {
        if (!neptunTorlesText.getText().isBlank()){
            hallgatoService.deleteHallgato(neptunTorlesText.getText());
            neptunTorlesText.clear();

            alert.alert("Törlés információ", "Sikeresen törölted a hallgatót az adatbázisból!");
        }
    }

    public void modosit(ActionEvent actionEvent) {

        if(!nevadatText.getText().isBlank() && !neptunadatText.getText().isBlank() && !szuletesievadatText.getText().isBlank()){
            hallgatoService.modositHallgato(nevadatText.getText(), szuletesievadatText.getText(), neptunadatText.getText());
            nevadatText.clear();
            neptunadatText.clear();
            szuletesievadatText.clear();

            alert.alert("Módosítási információ", "Sikeres adatmódosítás!");
        }

    }

    public void logout(MouseEvent mouseEvent){

        kilepes.kilepvisszalep(mouseEvent, kilep, "/home.fxml");
    }

    public void lekerdez(ActionEvent actionEvent) {
        try {
            if (!neptunbevitelText.getText().isBlank()) {
                Hallgato hallgato = hallgatoService.lekerdezHallgato(neptunbevitelText.getText());
                nevadatText.setText(hallgato.getNev());
                szuletesievadatText.setText(hallgato.getSzuletesi_ev());
                neptunadatText.setText(hallgato.getNeptun_kod());
                neptunadatText.setDisable(true);

                neptunbevitelText.clear();
            }else if(keresoText.getText().isBlank()){
                alert.alert("Kereső információ","Nem adtál meg Neptun-kódot!");
            }
        }catch (Exception e){
            alert.alert("Kereső információ","A megadott Neptun-kód nem található az adatbázisban!");
        }

    }

    public void kereso(ActionEvent actionEvent) {
        try {
            if (!keresoText.getText().isBlank()) {
                Hallgato hallgato = hallgatoService.lekerdezHallgato(keresoText.getText());
                nevKeresoText.setText(hallgato.getNev());
                szuletesiEvKeresoText.setText(hallgato.getSzuletesi_ev());
                neptunKeresoText.setText(hallgato.getNeptun_kod());
                nevKeresoText.setDisable(true);
                szuletesiEvKeresoText.setDisable(true);
                neptunKeresoText.setDisable(true);

                keresoText.clear();
            } else if(keresoText.getText().isBlank()){
                alert.alert("Kereső információ","Nem adtál meg Neptun-kódot!");
            }
        }catch (Exception e){
            alert.alert("Kereső információ","A megadott Neptun-kód nem található az adatbázisban!");
        }

    }

    public void lekerdezMindenHallgato(ActionEvent actionEvent) {
        try {
            ObservableList<Hallgato> getHallgatok = FXCollections.observableArrayList();
            List<Hallgato> hallgatok = hallgatoService.MindenHallgato();
            if(hallgatok.isEmpty()){
                alert.alert("Minden hallgató infó", "Nincs az adatbázisban egy hallgató sem!");
            }
            getHallgatok.addAll(hallgatok);
            oszlopnev.setCellValueFactory(new PropertyValueFactory<>("nev"));
            szuletesievoszlop.setCellValueFactory(new PropertyValueFactory<>("szuletesi_ev"));
            neptunoszlop.setCellValueFactory(new PropertyValueFactory<>("neptun_kod"));
            hallgatotabla.setItems(getHallgatok);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
