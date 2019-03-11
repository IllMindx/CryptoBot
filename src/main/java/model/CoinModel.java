package model;

public class CoinModel {
    private String name, symbol;
    private int circulatingSupply, rank, price, percentChange1h, percentChange24h, percentChange7d, maxSupply;

    public CoinModel(String name, String symbol, int rank, int price){
        this.name = name;
        this.symbol = symbol;
        this.rank = rank;
        this.price = price;
    }

    public CoinModel(String name, String symbol, int rank, int price,
                     int circulatingSupply, int maxSupply, int percentChange1h, int percentChange24h, int percentChange7d ){

        this.name = name;
        this.symbol = symbol;
        this.rank = rank;
        this.price = price;
        this.circulatingSupply = circulatingSupply;
        this.maxSupply = maxSupply;
        this.percentChange1h = percentChange1h;
        this.percentChange24h = percentChange24h;
        this.percentChange7d = percentChange7d;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(int circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPercentChange1h() {
        return percentChange1h;
    }

    public void setPercentChange1h(int percentChange1h) {
        this.percentChange1h = percentChange1h;
    }

    public int getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(int percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public int getPercentChange7d() {
        return percentChange7d;
    }

    public void setPercentChange7d(int percentChange7d) {
        this.percentChange7d = percentChange7d;
    }

    public int getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(int maxSupply) {
        this.maxSupply = maxSupply;
    }
}
