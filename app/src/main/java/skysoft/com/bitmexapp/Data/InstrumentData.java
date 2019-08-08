package skysoft.com.bitmexapp.Data;

import com.google.gson.annotations.SerializedName;

public class InstrumentData {
    @SerializedName("symbol")
    public String symbol;
    @SerializedName("quoteCurrency")
    public String quoteCurrency;
    @SerializedName("state")
    public String state;
    @SerializedName("volume")
    public long volume;
    @SerializedName("lastPrice")
    public float lastPrice;
    @SerializedName("lastChangePcnt")
    public float lastChangePcnt;

    public InstrumentData(String symbol, String quoteCurrency, String state, long volume, float lastPrice, float lastChangePcnt) {
        this.symbol = symbol;
        this.quoteCurrency = quoteCurrency;
        this.state = state;
        this.volume = volume;
        this.lastPrice = lastPrice;
        this.lastChangePcnt = lastChangePcnt;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    public void setQuoteCurrency(String quoteCurrency) {
        this.quoteCurrency = quoteCurrency;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public float getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(float lastPrice) {
        this.lastPrice = lastPrice;
    }

    public float getLastChangePcnt() {
        return lastChangePcnt;
    }

    public void setLastChangePcnt(float lastChangePcnt) {
        this.lastChangePcnt = lastChangePcnt;
    }
}
