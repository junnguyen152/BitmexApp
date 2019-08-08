package skysoft.com.bitmexapp.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import skysoft.com.bitmexapp.Data.InstrumentData;
import skysoft.com.bitmexapp.Data.OrderBookData;
import skysoft.com.bitmexapp.Data.RecentTradesData;

public interface APIService {

    @GET("orderBook/L2?symbol=XBTUSD&depth=7")
    Call<List<OrderBookData>> getOrderBookData();
    @GET("orderBook/L2?symbol=XBTU19&depth=7")
    Call<List<OrderBookData>> getOrderBookDataXBTU19();
    @GET("orderBook/L2?symbol=XBTZ19&depth=7")
    Call<List<OrderBookData>> getOrderBookDataXBTZ19();

    @GET("trade?symbol=XBTUSD&columns=timestamp%2C%20side%2C%20size%2C%20price&count=20&reverse=true")
    Call<List<RecentTradesData>> getRecentTradesData();
    @GET("trade?symbol=XBTU19&columns=timestamp%2C%20side%2C%20size%2C%20price&count=20&reverse=true")
    Call<List<RecentTradesData>> getRecentTradesDataXBTU19();
    @GET("trade?symbol=XBTZ19&columns=timestamp%2C%20side%2C%20size%2C%20price&count=20&reverse=true")
    Call<List<RecentTradesData>> getRecentTradesDataXBTZ19();

    //@GET("instrument/active")
    //@GET("dds861/e5f0ae4e99ff820e003c38f35276d4ce/raw/35f7b1452b3cfd55009416c62b055592858b9177/json1.json")
    //Call<List<InstrumentActiveData>> getInstrumentData();

    @GET("instrument?filter=%7B\"state\"%3A%20\"Open\"%7D&columns=rootSymbol%2C%20quoteCurrency%2C%20state%2C%20volume%2C%20lastPrice%2C%20lastChangePcnt&count=100&reverse=false")
    Call<List<InstrumentData>> getInstrumentData();
}
