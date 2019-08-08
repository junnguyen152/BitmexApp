package skysoft.com.bitmexapp.Adapter;

import android.content.Context;
import android.support.constraint.Guideline;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import skysoft.com.bitmexapp.Data.OrderBookData;
import skysoft.com.bitmexapp.R;

public class RecyclerSellOrderAdapter extends RecyclerView.Adapter<RecyclerSellOrderAdapter.ViewHolder> {

    private List<OrderBookData> item;
    private Context context ;
    private long total = 0;
    public long finaltotal;
    public static long selltotal;


    public RecyclerSellOrderAdapter(Context context, List<OrderBookData> item ) {
        this.item = item;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sellorder_row, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String size = String.valueOf(item.get(position).getSize());
        String price = String.valueOf(item.get(position).getPrice());
        String side = item.get(position).getSide();
        selltotal = 0;
        total = 0;
        for(int i = position; i < item.size() ; i++){
            total += item.get(i).getSize();
        }
        for(int i = 0; i < item.size(); i++){
            selltotal += item.get(i).getSize();
        }

        String strTotal = String.valueOf(total);
        holder.symbol.setText(strTotal);
        holder.size.setText(size);
        holder.price.setText(price);

        finaltotal = selltotal + RecyclerBuyOrderAdapter.buytotal;
        float sellPer = (float)total/finaltotal;
        //String yourVal = "1";
        //float a = (Float.valueOf(yourVal)).floatValue();
        holder.background_guiline.setGuidelinePercent(1-sellPer);
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView symbol, size, price;
        public Guideline background_guiline;
        public ViewHolder(View itemView) {
            super(itemView);

            symbol = (TextView) itemView.findViewById(R.id.symbol);
            size = (TextView) itemView.findViewById(R.id.size);
            price = (TextView) itemView.findViewById(R.id.price);
            background_guiline = (Guideline) itemView.findViewById(R.id.background_guideline);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
        }
    }
}