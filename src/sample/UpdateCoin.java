package sample;

public class UpdateCoin implements Runnable {
    private Coin coin;

    public UpdateCoin(Coin coin) {
        this.coin = coin;
    }

    @Override
    public void run() {

        System.out.println("Updating coin "+coin.getName());
        double curr = this.coin.getCurrentPrice();
        CoinGecko.updateCurrentPrice(coin);
        if(curr != this.coin.getCurrentPrice()){
            System.out.println("---------------------Price Changed "+ this.coin.getName());
        }
    }
}
