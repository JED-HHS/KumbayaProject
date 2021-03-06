package program;
import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.image.Image ;
import static program.Afname.*;



public class SchrijvenController {
    public TextArea test1Field;
    public Label testNumber;
    public Button checkButton;
    public Button playButton;
    public Label middleText;
    public ImageView endscreenImage;
    public int current = 1; //houd bij welke mp3 file gebruikt moet worden

    private static SchrijvenController instance;

    // ik heb hieronder een private controller gemaakt. Hier door kan er maar 1 instance gemaakt worden
    // je vraagt deze dan ook alsvolgt op: SchrijvenController.getInstance();
    // je kunt nu niet meer SchrijvenController schrijven = new SchrijvenController();
    // Kijk maar naar de if statement hieronder.
    // als er al een instance is dan is 'instance == null' false, waardoor hij de zelfde  'return instance' geven.
    // Als er geen instance is dus 'instance == nul' is true, dan kun je maar als het ware 1 object aanmaken.
    private SchrijvenController(){
        //System.out.println("hoi");
    }

    // ik heb dit weggehaald: static SchrijvenController obj = new SchrijvenController();
    public static SchrijvenController getInstance(){
        if (instance == null){
            instance = new SchrijvenController();
        }
        return instance;
       // dit ook: return obj;
    }

    public int teller = 2; //
    public int count = 0;
    //Speelt de mp3 file die is geselecteerd
    public void startTest(){
        StartToets("Schrijven");
        Input input = Afname.opdrachten.get(count);
        System.out.println(((SchrijvenInput) input).getMp3());
    final URL resource = getClass().getResource("../mp3/" + ((SchrijvenInput) input).getMp3() +".mp3");
    final Media media = new Media(resource.toString());
    final MediaPlayer mediaPlayer = new MediaPlayer(media);
    mediaPlayer.play();
    }

    //Controleerd antwoord
    public void checkTest() {
        System.out.println("size = " + opdrachten.size());
        Input opdracht = Afname.opdrachten.get(count);
        String answer = ((SchrijvenInput) opdracht).getAntwoord();
        String input = test1Field.getText();
        if (count < 2) {
            if (input.equals(answer)) {
                alertBox(true);
                test1Field.clear();
                testNumber.setText("Test " + (count + 1));
                count++;
            } else {
                alertBox(false);
            }
        } else {
            done();
        }
    }

    //Eindscherm laten zien voor als de gebruiker alles goed heeft
    public void done(){
        test1Field.setVisible(false);
        playButton.setVisible(false);
        checkButton.setVisible(false);
        testNumber.setText("Good Job!");
        middleText.setText("You have completed your test!");
        endscreenImage.setVisible(true);
    }

    //Alertbox laten zien bij het checken van antwoord
    public void alertBox(boolean status){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        if (status == true){
            alert.setTitle("Good Job!");
            alert.setContentText("I have a great message for you!");
            alert.setContentText("press ok to continue");
            Image image = new Image(getClass().getResource("../images/goodjob.gif").toExternalForm());
            ImageView imageView = new ImageView(image);
            alert.setGraphic(imageView);
            alert.showAndWait();
        }else{
            alert.setTitle("False answer");
            alert.setContentText("I have a great message for you!");
            alert.setContentText("press ok to continue");
            Image image = new Image(getClass().getResource("../images/false.gif").toExternalForm());
            ImageView imageView = new ImageView(image);
            alert.setGraphic(imageView);
            alert.showAndWait();
        }
    }


    public void toHomescreen(ActionEvent event) throws IOException {
        Parent tohome  = FXMLLoader.load(getClass().getResource("homescreen.fxml"));
        Scene homeScene = new Scene(tohome);

        //pakt stage informatie
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(homeScene);
        window.show();
    }
}
