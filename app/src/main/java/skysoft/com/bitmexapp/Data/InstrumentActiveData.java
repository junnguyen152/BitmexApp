package skysoft.com.bitmexapp.Data;

import com.google.gson.annotations.SerializedName;

public class InstrumentActiveData {
    @SerializedName("symbol")
    public String symbol;
    @SerializedName("lastPrice")
    public float lastPrice;
    @SerializedName("bidPrice")
    public float bidPrice;
    @SerializedName("askPrice")
    public float askPrice;

    public InstrumentActiveData(String symbol, float lastPrice, float bidPrice, float askPrice) {
        this.symbol = symbol;
        this.lastPrice = lastPrice;
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public float getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(float lastPrice) {
        this.lastPrice = lastPrice;
    }

    public float getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(float bidPrice) {
        this.bidPrice = bidPrice;
    }

    public float getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(float askPrice) {
        this.askPrice = askPrice;
    }
}