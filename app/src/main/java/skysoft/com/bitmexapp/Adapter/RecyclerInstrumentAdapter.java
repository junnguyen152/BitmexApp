package skysoft.com.bitmexapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import skysoft.com.bitmexapp.Data.InstrumentData;
import skysoft.com.bitmexapp.R;

public class RecyclerInstrumentAdapter extends RecyclerView.Adapter<RecyclerInstrumentAdapter.ViewHolder> {

    private List<InstrumentData> item;
    private Context context ;
    public NumberFormat nf;
    private String buyColorStr = "00c087";
    private String sellColorStr = "e5036f";
    public static ArrayList<String> symbolIdList = new ArrayList<String>();


    public RecyclerInstrumentAdapter(Context context, List<InstrumentData> item ) {
        this.item = item;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_instrument_row, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        float price = item.get(position).getLastPrice();
        DecimalFormat df = new DecimalFormat("#.########");
        String lastPrice = df.format(price);
        String volume = "Vol "+String.valueOf(item.get(position).getVolume());


        float percentfloat = item.get(position).getLastChangePcnt()*100;
        nf = NumberFormat.getNumberInstance();
        nf.setMinimumFractionDigits(2);
        String percent = nf.format(percentfloat);

        holder.symbol.setText(item.get(position).getSymbol());
        holder.quote.setText(" / "+item.get(position).getQuoteCurrency());
        holder.volume.setText(volume);
        holder.lastPrice.setText(lastPrice);
        holder.state.setText(item.get(position).getState());

        if(item.get(position).getLastChangePcnt()>=0){
            holder.changePcnt.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.custom_changepcnt_up));
            holder.lastPrice.setTextColor(Color.parseColor("#00c087"));
            String changePcntUp = "+" + percent +"%";
            holder.changePcnt.setText(changePcntUp);
        }else{
            holder.changePcnt.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.custom_changepcnt));
            holder.lastPrice.setTextColor(Color.parseColor("#e5036f"));
            String changePcntDown = percent +"%";
            holder.changePcnt.setText(changePcntDown);
        }
        //symbolIdList.add(item.get(position).getSymbol());
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView symbol, quote, volume, lastPrice, state, changePcnt;

        public ViewHolder(View itemView) {
            super(itemView);

            symbol = (TextView) itemView.findViewById(R.id.symbol);
            quote = (TextView) itemView.findViewById(R.id.quote);
            volume = (TextView) itemView.findViewById(R.id.volume);
            lastPrice = (TextView) itemView.findViewById(R.id.lastPrice);
            state = (TextView) itemView.findViewById(R.id.state);
            changePcnt = (TextView) itemView.findViewById(R.id.changePcnt);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
        }
    }
}