package sample;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import java.io.IOException;


public class DetailsController {
    @FXML
    Label labelBTC;
    @FXML
    Label labelETH;

    @FXML
    HBox BTChbox;

    @FXML
    HBox ETHhbox;


    // the values of label present sent in initialize method
    Coin bitcoin , ethereum ;
    Timer bitcoinTimer , ethereumTimer;


    // the values of label present sent in initialize method
    public void initialize(){
        this.bitcoin = new Coin("bitcoin");
        this.ethereum = new Coin("ethereum");


        labelBTC.textProperty().bind(Bindings.format("$%-10.2f" , bitcoin.currentPriceProperty()));
        labelETH.textProperty().bind(Bindings.format("$%-10.2f" , ethereum.currentPriceProperty()));

        bitcoinTimer = new Timer();
        bitcoinTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new UpdateCoin(bitcoin));
            }
        } , 0  , 5000);

        ethereumTimer = new Timer();
        ethereumTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new UpdateCoin(ethereum));
            }
        } , 0 , 5000);
        // labelBTC.setText("$48,000.00");
        // labelETH.setText("$1,832.00");

    }

    public DetailsController() {
        System.out.println("Constructor");

    }


    public void buttonClicked(MouseEvent mouseEvent) throws IOException {

        shutdown();


        Parent root = FXMLLoader.load(getClass().getResource("Chart.fxml"));
        Stage primaryStage = (Stage) BTChbox.getScene().getWindow();
        primaryStage.setScene(new Scene(root, 500, 500));}

    public void shutdown(){
        System.out.println("Shutdown called Timer stopped");
        bitcoinTimer.cancel();
        ethereumTimer.cancel();
    }
}

