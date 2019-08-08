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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import skysoft.com.bitmexapp.API.APIService;
import skysoft.com.bitmexapp.Adapter.RecyclerInstrumentAdapter;
import skysoft.com.bitmexapp.Data.InstrumentData;
import skysoft.com.bitmexapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MarketsFragment extends Fragment {

    private LinearLayoutManager layoutManager;
    private List<InstrumentData> instrumentList = null;
    public Handler handler;
    Retrofit retrofit = null;
    SwipeRefreshLayout mSwipeRefreshLayout;


    public MarketsFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_markets, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getInstrumentList();

        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getInstrumentList();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
/*
        void refreshItems(){
            // Load items
            // ...

            // Load complete
            onItemsLoadComplete();
        }

        void onItemsLoadComplete(){
            // Update the adapter and notify data set changed
            // ...

            // Stop refresh animation
            mSwipeRefreshLayout.setRefreshing(false);
        }*/
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    private void getInstrumentList(){

        try {
            String url = "https://www.bitmex.com/api/v1/";
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }

            APIService service = retrofit.create(APIService.class);

            Call<List<InstrumentData>> call = service.getInstrumentData();

            call.enqueue(new Callback<List<InstrumentData>>() {
                @Override
                public void onResponse(Call<List<InstrumentData>> call, Response<List<InstrumentData>> response) {
                    //Log.i("onResponse", response.message());
                    try {
                        instrumentList = response.body();
                        if(instrumentList == null || instrumentList.isEmpty()){
                            return;
                        }

                        RecyclerView recyclerView = (RecyclerView) requireActivity().findViewById(R.id.recyclerInstrument);

                        layoutManager = new LinearLayoutManager(requireContext());
                        recyclerView.setLayoutManager(layoutManager);


                        RecyclerInstrumentAdapter recyclerViewAdapter = new RecyclerInstrumentAdapter(requireActivity().getApplicationContext(), instrumentList);
                        recyclerView.setAdapter(recyclerViewAdapter);
                    }catch (Exception e){
                        System.out.println("Error " + e.getMessage());
                    }
                }
                @Override
                public void onFailure(Call<List<InstrumentData>> call, Throwable t) {
                    Log.i("autolog", t.getMessage());
                }
            });
        }catch (Exception e) {Log.i("autolog", "Exception");}
    }
}
