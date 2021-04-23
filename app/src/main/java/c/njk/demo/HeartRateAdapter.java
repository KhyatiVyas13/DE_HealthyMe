package c.njk.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HeartRateAdapter extends RecyclerView.Adapter<HeartRateAdapter.viewHolder> {

    LineChart lineChart;
    ArrayList<Entry> heartValue;
    ArrayList<ILineDataSet> dataSets ;
    LineData data;
    private Context context;
    public HeartRateAdapter(Context context,LineChart lineChart, ArrayList<Entry> heartValue, ArrayList<ILineDataSet> dataSets) {
        this.lineChart = lineChart;
        this.heartValue = heartValue;
        this.dataSets = dataSets;
        this.context = context;
    }

    public ArrayList<ILineDataSet> getDataSets() {
        return dataSets;
    }

    public void setDataSets(ArrayList<ILineDataSet> dataSets) {
        this.dataSets = dataSets;
    }

    public LineData getData() {
        return data;
    }

    public void setData(LineData data) {
        this.data = data;
    }

    public LineChart getLineChart() {
        return lineChart;
    }

    public void setLineChart(LineChart lineChart) {
        this.lineChart = lineChart;
    }

    public ArrayList<Entry> getHeartValue() {
        return heartValue;
    }

    public void setHeartValue(ArrayList<Entry> heartValue) {
        this.heartValue = heartValue;
    }

    public ArrayList<Entry> heartValue(){
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
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.heart_rate_chart,parent,false);

//        LineDataSet lineDataSet = new LineDataSet(heartValue(),"Overall Heart Rate");
//        ArrayList<ILineDataSet> dataSets = new ArrayList<>() ;
//        dataSets.add(lineDataSet);
        LineData data = new LineData(dataSets);
        lineChart = view.findViewById(R.id.heartLineChart);
        lineChart.setData(data);
        lineChart.invalidate();
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        public viewHolder(View view) {
            super(view);
        }
    }
}
