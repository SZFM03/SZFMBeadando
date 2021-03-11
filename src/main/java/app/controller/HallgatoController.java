package app.controller;

import app.entity.Hallgato;
import app.repository.HallgatoRepository;
import app.service.HallgatoService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HallgatoController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private final HallgatoService hallgatoService = new HallgatoService(new HallgatoRepository());

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

    public void register(ActionEvent actionEvent) {
        try {
            if (!nevAddText.getText().isBlank() && !neptunAddText.getText().isBlank() && !szuletesiEvAddText.getText().isBlank()) {
                hallgatoService.saveHallgato(new Hallgato(nevAddText.getText(), szuletesiEvAddText.getText(), neptunAddText.getText()));

                nevAddText.clear();
                neptunAddText.clear();
                szuletesiEvAddText.clear();

                Alert alert2=new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Regisztrációs információ");
                alert2.setHeaderText(null);
                alert2.setContentText("Sikeresen regisztráltál egy hallgatót!");
                alert2.showAndWait();

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteTorles(ActionEvent actionEvent) throws InterruptedException {
        if (!neptunTorlesText.getText().isBlank()){
            hallgatoService.deleteHallgato(neptunTorlesText.getText());
            neptunTorlesText.clear();

            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Törlés információ");
            alert.setHeaderText(null);
            alert.setContentText("Sikeresen törölted a hallgatót az adatbázisból!");
            alert.showAndWait();
        }
    }

    public void modosit(ActionEvent actionEvent) {

      if(!nevAdatText.getText().isBlank() && !neptunAdatText.getText().isBlank() && !szuletesiEvAdatText.getText().isBlank()){
            hallgatoService.modositHallgato(""+nevAdatText, ""+szuletesiEvAdatText, ""+neptunAdatText);
        }

    }

    public void logout(MouseEvent mouseEvent){

        if (mouseEvent.getSource() == kilep) {

            try {

                Node node = (Node) mouseEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();

                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/home.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());

            }

        }
    }
}
