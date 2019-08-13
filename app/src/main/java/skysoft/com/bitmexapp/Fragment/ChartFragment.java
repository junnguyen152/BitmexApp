package skysoft.com.bitmexapp.Fragment;


import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;

import skysoft.com.bitmexapp.Adapter.MyMarkerView;
import skysoft.com.bitmexapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragment extends Fragment {

    public LineChart chart;
    public ChartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chart = requireActivity().findViewById(R.id.chart);

        chart.setTouchEnabled(true);
        chart.setPinchZoom(true);

        MyMarkerView mv = new MyMarkerView(requireContext(), R.layout.custom_market_view);

        mv.setChartView(chart);
        chart.setMarker(mv);
        renderData();
    }

    public void renderData() {
        /*LimitLine llXAxis = new LimitLine(10f, "Index 10");
        llXAxis.setLineWidth(4f);
        llXAxis.enableDashedLine(10f, 10f, 0f);
        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        llXAxis.setTextSize(10f);
        llXAxis.setTextColor(Color.WHITE);*/

        //buy chart
        XAxis xAxis = chart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setAxisMaximum(12000);
        xAxis.setAxisMinimum(9000);
        xAxis.setTextColor(Color.WHITE);;
        xAxis.setDrawLimitLinesBehindData(true);

        /*LimitLine ll1 = new LimitLine(215f, "Maximum Limit");
        ll1.setLineWidth(4f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        ll1.setTextSize(10f);
        ll1.setTextColor(Color.WHITE);

        LimitLine ll2 = new LimitLine(70f, "Minimum Limit");
        ll2.setLineWidth(4f);
        ll2.enableDashedLine(10f, 10f, 0f);
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        ll2.setTextSize(10f);
        ll2.setTextColor(Color.WHITE);*/

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        //leftAxis.addLimitLine(ll1);
        //leftAxis.addLimitLine(ll2);
        leftAxis.setAxisMaximum(350f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setDrawLimitLinesBehindData(false);
        leftAxis.setTextColor(Color.WHITE);

        chart.getAxisRight().setEnabled(false);
        setData();
    }

    public void setData() {
        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(9000, 50));
        values.add(new Entry(9100, 100));
        values.add(new Entry(9200, 80));
        values.add(new Entry(9500, 120));
        values.add(new Entry(10000, 110));
        values.add(new Entry(10300, 150));
        values.add(new Entry(10500, 250));

        ArrayList<Entry> values1 = new ArrayList<>();
        values1.add(new Entry(TradesFragment.buyList.get(2).getPrice(), 50));
        values1.add(new Entry(TradesFragment.buyList.get(1).getPrice(), 100));
        values1.add(new Entry(TradesFragment.buyList.get(0).getPrice(), 80));

        LineDataSet set1;
        LineDataSet set2;
        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            set2 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set2.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            //dataset 1
            set1 = new LineDataSet(values, "Buy");
            set1.setDrawIcons(false);
            set1.enableDashedLine(10f, 5f, 0f);
            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setColor(Color.parseColor("#00c087"));
            set1.setCircleColor(Color.parseColor("#f5bc00"));
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setValueTextColor(Color.parseColor("#00c087"));
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            //Dataset 2
            set2 = new LineDataSet(values1, "Sell");
            set2.setDrawIcons(false);
            set2.enableDashedLine(10f, 5f, 0f);
            set2.enableDashedHighlightLine(10f, 5f, 0f);
            set2.setColor(Color.parseColor("#e5036f"));
            set2.setCircleColor(Color.parseColor("#f5bc00"));
            set2.setLineWidth(1f);
            set2.setCircleRadius(3f);
            set2.setDrawCircleHole(false);
            set2.setValueTextSize(9f);
            set2.setValueTextColor(Color.parseColor("#e5036f"));
            set2.setDrawFilled(true);
            set2.setFormLineWidth(1f);
            set2.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set2.setFormSize(15.f);

            if (Utils.getSDKInt() >= 18) {
                Drawable drawable = ContextCompat.getDrawable(requireContext(), R.drawable.fade_buy);
                set1.setFillDrawable(drawable);
                Drawable drawable1 = ContextCompat.getDrawable(requireContext(), R.drawable.fade_sell);
                set2.setFillDrawable(drawable1);
            } else {
                set1.setFillColor(Color.parseColor("#f5bc00"));
                set2.setFillColor(Color.parseColor("#f5bc00"));
            }
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            dataSets.add(set2);
            LineData data = new LineData(dataSets);
            chart.setData(data);
            chart.getLegend().setTextColor(Color.WHITE);
        }
    }
}
