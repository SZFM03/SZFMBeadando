package app.controller;

import app.entity.Hallgato;
import app.entity.Jegy;
import app.entity.Leckekonyv;
import app.entity.Tantargy;
import app.repository.HallgatoRepository;
import app.repository.JegyRepository;
import app.repository.LeckekonyvRepository;
import app.repository.TantargyakRepository;
import app.service.HallgatoService;
import app.service.JegyService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LeckekonyvController implements Initializable {

    @FXML
    public ComboBox TantargyComboBox;

    @FXML
    public ComboBox jegybecombo;

    @FXML
    private Label jegylabel;

    @FXML
    private Button leckekonyvbtn;

    @FXML
    private TextField neptunkodbevitel;

    @FXML
    private Button targylekerdez;

    @FXML
    private TextField hallgatoTargyfelvetelText;

    @FXML
    private TextField neptunJegylkText;

    @FXML
    private Button lekerdezJegyBtn;

    @FXML
    private TextField nevJegyText;

    @FXML
    private TextField neptunJegyText;

    @FXML
    private TextField neptunbelk;

    @FXML
    private TextField neptunatlag;

    @FXML
    private TextField atlaghatar;

    @FXML
    private TextField hallgneve;

    @FXML
    private TextField besorolas;

    @FXML
    private TextField hallgatoatlag;

    @FXML
    private Button visszabtn;

    @FXML
    private Button kilep;

    public Button targyaklistazasa;

    @FXML
    public Button tanatlag;

    public TableView targyfelvetelTable1;
    public TableColumn targykod;
    public TableColumn targynev;
    public TableColumn kredit;

    public TableView targyfelvetelTable2;
    public TableColumn targykodfelvetel;
    public TableColumn targynevfelvetel;
    public TableColumn kreditoszlop;

    @FXML
    public TableView leckekonyvtable;
    @FXML
    private TableColumn neptunlkColumn;

    @FXML
    private TableColumn targylkColumn;

    @FXML
    private TableColumn jegylkColumn;

    private final KilepVisszalep kilepes = new KilepVisszalep();

    private final TantargyakService tantargyakService = new TantargyakService(new TantargyakRepository());

    private final HallgatoService hallgatoService = new HallgatoService(new HallgatoRepository());

    private final HallgatoRepository hallgatoRepository = new HallgatoRepository();

    private final LeckekonyvRepository leckekonyvRepository = new LeckekonyvRepository();

    private final JegyService jegyService = new JegyService(new JegyRepository());

    private final JegyRepository jegyRepository = new JegyRepository();

    public void vButtonAction(MouseEvent mouseEvent) {
        kilepes.kilepvisszalep(mouseEvent, visszabtn, "/fooldal.fxml");
    }

    public void logout(MouseEvent mouseEvent) {
        kilepes.kilepvisszalep(mouseEvent, kilep, "/home.fxml");
    }

    private final AlertS alert = new AlertS();

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

            } else if (neptunkodbevitel.getText().isBlank()) {
                alert.alert("Kereső információ", "Nem adtál meg Neptun-kódot!");
            }
        } catch (Exception e) {
            alert.alert("Kereső információ", "A megadott neptun-kód nem található az adatbázisban!");
        }

    }

    public void lekerdez(ActionEvent actionEvent) {
        try {
                Hallgato hallgato = hallgatoService.lekerdezHallgato(neptunJegylkText.getText());
                nevJegyText.setText(hallgato.getNev());
                nevJegyText.setDisable(true);
                neptunJegyText.setText(hallgato.getNeptun_kod());
                neptunJegyText.setDisable(true);
                ObservableList<String> targyak = FXCollections.observableArrayList(felvettTargyakBoxhoz());
                TantargyComboBox.setItems(targyak);

        } catch (Exception e){
            alert.alert("Hiba!", "A megadott neptun-kód nem található az adatbázisban");
        }
    }

    public void leckekonyvlekerdez(ActionEvent actionEvent) {
        try {
            Hallgato hallgato = hallgatoService.lekerdezHallgato(neptunbelk.getText());
            long hallgato_id = hallgato.getId();

            List<Tantargy> tantargyak = hallgato.getTantargyak();

            ObservableList<Leckekonyv> leckekonyv = FXCollections.observableArrayList();

            for (var tantargy : tantargyak) {
                long tantargy_id = tantargy.getId();
                Jegy jegy = jegyRepository.selectHallgatoIDTantargyID(hallgato_id, tantargy_id);
                leckekonyv.add(new Leckekonyv(hallgato.getNeptun_kod(), tantargy.getNev(), jegy.getJegy()));
            }

            neptunlkColumn.setCellValueFactory(new PropertyValueFactory<>("neptun_kod"));
            targylkColumn.setCellValueFactory(new PropertyValueFactory<>("nev"));
            jegylkColumn.setCellValueFactory(new PropertyValueFactory<>("jegy"));

            leckekonyvtable.setItems(leckekonyv);
        } catch (Exception e){
            alert.alert("Hiba!", "A megadott Neptun-kód nem szerepel az adatbázisban!");
        }

    }

    public void tanulmanyiatlag(ActionEvent actionEvent) {
        Hallgato hallgato = hallgatoService.lekerdezHallgato(neptunatlag.getText());
        hallgneve.setText(hallgato.getNev());
        if (!atlaghatar.getText().isBlank()) {
            double hatar = Double.parseDouble(atlaghatar.getText());

            hallgatoatlag.setText(String.format("%.2f", sulyozottAtlag()));

            if (sulyozottAtlag() < hatar) {
                besorolas.setText("Önköltséges");
            } else {
                besorolas.setText("Államilag támogatott");
            }
        } else {
            hallgatoatlag.setText(String.format("%.2f", sulyozottAtlag()));
        }

    }

    public double sulyozottAtlag() {
        Hallgato hallgato = hallgatoService.lekerdezHallgato(neptunatlag.getText());
        long hallgato_id = hallgato.getId();
        int osszeg = 0;
        int szorzat = 0;
        int kreditosszeg = 0;
        List<Tantargy> tantargyak = hallgato.getTantargyak();
        for (var tantargy : tantargyak) {
            long tantargy_id = tantargy.getId();
            Jegy jegy = jegyRepository.selectHallgatoIDTantargyID(hallgato_id, tantargy_id);
            szorzat = tantargy.getKreditszam() * jegy.getJegy();
            osszeg += szorzat;
            kreditosszeg += tantargy.getKreditszam();
        }
        return (double) osszeg / kreditosszeg;
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
            if (tantargy.isEmpty()) {
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
        Tantargy tantargy = getTantargyak.get(0);

        Hallgato hallgato = hallgatoService.lekerdezHallgato(neptunkodbevitel.getText());

        List<Tantargy> hallgatoTantargyak = hallgato.getTantargyak();

        if (!hallgatoTantargyak.contains(getTantargyak.get(0))) {
            String neptun_kod = neptunkodbevitel.getText();

            hallgatoRepository.saveTantargyak(neptun_kod, getTantargyak);
            jegyService.saveJegy(new Jegy(hallgato.getId(), tantargy.getId(), null));
            ObservableList<Tantargy> getTantargy2 = FXCollections.observableArrayList();
            getTantargy2.addAll(hallgato.getTantargyak());
            targykod.setCellValueFactory(new PropertyValueFactory<>("kod"));
            targynev.setCellValueFactory(new PropertyValueFactory<>("nev"));
            kredit.setCellValueFactory(new PropertyValueFactory<>("kreditszam"));

            targyfelvetelTable1.setItems(getTantargy2);
            if (!neptunJegylkText.getText().isBlank()) {
                ObservableList<String> targyak = FXCollections.observableArrayList(felvettTargyakBoxhoz());
                TantargyComboBox.setItems(targyak);
            }
        } else {
            alert.alert("Tantárgy felvétel!", "Ez a tantárgy már hozzá van rendelve a hallgatóhoz!");
        }
    }

    public void töröl(ActionEvent actionEvent) {
        ObservableList<Tantargy> getTantargyakbe = targyfelvetelTable1.getSelectionModel().getSelectedItems();
        Hallgato hallgato = hallgatoService.lekerdezHallgato(neptunkodbevitel.getText());
        List<Tantargy> tantargyak = hallgato.getTantargyak();
        long hallgato_id = hallgato.getId();
        long tantargy_id = 0;
        for (var tantargy : tantargyak) {
            if (getTantargyakbe.get(0).getNev().equals(tantargy.getNev())) {
                tantargy_id = getTantargyakbe.get(0).getId();
                break;
            }
        }

        hallgatoRepository.removeTantargyak(neptunkodbevitel.getText(), getTantargyakbe);
        jegyRepository.removeJegy(hallgato_id, tantargy_id);
        ObservableList<Tantargy> getTantargy2 = FXCollections.observableArrayList();
        getTantargy2.addAll(hallgato.getTantargyak());
        targykod.setCellValueFactory(new PropertyValueFactory<>("kod"));
        targynev.setCellValueFactory(new PropertyValueFactory<>("nev"));
        kredit.setCellValueFactory(new PropertyValueFactory<>("kreditszam"));

        targyfelvetelTable1.setItems(getTantargy2);
        if (!neptunJegylkText.getText().isBlank()) {
            ObservableList<String> targyak = FXCollections.observableArrayList(felvettTargyakBoxhoz());
            TantargyComboBox.setItems(targyak);
        }
    }

    public List<String> felvettTargyakBoxhoz() {
        Hallgato hallgato = hallgatoService.lekerdezHallgato(neptunJegylkText.getText());
        List<Tantargy> tantargyak = hallgato.getTantargyak();
        List<String> tantargyNevLista = new ArrayList<>();
        for (var tantargy : tantargyak) {
            tantargyNevLista.add(tantargy.getNev());
        }
        return tantargyNevLista;
    }


    public void jegycombo() {
        try {
            jegylabel.setText(jegybecombo.getValue().toString());
        } catch (Exception e){

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        jegybecombo.getItems().addAll("1", "2", "3", "4", "5");
        targylekerdez.setDefaultButton(true);
        lekerdezJegyBtn.setDefaultButton(true);
        leckekonyvbtn.setDefaultButton(true);
        tanatlag.setDefaultButton(true);

    }

    public void jegyHozzaad(ActionEvent actionEvent) {
        try {
            if (!neptunJegylkText.getText().isBlank()) {
                Hallgato hallgato = hallgatoService.lekerdezHallgato(neptunJegylkText.getText());
                long hallgato_id = hallgato.getId();
                String targyNev = (String) TantargyComboBox.getSelectionModel().getSelectedItem();
                long tantargyID = 0;

                for (var tantargy : hallgato.getTantargyak()) {
                    if (tantargy.getNev().equals(targyNev)) {
                        tantargyID = tantargy.getId();
                    }
                }

                int jegy = Integer.parseInt(String.valueOf(jegybecombo.getSelectionModel().getSelectedItem()));
                jegyRepository.updateJegy(hallgato_id, tantargyID, jegy);
            } else if (neptunJegylkText.getText().isBlank()) {
                alert.alert("Hiba!", "Nem adtál meg Neptun-kódot!");
            }
        } catch (Exception e) {
            alert.alert("Hiba!", "A megadott Neptun-kód nem szerepel az adatbázisban!");
        }
    }
}
