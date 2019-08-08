package skysoft.com.bitmexapp.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import skysoft.com.bitmexapp.Adapter.RecyclerRecentTradesAdapter;
import skysoft.com.bitmexapp.Data.RecentTradesData;
import skysoft.com.bitmexapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentTradesFragment extends Fragment {

    private LinearLayoutManager layoutManager;
    private List<RecentTradesData> recentTradesList;
    Handler handler;
    String[] symbols_rct_id = {"XBTUSD", "XBTU19", "XBTZ19"};
    Spinner spinner;
    SwipeRefreshLayout mSwipeRefreshLayoutRecentTrade;

    public RecentTradesFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recent_trades, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupSpinner();
        try {
            handler = new Handler();
            final Runnable r = new Runnable() {
                public void run() {
                    getRecentTradesList();
                    handler.postDelayed(this, 2000);
                }
            };
            handler.postDelayed(r, 2000);
        }catch (Exception e){
            System.out.println("Error " + e.getMessage());
        }
        mSwipeRefreshLayoutRecentTrade = view.findViewById(R.id.swipeRefreshLayoutRecentTrades);
        mSwipeRefreshLayoutRecentTrade.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getRecentTradesList();
                mSwipeRefreshLayoutRecentTrade.setRefreshing(false);
            }
        });
    }

    private void getRecentTradesList(){
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
                Call<List<RecentTradesData>> call = service.getRecentTradesData();

                call.enqueue(new Callback<List<RecentTradesData>>() {
                    @Override
                    public void onResponse(Call<List<RecentTradesData>> call, Response<List<RecentTradesData>> response) {
                        //Log.i("onResponse", response.message());
                        try {
                            recentTradesList = new ArrayList<RecentTradesData>();
                            recentTradesList = response.body();

                            if(recentTradesList == null || recentTradesList.isEmpty()){
                                return;
                            }
                            RecyclerView recenttrades_recyclerView = (RecyclerView) requireActivity().findViewById(R.id.recyclerRecentTrades);
                            layoutManager = new LinearLayoutManager(requireContext());
                            recenttrades_recyclerView.setLayoutManager(layoutManager);
                            RecyclerRecentTradesAdapter recenttrades_recyclerViewAdapter = new RecyclerRecentTradesAdapter(requireActivity().getApplicationContext(), recentTradesList);
                            recenttrades_recyclerView.setAdapter(recenttrades_recyclerViewAdapter);
                        }catch (Exception e){
                            System.out.println("Error " + e.getMessage());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<RecentTradesData>> call, Throwable t) {
                        Log.i("autolog", t.getMessage());
                    }
                });

            }else if(spinner.getSelectedItemId()==1){
                Call<List<RecentTradesData>> call = service.getRecentTradesDataXBTU19();

                call.enqueue(new Callback<List<RecentTradesData>>() {
                    @Override
                    public void onResponse(Call<List<RecentTradesData>> call, Response<List<RecentTradesData>> response) {
                        //Log.i("onResponse", response.message());
                        try {
                            recentTradesList = new ArrayList<RecentTradesData>();
                            recentTradesList = response.body();

                            if(recentTradesList == null || recentTradesList.isEmpty()){
                                return;
                            }
                            RecyclerView recenttrades_recyclerView = (RecyclerView) requireActivity().findViewById(R.id.recyclerRecentTrades);
                            layoutManager = new LinearLayoutManager(requireContext());
                            recenttrades_recyclerView.setLayoutManager(layoutManager);
                            RecyclerRecentTradesAdapter recenttrades_recyclerViewAdapter = new RecyclerRecentTradesAdapter(requireActivity().getApplicationContext(), recentTradesList);
                            recenttrades_recyclerView.setAdapter(recenttrades_recyclerViewAdapter);
                        }catch (Exception e){
                            System.out.println("Error " + e.getMessage());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<RecentTradesData>> call, Throwable t) {
                        Log.i("autolog", t.getMessage());
                    }
                });
            }else{
                Call<List<RecentTradesData>> call = service.getRecentTradesDataXBTZ19();

                call.enqueue(new Callback<List<RecentTradesData>>() {
                    @Override
                    public void onResponse(Call<List<RecentTradesData>> call, Response<List<RecentTradesData>> response) {
                        //Log.i("onResponse", response.message());
                        try {
                            recentTradesList = new ArrayList<RecentTradesData>();
                            recentTradesList = response.body();

                            if(recentTradesList == null || recentTradesList.isEmpty()){
                                return;
                            }
                            RecyclerView recenttrades_recyclerView = (RecyclerView) requireActivity().findViewById(R.id.recyclerRecentTrades);
                            layoutManager = new LinearLayoutManager(requireContext());
                            recenttrades_recyclerView.setLayoutManager(layoutManager);
                            RecyclerRecentTradesAdapter recenttrades_recyclerViewAdapter = new RecyclerRecentTradesAdapter(requireActivity().getApplicationContext(), recentTradesList);
                            recenttrades_recyclerView.setAdapter(recenttrades_recyclerViewAdapter);
                        }catch (Exception e){
                            System.out.println("Error " + e.getMessage());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<RecentTradesData>> call, Throwable t) {
                        Log.i("autolog", t.getMessage());
                    }
                });
            }

        }catch (Exception e) {Log.i("autolog", "Exception");}
    }



    public void setupSpinner(){

        spinner = (Spinner) requireActivity().findViewById(R.id.recenttrades_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireActivity(), R.layout.spinner_item, symbols_rct_id);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(requireActivity(),"Spinner title " + String.valueOf(position), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}