import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.util.concurrent.ThreadLocalRandom;

public class GameController {

    @FXML
    private GridPane gridPane;
    @FXML
    private ImageView tile00;
    @FXML
    private ImageView tile10;
    @FXML
    private ImageView tile20;
    @FXML
    private ImageView tile01;
    @FXML
    private ImageView tile11;
    @FXML
    private ImageView tile21;
    @FXML
    private ImageView tile02;
    @FXML
    private ImageView tile12;
    @FXML
    private ImageView tile22;
    @FXML
    private ImageView currentTurn;
    @FXML
    private ImageView theWinner;
    @FXML
    private Label theWinnerLabel;
    @FXML
    private Button resetButton;
    @FXML
    private Button exitButton;

    private boolean isTurnX;
    private boolean isWinnerDetermined;
    private int clickCounter;
    private Image circle = new Image("circle.png");
    private Image cross = new Image("cross.png");
    private Image none = new Image("none.png");

    @FXML
    public void initialize(){
        randomFirstTurnStarter();
        clickCounter = 0;
        isWinnerDetermined = false;
    }

    @FXML
    public void mouseClickedTileSetup(MouseEvent event) throws Exception{

       try
       {
           ImageView image = (ImageView) event.getSource();

           if (isTurnX == true){
               image.setImage(cross);
               isTurnX = false;
               currentTurn.setImage(circle);
           } else if(isTurnX == false){
               image.setImage(circle);
               isTurnX = true;
               currentTurn.setImage(cross);
           }
           image.setDisable(true);
           clickCounter++;
           determineWinner();

           if(isWinnerDetermined == true){
               currentTurn.setImage(none);
               disableAllTiles(true);
           }
       }
       catch(Exception e ){

       }
    }

    @FXML
    public void resetGame(MouseEvent event){

        tile00.setImage(null);
        tile10.setImage(null);
        tile20.setImage(null);
        tile01.setImage(null);
        tile11.setImage(null);
        tile21.setImage(null);
        tile02.setImage(null);
        tile12.setImage(null);
        tile22.setImage(null);

        disableAllTiles(false);

        randomFirstTurnStarter();
        clickCounter = 0;
        isWinnerDetermined = false;

        theWinner.setVisible(false);
        theWinnerLabel.setVisible(false);
    }

    @FXML
    public void exitProgram(MouseEvent event){

        Platform.exit();
    }

    private void determineWinner() {

        if ((tile00.getImage()==cross && tile01.getImage()==cross && tile02.getImage()==cross) ||
                (tile10.getImage()==cross && tile11.getImage()==cross && tile12.getImage()==cross) ||
                (tile20.getImage()==cross && tile21.getImage()==cross && tile22.getImage()==cross) ||
                (tile00.getImage()==cross && tile10.getImage()==cross && tile20.getImage()==cross) ||
                (tile01.getImage()==cross && tile11.getImage()==cross && tile21.getImage()==cross) ||
                (tile02.getImage()==cross && tile12.getImage()==cross && tile22.getImage()==cross) ||
                (tile02.getImage()==cross && tile11.getImage()==cross && tile20.getImage()==cross) ||
                (tile00.getImage()==cross && tile11.getImage()==cross && tile22.getImage()==cross)){

            theWinner.setImage(cross);
            isWinnerDetermined = true;


        }else if((tile00.getImage()==circle && tile01.getImage()==circle && tile02.getImage()==circle) ||
                (tile10.getImage()==circle && tile11.getImage()==circle && tile12.getImage()==circle) ||
                (tile20.getImage()==circle && tile21.getImage()==circle && tile22.getImage()==circle) ||
                (tile00.getImage()==circle && tile10.getImage()==circle && tile20.getImage()==circle) ||
                (tile01.getImage()==circle && tile11.getImage()==circle && tile21.getImage()==circle) ||
                (tile02.getImage()==circle && tile12.getImage()==circle && tile22.getImage()==circle) ||
                (tile02.getImage()==circle && tile11.getImage()==circle && tile20.getImage()==circle) ||
                (tile00.getImage()==circle && tile11.getImage()==circle && tile22.getImage()==circle)){

            theWinner.setImage(circle);
            isWinnerDetermined = true;

        }else if(clickCounter == 9){

            theWinner.setImage(none);
            isWinnerDetermined = true;
        }

        if(isWinnerDetermined == true){

            theWinner.setVisible(true);
            theWinnerLabel.setVisible(true);
        }
    }

    private void randomFirstTurnStarter(){
        double rand = ThreadLocalRandom.current().nextDouble(0,2);

        if(rand >= 1.00){
            currentTurn.setImage(cross);
            isTurnX = true;
        }else {
            currentTurn.setImage(circle);
            isTurnX = false;
        }
    }

    private void disableAllTiles(boolean value){
        tile00.setDisable(value);
        tile10.setDisable(value);
        tile20.setDisable(value);
        tile01.setDisable(value);
        tile11.setDisable(value);
        tile21.setDisable(value);
        tile02.setDisable(value);
        tile12.setDisable(value);
        tile22.setDisable(value);
    }


}


