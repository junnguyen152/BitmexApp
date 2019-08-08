package skysoft.com.bitmexapp.Data;

import com.google.gson.annotations.SerializedName;

public class OrderBookData {
    @SerializedName("symbol")
    public String symbol;
    @SerializedName("id")
    public long id;
    @SerializedName("side")
    public String side;
    @SerializedName("size")
    public long size;
    @SerializedName("price")
    public float price;

    public OrderBookData(String symbol, long id, String side, long size, float price) {
        this.symbol = symbol;
        this.id = id;
        this.side = side;
        this.size = size;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
