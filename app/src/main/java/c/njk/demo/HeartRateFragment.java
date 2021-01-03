package c.njk.demo;

import android.content.Context;
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
    LineChart lineChart;
    private ArrayList<Entry> heartValue(){
        ArrayList<Entry> val = new ArrayList<Entry>();
        val.add(new Entry(0,20));
        val.add(new Entry(1,50));
        val.add(new Entry(2,30));
        val.add(new Entry(3,20));
        val.add(new Entry(4,40));
        return val;
    }
    LineDataSet lineDataSet = new LineDataSet(heartValue(),"Overall Heart Rate");
    ArrayList<ILineDataSet> dataSets = new ArrayList<>() ;

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
        RecyclerView recyclerView = view.findViewById(R.id.heartRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dataSets.add(lineDataSet);
        LineData data = new LineData(dataSets);
        HeartRateAdapter heartRateAdapter = new HeartRateAdapter(getContext(),lineChart,heartValue(),dataSets);
       recyclerView.setAdapter(heartRateAdapter);

        Typeface kohoFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/KoHo-Medium.ttf");
        heartTitle.setTypeface(kohoFont);
        return view;
    }
}