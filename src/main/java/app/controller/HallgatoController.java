package app.controller;

import app.entity.Hallgato;
import app.entity.Jegy;
import app.entity.Leckekonyv;
import app.entity.Tantargy;
import app.repository.HallgatoRepository;
import app.repository.JegyRepository;
import app.repository.LeckekonyvRepository;
import app.service.HallgatoService;
import app.service.LeckekonyvService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
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
    private Button leckekonyvKeresoButton;

    @FXML
    private TextField nevKeresoText;

    @FXML
    private TextField neptunKeresoText;

    @FXML
    private TextField szuletesiEvKeresoText;

    @FXML
    private TextField keresoText;

    @FXML
    private Button loadKeresoButton;


    @FXML
    private Button addButton;

    @FXML
    private TextField nevAddText;

    @FXML
    private TextField neptunAddText;

    @FXML
    private TextField szuletesiEvAddText;

    @FXML
    private Button deleteTorlesButton;

    @FXML
    private TextField neptunTorlesText;

    @FXML
    private Button saveAdatButton1;

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

    private final KilepVisszalep oldalLeptetes = new KilepVisszalep();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadKeresoButton.setDefaultButton(true);
        addButton.setDefaultButton(true);
        deleteTorlesButton.setDefaultButton(true);
        saveAdatButton1.setDefaultButton(true);
    }

    private final HallgatoService hallgatoService = new HallgatoService(new HallgatoRepository());

    private final KilepVisszalep kilepes = new KilepVisszalep();

    private final JegyRepository jegyRepository = new JegyRepository();

    private final LeckekonyvRepository leckekonyvRepository = new LeckekonyvRepository();

    private final LeckekonyvService leckekonyvService = new LeckekonyvService(leckekonyvRepository, jegyRepository);

    private final AlertS alert = new AlertS();

    public void vButtonAction(MouseEvent mouseEvent) {
        kilepes.kilepvisszalep(mouseEvent, visszabtn, "/fooldal.fxml");
    }

    public void lButtonAction(MouseEvent mouseEvent) {
        Hallgato hallgato = hallgatoService.lekerdezHallgato(keresoText.getText());
        long hallgato_id = hallgato.getId();
        List<Tantargy> tantargyak = hallgato.getTantargyak();

        List<Leckekonyv> leckekonyv = new ArrayList<>();

        for(var tantargy : tantargyak) {
            long tantargy_id = tantargy.getId();
            Jegy jegy = jegyRepository.selectHallgatoIDTantargyID(hallgato_id, tantargy_id);
            if (jegy.getJegy() == null) {
                leckekonyvService.saveLeckekonyv(new Leckekonyv(hallgato.getId(), tantargy.getId(), null));
                leckekonyv.add(new Leckekonyv(hallgato.getId(), tantargy.getId(), null));
            } else {
                leckekonyvService.saveLeckekonyv(new Leckekonyv(hallgato.getId(), tantargy.getId(), jegy.getJegy()));
                leckekonyv.add(new Leckekonyv(hallgato.getId(), tantargy.getId(), jegy.getJegy()));
            }
        }
        oldalLeptetes.kilepvisszalep(mouseEvent, leckekonyvKeresoButton, "/leckekonyv.fxml");
    }

    public void register(ActionEvent actionEvent) {
        boolean negativSzuletesiDatum = false;
        try {

            if (!nevAddText.getText().isBlank() && !neptunAddText.getText().isBlank() && !szuletesiEvAddText.getText().isBlank()) {
                Hallgato hallgato = new Hallgato(nevAddText.getText(), Integer.parseInt(szuletesiEvAddText.getText()), neptunAddText.getText());
                negativSzuletesiDatum = hallgatoService.pozitivSzuletesiDatum(hallgato);
                if (!negativSzuletesiDatum) {
                    hallgatoService.saveHallgato(hallgato);
                    alert.alert("Regisztr??ci??s inform??ci??", "Sikeresen regisztr??lt??l egy hallgat??t!");
                }
            }
        }catch (Exception e){
            if (szuletesiEvAddText.getText().matches("[0-9]+") && szuletesiEvAddText.getText().length() >= 1) {
                alert.alert("Regisztr??ci??s inform??ci??", "Ez a Neptun-k??d m??r foglalt!");
            }
        } if(nevAddText.getText().isBlank()){
            alert.alert("Regisztr??ci??s inform??ci??", "Nem adt??l meg Nevet!");
        } else if (szuletesiEvAddText.getText().isBlank()){
            alert.alert("Regisztr??ci??s inform??ci??","Nem adt??l meg sz??let??si ??vet!");
        } else if(neptunAddText.getText().isBlank()){
            alert.alert("Regisztr??ci??s inform??ic??", "Nem adt??l meg Neptun-k??dot!");
        } if (negativSzuletesiDatum) {
            alert.alert("Tant??rgy hozz??ad??sa inform??ci??", "A sz??let??si ??v nem lehet negat??v");
            szuletesiEvAddText.clear();
        }
        else if (!szuletesiEvAddText.getText().matches("[0-9]+") && szuletesiEvAddText.getText().length() >= 1) {
            alert.alert("Regisztr??ci??s inform??ci??", "A sz??let??si ??v csak sz??mokat tartalmazhat!");
            szuletesiEvAddText.clear();
        }else {
            nevAddText.clear();
            neptunAddText.clear();
            szuletesiEvAddText.clear();
        }
    }

    public void deleteTorles(ActionEvent actionEvent) throws InterruptedException {
        if (!neptunTorlesText.getText().isBlank()){
            hallgatoService.deleteHallgato(neptunTorlesText.getText());
            neptunTorlesText.clear();

            alert.alert("T??rl??s inform??ci??", "Sikeresen t??r??lted a hallgat??t az adatb??zisb??l!");
        }
    }

    public void modosit(ActionEvent actionEvent) {

        if(!nevadatText.getText().isBlank() && !neptunadatText.getText().isBlank() && !szuletesievadatText.getText().isBlank()){
            hallgatoService.modositHallgato(nevadatText.getText(), szuletesievadatText.getText(), neptunadatText.getText());
            nevadatText.clear();
            neptunadatText.clear();
            szuletesievadatText.clear();

            alert.alert("M??dos??t??si inform??ci??", "Sikeres adatm??dos??t??s!");
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
                szuletesievadatText.setText(hallgato.getSzuletesi_ev().toString());
                neptunadatText.setText(hallgato.getNeptun_kod());
                neptunadatText.setDisable(true);

                neptunbevitelText.clear();
            }else if(keresoText.getText().isBlank()){
                alert.alert("Keres?? inform??ci??","Nem adt??l meg Neptun-k??dot!");
            }
        }catch (Exception e){
            alert.alert("Keres?? inform??ci??","A megadott Neptun-k??d nem tal??lhat?? az adatb??zisban!");
        }

    }

    public void kereso(ActionEvent actionEvent) {
        try {
            if (!keresoText.getText().isBlank()) {
                Hallgato hallgato = hallgatoService.lekerdezHallgato(keresoText.getText());
                nevKeresoText.setText(hallgato.getNev());
                szuletesiEvKeresoText.setText(hallgato.getSzuletesi_ev().toString());
                neptunKeresoText.setText(hallgato.getNeptun_kod());
                nevKeresoText.setDisable(true);
                szuletesiEvKeresoText.setDisable(true);
                neptunKeresoText.setDisable(true);

            } else if(keresoText.getText().isBlank()){
                alert.alert("Keres?? inform??ci??","Nem adt??l meg Neptun-k??dot!");
            }
        }catch (Exception e){
            alert.alert("Keres?? inform??ci??","A megadott Neptun-k??d nem tal??lhat?? az adatb??zisban!");
        }

    }

    public void lekerdezMindenHallgato(ActionEvent actionEvent) {
        try {
            ObservableList<Hallgato> getHallgatok = FXCollections.observableArrayList();
            List<Hallgato> hallgatok = hallgatoService.MindenHallgato();
            if(hallgatok.isEmpty()){
                alert.alert("Minden hallgat?? inf??", "Nincs az adatb??zisban egy hallgat?? sem!");
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
