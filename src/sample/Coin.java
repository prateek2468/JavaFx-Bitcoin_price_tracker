package sample;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.chart.XYChart;

public class Coin {
    private String name ;
    private final DoubleProperty currentPrice;

    private XYChart.Series<Number , Number> historicalValues;

    public XYChart.Series<Number, Number> getHistoricalValues() {
        return historicalValues;
    }

    public void setHistoricalValues(XYChart.Series<Number, Number> historicalValues) {
        this.historicalValues = historicalValues;
    }

    public void addHistoricalValue(int day , double value){
        historicalValues.getData().add(new XYChart.Data<>(day , value));
    }

    public Coin(String name){
        this.name = name ;
        currentPrice = new SimpleDoubleProperty();
        historicalValues = new XYChart.Series<>();
        historicalValues.setName(name);
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public void setCurrentPrice(double currentPrice){
        this.currentPrice.set(currentPrice);
    }

    public double getCurrentPrice(){
        return currentPrice.get();
    }

    public DoubleProperty currentPriceProperty(){
        return currentPrice;
    }


    @Override
    public String toString(){
        return String.format("%20s : $%-10.2f" , this.name , this.currentPrice.getValue());
    }
}
