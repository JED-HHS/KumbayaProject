package program;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static program.Afname.*;

public class OefenRekenController {

    @FXML
    Label Getal1;
    @FXML
    Label Getal2;
    @FXML
    Label Score;

    @FXML
    TextField OefenGetal;
    @FXML
    TextField inputoefen;

    int count = 0;

    public void NextQestion(ActionEvent event) {

        for (Input opdracht : Afname.opdrachten) {
            // checkt of reken opdarcht is
            if (opdracht instanceof RekenInput) {

                if (
                    // checkt of getal 1 gelijk is aan getal 1 zelfde voor getal 2
                        ((RekenInput) opdracht).getGetal1().equals(Integer.parseInt(Getal1.getText())) &&
                                ((RekenInput) opdracht).getGetal2().equals(Integer.parseInt(Getal2.getText()))
                ) {
                    opdracht.setInput(inputoefen.getText());

                }
            }
        }
        SetNextQuestion();
    }

    public void SetNextQuestion(){
            if (type.equals("Rekenen")) {
                if (count < Afname.opdrachten.size()) {
                    // haalt de volgende getallen uit de array
                    Input opdracht1 = Afname.opdrachten.get(count);
                    Getal1.setText("" + ((RekenInput) opdracht1).getGetal1());
                    Getal2.setText("" + ((RekenInput) opdracht1).getGetal2());

                    count++;
                } else {
                    Score.setText("Aantalgoed = " + BerekenScore());

                }
            }
        }

        // checkt of het rekenen is




    public void StartButtonOefenToets(ActionEvent event) throws IOException {

        String nummer = OefenGetal.getText();

        StartOefen("Rekenen", nummer);

        Input opdracht = Afname.opdrachten.get(count);
        Getal1.setText("" + ((RekenInput) opdracht).getGetal1());
        Getal2.setText("" + ((RekenInput) opdracht).getGetal2());
        count++;

    }


    //Gaat naar RekenenHome Ovezicht
    public void BacktorekenHome(ActionEvent event) throws IOException {
        Parent toRhome = FXMLLoader.load(getClass().getResource("RekenenHome.fxml"));
        Scene homeRScene = new Scene(toRhome);

        //pakt stage informatie
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homeRScene);
        window.show();
    }


}
