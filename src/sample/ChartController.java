package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class ChartController {
    @FXML
    ImageView backArrow;
    @FXML
    LineChart<Number , Number > priceChart;
    @FXML
    ComboBox DateRange;
    @FXML
    ComboBox CoinSelector;


    Coin bitcoin , ethereum;

    public void initialize(){
        priceChart.setAnimated(false);
        bitcoin = new Coin("bitcoin");
        ethereum = new Coin("ethereum");

        CoinGecko.updatePrice(bitcoin , 365);
        CoinGecko.updatePrice(ethereum , 365);

        priceChart.getData().add(bitcoin.getHistoricalValues());

    }

    public void backClicked(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Details.fxml"));
        Stage primaryStage = (Stage) backArrow.getScene().getWindow();
        primaryStage.setScene(new Scene(root, 500, 500));
    }

    public void onDateRangeChanged(ActionEvent actionEvent) {
        int day =0 ;
        switch((String)DateRange.getValue()){
            case "Year":
                day = 365;
                break;
            case "90 days":
                day = 90;
                break;
            case "60 days":
                day = 60;
                break;
            case "30 days":
                day = 30;
                break;
            case "week":
                day = 7;
                break;
        }
        CoinGecko.updatePrice(bitcoin , day);
        CoinGecko.updatePrice(ethereum , day);
    }

    private void updateChart(){
        priceChart.getData().clear();
        switch ((String)CoinSelector.getValue()){
            case "Bitcoin":
                priceChart.getData().add(bitcoin.getHistoricalValues());
                break;
            case "Ethereum":
                priceChart.getData().add(ethereum.getHistoricalValues());
                break;
            case "All":
                priceChart.getData().add(bitcoin.getHistoricalValues());
                priceChart.getData().add(ethereum.getHistoricalValues());
                break;
        }

    }

    public void onCoinChanged(ActionEvent actionEvent) {
        updateChart();
    }
}
