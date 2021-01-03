package c.njk.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
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

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.heart_rate_chart,parent,false);

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
        return heartValue.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        public viewHolder(View view) {
            super(view);
        }
    }
}
