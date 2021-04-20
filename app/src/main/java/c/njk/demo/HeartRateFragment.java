package c.njk.demo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;


public class HeartRateFragment extends Fragment {

    private TextView heartTitle;
    private Context context;
    private LineChart lineChart;
    private ArrayList<Entry> heartValue(){
        ArrayList<Entry> val = new ArrayList<Entry>();
        val.add(new Entry(0,80));
        val.add(new Entry(1,70));
        val.add(new Entry(2,67));
        val.add(new Entry(3,78));
        val.add(new Entry(4,80));
        val.add(new Entry(5,80));
        val.add(new Entry(6,85));
        val.add(new Entry(7,90));
        val.add(new Entry(8,95));
        val.add(new Entry(9,100));
        return val;
    }


    public HeartRateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_heart_rate, container, false);
        heartTitle = view.findViewById(R.id.heartTitle);
        lineChart = view.findViewById(R.id.heartLineChart);
//        lineChart.setTouchEnabled(true);
//        lineChart.setPinchZoom(true);

        RecyclerView recyclerView = view.findViewById(R.id.heartRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        LineDataSet lineDataSet = new LineDataSet(heartValue(),"Overall Heart Rate");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>() ;
        dataSets.add(lineDataSet);

        //customizing line chart
        lineDataSet.setColor(Color.parseColor("#0061a8"));
        lineDataSet.setLineWidth(3);

//        LineData data = new LineData(dataSets);
//        lineChart.setData(data);
//        lineChart.invalidate();
        HeartRateAdapter heartRateAdapter = new HeartRateAdapter(getContext(),lineChart,heartValue(),dataSets);
        recyclerView.setAdapter(heartRateAdapter);

        Typeface kohoFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/KoHo-Medium.ttf");
        heartTitle.setTypeface(kohoFont);
        return view;
    }
}