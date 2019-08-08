package skysoft.com.bitmexapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import skysoft.com.bitmexapp.Data.RecentTradesData;
import skysoft.com.bitmexapp.R;

public class RecyclerRecentTradesAdapter extends RecyclerView.Adapter<RecyclerRecentTradesAdapter.ViewHolder> {

    private List<RecentTradesData> item;
    private Context context ;
    private String buyColorStr = "00c087";
    private String sellColorStr = "e5036f";


    public RecyclerRecentTradesAdapter(Context context, List<RecentTradesData> item ) {
        this.item = item;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recenttrade_row, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String size = String.valueOf(item.get(position).getSize());
        String price = String.valueOf(item.get(position).getPrice());
        String side = item.get(position).getSide();

        String timestamp = String.valueOf(item.get(position).getTimestamp());
        String formatTime = String.valueOf(formatDate(timestamp));

        try {
            if (side.equals("Buy")) {
                holder.price.setTextColor(Color.parseColor("#00c087"));
            } else {
                holder.price.setTextColor(Color.parseColor("#e5036f"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        holder.price.setText(price);
        holder.size.setText(size);
        holder.time.setText(formatTime);

    }

    private String formatDate(String dateString){
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS" );
        sd.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date d = null;
        try {
            d = sd.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sd = new SimpleDateFormat("hh:mm:ss.SS");
        sd.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
            return sd.format(d);
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView  size, price, time;

        public ViewHolder(View itemView) {
            super(itemView);


            size = (TextView) itemView.findViewById(R.id.size);
            price = (TextView) itemView.findViewById(R.id.price);
            time = (TextView) itemView.findViewById(R.id.time);


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
        }
    }
}