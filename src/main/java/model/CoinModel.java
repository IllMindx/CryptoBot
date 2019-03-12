package model;

public class CoinModel {
    private String name, symbol, circulatingSupply, rank, price, percentChange1h, percentChange24h, percentChange7d, maxSupply;

    public CoinModel(String name, String symbol, String rank, String price){
        this.name = name;
        this.symbol = symbol;
        this.rank = rank;
        this.price = price;
    }

    public CoinModel(String name, String symbol, String rank, String price,
                     String circulatingSupply, String maxSupply, String percentChange1h, String percentChange24h, String percentChange7d ){

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

    public String getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(String circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPercentChange1h() {
        return percentChange1h;
    }

    public void setPercentChange1h(String percentChange1h) {
        this.percentChange1h = percentChange1h;
    }

    public String getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(String percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public String getPercentChange7d() {
        return percentChange7d;
    }

    public void setPercentChange7d(String percentChange7d) {
        this.percentChange7d = percentChange7d;
    }

    public String getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(String maxSupply) {
        this.maxSupply = maxSupply;
    }
}
