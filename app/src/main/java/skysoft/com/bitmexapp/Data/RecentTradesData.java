package skysoft.com.bitmexapp.Data;

import com.google.gson.annotations.SerializedName;

public class RecentTradesData {
    @SerializedName("timestamp")
    public String timestamp;
    @SerializedName("symbol")
    public String symbol;
    @SerializedName("side")
    public String side;
    @SerializedName("size")
    public long size;
    @SerializedName("price")
    public float price;

    public RecentTradesData(String timestamp, String symbol, String side, long size, float price) {
        this.timestamp = timestamp;
        this.symbol = symbol;
        this.side = side;
        this.size = size;
        this.price = price;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
