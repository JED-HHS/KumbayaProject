package program;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class DiseasesController implements Initializable {

    public void terug(ActionEvent event) throws IOException {
        Parent showTerug = FXMLLoader.load(getClass().getResource("Beschikbaarheden.fxml"));
        Scene showHomeScene = new Scene(showTerug);

        //pakt stage informatie
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(showHomeScene);
        window.show();
    }
    //Tableview settings.
    @FXML private TableView<Diseases> Tview;
    @FXML private TableColumn<Diseases ,  String> Disease;

    //Inputs voor Tableview.
    @FXML private TextField DiseaseName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Maakt colums/
        Disease.setCellValueFactory(new PropertyValueFactory<Diseases, String>("Disease"));

        //Gegevens laden/
        Tview.setItems(loadData());

        //Aanpasbaar colums.
        Tview.setEditable(true);
        Disease.setCellFactory(TextFieldTableCell.forTableColumn());

        }
        //De geselecteerde item in de column kan geweizigd worden.
        public void veranderNaam(TableColumn.CellEditEvent editCell){
            Diseases adressSelected = Tview.getSelectionModel().getSelectedItem();
            adressSelected.setDisease(editCell.getNewValue().toString());
        }


        //Items toevoegen door Add knop zie .fxml file.
        public void addButtton(){
            Diseases disease = new Diseases(DiseaseName.getText());
            Tview.getItems().add(disease);
        }

        //Items verwijderen door Delete knop zie .fxml file.
        public void deleteButton(){
            ObservableList<Diseases> adressSelected, allAdresses;
            allAdresses = Tview.getItems();
            adressSelected = Tview.getSelectionModel().getSelectedItems();
            adressSelected.forEach(allAdresses::remove);
        }


    //hier wordt data uit gehaald en gemaakt.
    private ObservableList<Diseases> loadData() {
        ObservableList<Diseases> data = FXCollections.observableArrayList();
        data.add(new Diseases("Diabetes type 2, last bij beide knieeën."));
        data.add(new Diseases("Hartaandoening, veel last bij kransslagaders."));
        return data;
    }
}
