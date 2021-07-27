package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Details.fxml"));
        Parent root  = loader.load();
        DetailsController controller = loader.getController();
        Scene scene  = new Scene(root , 500 , 500);
        primaryStage.setTitle("Coin Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnHidden(e -> controller.shutdown());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
