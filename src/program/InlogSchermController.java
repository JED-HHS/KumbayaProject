package program;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InlogSchermController {
    // onderstaande 2 textfields is de tekst die in deze textfields krijgen van de gebruiker
    @FXML
    private TextField inlogUsername;
    @FXML
    private TextField inlogPassword;
    public Label wrongPasswordAlert;

    String Gebruikersnaam= "Jan";
    String Wachtwoord = "123";
    boolean correctLogin = false;

    public boolean naLogin(ActionEvent event) throws IOException{
        if((inlogUsername.getText().equals(Gebruikersnaam))&&(inlogPassword.getText().equals(Wachtwoord))) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("homescreen.fxml"));
            Parent showMain = loader.load();
            Scene showMainScene = new Scene(showMain);

            homescreenController controller = loader.getController();
            controller.setStartingData();;

            //pakt stage informatie
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(showMainScene);
            window.show();
            return correctLogin = true;
        }
        else{
            wrongPasswordAlert.setText("Wrong password or username, try again.");
            return correctLogin = false;
        }
    }
    public String getGebruikersnaam() {
        return Gebruikersnaam;
    }
    public String getWachtwoord() {
        return Wachtwoord;
    }
    public boolean getCorrectLogin() {return correctLogin;}
}