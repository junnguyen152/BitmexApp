package skysoft.com.bitmexapp.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import skysoft.com.bitmexapp.API.APIService;
import skysoft.com.bitmexapp.Adapter.RecyclerBuyOrderAdapter;
import skysoft.com.bitmexapp.Adapter.RecyclerSellOrderAdapter;
import skysoft.com.bitmexapp.Data.OrderBookData;
import skysoft.com.bitmexapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TradesFragment extends Fragment {

    private LinearLayoutManager layoutManager;
    private List<OrderBookData> orderbookList = null;
    private List<OrderBookData> sellorderbookList = null;
    private List<OrderBookData> buyorderbookList = null;
    Handler handler;
    Spinner spinner;
    //String[] symbols_id = {"XBTUSD", "XBT7D_U105", "XBT7D_D95", "ETHUSD", "XBTU19", "XBTZ19", "ETHU19", "LTCU19", "BHCU19", "ADAU19", "EOSU19", "TRXU19"};
    String[] symbols_id = {"XBTUSD", "XBTU19", "XBTZ19"};
    public String element;


    public TradesFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trades, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupSpinner();

        try {
            handler = new Handler();
            final Runnable r = new Runnable() {
                public void run() {
                    getOrderBookList();
                    handler.postDelayed(this, 2000);
                }
            };
            handler.postDelayed(r, 2000);
        }catch (Exception e){
            System.out.println("Error " + e.getMessage());
        }


    }

    private void getOrderBookList(){
        try {
            String url = "https://www.bitmex.com/api/v1/";
            Retrofit retrofit = null;
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            APIService service = retrofit.create(APIService.class);

            if(spinner.getSelectedItemId()==0){
                Call<List<OrderBookData>> call = service.getOrderBookData();

                call.enqueue(new Callback<List<OrderBookData>>() {
                    @Override
                    public void onResponse(Call<List<OrderBookData>> call, Response<List<OrderBookData>> response) {
                        try {
                            orderbookList = response.body();
                            if(orderbookList == null || orderbookList.isEmpty()){
                                return;
                            }

                            sellorderbookList = new ArrayList<OrderBookData>();
                            buyorderbookList = new ArrayList<OrderBookData>();

                            for (int i = 0; i < orderbookList.size(); i++) {
                                if (orderbookList.get(i).getSide().equals("Buy")) {
                                    buyorderbookList.add(orderbookList.get(i));
                                } else if (orderbookList.get(i).getSide().equals("Sell")) {
                                    sellorderbookList.add(orderbookList.get(i));
                                }
                            }
                            callSellAdapter(sellorderbookList);
                            callBuyAdapter(buyorderbookList);
                        }catch (Exception e){
                            System.out.println("Error " + e.getMessage());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<OrderBookData>> call, Throwable t) {
                        Log.i("autolog", t.getMessage());
                    }
                });
            }else if(spinner.getSelectedItemId()==1){
                Call<List<OrderBookData>> call = service.getOrderBookDataXBTU19();

                call.enqueue(new Callback<List<OrderBookData>>() {
                    @Override
                    public void onResponse(Call<List<OrderBookData>> call, Response<List<OrderBookData>> response) {
                        try {
                            orderbookList = response.body();
                            if(orderbookList == null || orderbookList.isEmpty()){
                                return;
                            }

                            sellorderbookList = new ArrayList<OrderBookData>();
                            buyorderbookList = new ArrayList<OrderBookData>();

                            for (int i = 0; i < orderbookList.size(); i++) {
                                if (orderbookList.get(i).getSide().equals("Buy")) {
                                    buyorderbookList.add(orderbookList.get(i));
                                } else if (orderbookList.get(i).getSide().equals("Sell")) {
                                    sellorderbookList.add(orderbookList.get(i));
                                }
                            }
                            callSellAdapter(sellorderbookList);
                            callBuyAdapter(buyorderbookList);
                        }catch (Exception e){
                            System.out.println("Error " + e.getMessage());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<OrderBookData>> call, Throwable t) {
                        Log.i("autolog", t.getMessage());
                    }
                });
            }else{
                Call<List<OrderBookData>> call = service.getOrderBookDataXBTZ19();

                call.enqueue(new Callback<List<OrderBookData>>() {
                    @Override
                    public void onResponse(Call<List<OrderBookData>> call, Response<List<OrderBookData>> response) {
                        try {
                            orderbookList = response.body();
                            if(orderbookList == null || orderbookList.isEmpty()){
                                return;
                            }

                            sellorderbookList = new ArrayList<OrderBookData>();
                            buyorderbookList = new ArrayList<OrderBookData>();

                            for (int i = 0; i < orderbookList.size(); i++) {
                                if (orderbookList.get(i).getSide().equals("Buy")) {
                                    buyorderbookList.add(orderbookList.get(i));
                                } else if (orderbookList.get(i).getSide().equals("Sell")) {
                                    sellorderbookList.add(orderbookList.get(i));
                                }
                            }
                            callSellAdapter(sellorderbookList);
                            callBuyAdapter(buyorderbookList);
                        }catch (Exception e){
                            System.out.println("Error " + e.getMessage());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<OrderBookData>> call, Throwable t) {
                        Log.i("autolog", t.getMessage());
                    }
                });
            }

        }catch (Exception e) {Log.i("autolog", "Exception");}

    }


    public void callBuyAdapter(List<OrderBookData> list){
        RecyclerView buy_recyclerView = (RecyclerView) requireActivity().findViewById(R.id.buyorder_recycler);
        layoutManager = new LinearLayoutManager(requireContext());
        buy_recyclerView.setLayoutManager(layoutManager);
        RecyclerBuyOrderAdapter buy_recyclerViewAdapter = new RecyclerBuyOrderAdapter(requireActivity().getApplicationContext(), list);
        buy_recyclerView.setAdapter(buy_recyclerViewAdapter);
    }

    public void callSellAdapter(List<OrderBookData> list){
        RecyclerView sell_recyclerView = (RecyclerView) requireActivity().findViewById(R.id.sellorder_recycler);
        layoutManager = new LinearLayoutManager(requireContext());
        sell_recyclerView.setLayoutManager(layoutManager);
        RecyclerSellOrderAdapter sell_recyclerViewAdapter = new RecyclerSellOrderAdapter(requireActivity().getApplicationContext(), list);
        sell_recyclerView.setAdapter(sell_recyclerViewAdapter);
    }

    public void setupSpinner(){

        spinner = (Spinner) requireActivity().findViewById(R.id.orderbook_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireActivity(), R.layout.spinner_item, symbols_id);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        element = spinner.getSelectedItem().toString();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


}
