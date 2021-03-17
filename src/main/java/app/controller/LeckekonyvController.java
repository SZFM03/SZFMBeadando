package app.controller;

import app.entity.Hallgato;
import app.entity.Tantargy;
import app.repository.HallgatoRepository;
import app.repository.LeckekonyvRepository;
import app.repository.TantargyakRepository;
import app.service.HallgatoService;
import app.service.LeckekonyvService;
import app.service.TantargyakService;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class LeckekonyvController implements Initializable{

    @FXML
    public ComboBox TantargyComboBox;
    @FXML
    private AnchorPane leckekonyvPane;

    @FXML
    private TabPane leckekonyvTabPane;

    @FXML
    private Tab leckekonyvTab;

    @FXML
    private Button leckekonyvbtn;

    @FXML
    private TableColumn<?, ?> neptunlkColumn;

    @FXML
    private TableColumn<?, ?> targylkColumn;

    @FXML
    private TableColumn<?, ?> jegylkColumn;

    @FXML
    private TableColumn<?, ?> felvetellkColumn;

    @FXML
    private TableColumn<?, ?> vizsgalkColumn;

    @FXML
    private TableColumn<?, ?> felevlkColumn;

    @FXML
    private TextField neptunkodbevitel;

    @FXML
    private Button targylekerdez;

    @FXML
    private TextField hallgatoTargyfelvetelText;

    @FXML
    private Button listafrissitese;

    @FXML
    private Button targyhozzaadas;

    @FXML
    private TextField neptunJegylkText;

    @FXML
    private TextField targyJegylktext;

    @FXML
    private Button lekerdezJegyBtn;

    @FXML
    private TextField nevJegyText;

    @FXML
    private TextField neptunJegyText;

    @FXML
    private TextField targyJegyText;

    @FXML
    private TextField jegyJegyText;

    @FXML
    private Button modositJegyBtn;

    @FXML
    private Button visszabtn;

    @FXML
    private Button kilep;

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
    private TextArea neptunTargyfelvetelText;

    @FXML
    private TextArea felvettarea;


    public Button targyaklistazasa;

    public TableView targyfelvetelTable1;
    public TableColumn targykod;
    public TableColumn targynev;
    public TableColumn kredit;

    public TableView targyfelvetelTable2;
    public TableColumn targykodfelvetel;
    public TableColumn targynevfelvetel;
    public TableColumn kreditoszlop;

    @FXML
    private TableView<Tantargy> taview = new TableView<>();

    private final KilepVisszalep kilepes = new KilepVisszalep();

    private final TantargyakService tantargyakService = new TantargyakService(new TantargyakRepository());

    private final HallgatoService hallgatoService = new HallgatoService(new HallgatoRepository());

    private final HallgatoRepository hallgatoRepository = new HallgatoRepository();

    public void vButtonAction(MouseEvent mouseEvent) {
        kilepes.kilepvisszalep(mouseEvent, visszabtn, "/fooldal.fxml");
    }

    public void logout(MouseEvent mouseEvent){
        kilepes.kilepvisszalep(mouseEvent, kilep, "/home.fxml");
    }

    private final AlertS alert = new AlertS();

    private final LeckekonyvService leckekonyvService = new LeckekonyvService(new LeckekonyvRepository());

    public void kereso(ActionEvent actionEvent) {
        try {
            if (!neptunkodbevitel.getText().isBlank()) {
                Hallgato hallgato = hallgatoService.lekerdezHallgato(neptunkodbevitel.getText());
                hallgatoTargyfelvetelText.setText(hallgato.getNev());
                hallgatoTargyfelvetelText.setDisable(true);
                List<Tantargy> getTantargy = hallgato.getTantargyak();

                ObservableList<Tantargy> getTantargy2 = FXCollections.observableArrayList();
                getTantargy2.addAll(getTantargy);
                targykod.setCellValueFactory(new PropertyValueFactory<>("kod"));
                targynev.setCellValueFactory(new PropertyValueFactory<>("nev"));
                kredit.setCellValueFactory(new PropertyValueFactory<>("kreditszam"));

                targyfelvetelTable1.setItems(getTantargy2);

            } else if(neptunkodbevitel.getText().isBlank()){
                alert.alert("Kereső információ","Nem adtál meg Neptun-kódot!");
            }
        }catch (Exception e){
            alert.alert("Kereső információ", "A megadott neptun-kód nem található az adatbázisban!");
        }

    }

    public void lekerdezMindenTantargy(ActionEvent actionEvent) {

        List<Tantargy> getTantargy = tantargyakLekerdez();
        ObservableList<Tantargy> getTantargy2 = FXCollections.observableArrayList();
        getTantargy2.addAll(getTantargy);
        targynevfelvetel.setCellValueFactory(new PropertyValueFactory<>("nev"));
        kreditoszlop.setCellValueFactory(new PropertyValueFactory<>("kreditszam"));
        targykodfelvetel.setCellValueFactory(new PropertyValueFactory<>("kod"));

        targyfelvetelTable2.setItems(getTantargy2);
    }

    private List<Tantargy> tantargyakLekerdez() {
        try {
            List<Tantargy> tantargy = tantargyakService.MindenTantargy();
            if(tantargy.isEmpty()){
                alert.alert("Minden tantárgy információ", "Nincs az adatbázisban egy tantárgy sem!");
            }
            return new ArrayList<>(tantargy);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void kivalasztottadatatadasa(ActionEvent actionEvent) {
        ObservableList<Tantargy> getTantargyakbe = targyfelvetelTable2.getSelectionModel().getSelectedItems();
        List<Tantargy> getTantargyak = new ArrayList<>(getTantargyakbe);

        Hallgato hallgato = hallgatoService.lekerdezHallgato(neptunkodbevitel.getText());

        List<Tantargy> hallgatoTantargyak = hallgato.getTantargyak();
        System.out.println(hallgato.getTantargyak().get(0));
        if(!hallgatoTantargyak.contains(getTantargyak.get(0))){
            String neptun_kod = neptunkodbevitel.getText();

            hallgatoRepository.saveTantargyak(neptun_kod, getTantargyak);
            ObservableList<Tantargy> getTantargy2 = FXCollections.observableArrayList();
            getTantargy2.addAll(hallgato.getTantargyak());
            targykod.setCellValueFactory(new PropertyValueFactory<>("kod"));
            targynev.setCellValueFactory(new PropertyValueFactory<>("nev"));
            kredit.setCellValueFactory(new PropertyValueFactory<>("kreditszam"));

            targyfelvetelTable1.setItems(getTantargy2);
        }else{
            alert.alert("Tantárgy felvétel!", "Ez a tantárgy már hozzá van rendelve a hallgatóhoz!");
        }


    }

    public void töröl(ActionEvent actionEvent) {
        ObservableList<Tantargy> getTantargyakbe = targyfelvetelTable1.getSelectionModel().getSelectedItems();
        Hallgato hallgato = hallgatoService.lekerdezHallgato(neptunkodbevitel.getText());

        hallgatoRepository.removeTantargyak(neptunkodbevitel.getText(), getTantargyakbe);
        ObservableList<Tantargy> getTantargy2 = FXCollections.observableArrayList();
        getTantargy2.addAll(hallgato.getTantargyak());
        targykod.setCellValueFactory(new PropertyValueFactory<>("kod"));
        targynev.setCellValueFactory(new PropertyValueFactory<>("nev"));
        kredit.setCellValueFactory(new PropertyValueFactory<>("kreditszam"));

        targyfelvetelTable1.setItems(getTantargy2);
    }


    public void lekerdez(ActionEvent actionEvent) {
        Hallgato hallgato = hallgatoService.lekerdezHallgato(neptunJegylkText.getText());

        nevJegyText.setText(hallgato.getNev());
        nevJegyText.setDisable(true);
        neptunJegyText.setText(hallgato.getNeptun_kod());
        neptunJegyText.setDisable(true);
        System.out.println(felvettTargyakBoxhoz());


    }


    public List<String> felvettTargyakBoxhoz (){
        Hallgato hallgato = hallgatoService.lekerdezHallgato(neptunJegylkText.getText());
        List<Tantargy> tantargyak = hallgato.getTantargyak();
        List<String> tantargyNevLista = new ArrayList<>();
        for(var tantargy : tantargyak){
            tantargyNevLista.add(tantargy.getNev());
        }
        return tantargyNevLista;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       TantargyComboBox.getItems().addAll("");
    }
}
