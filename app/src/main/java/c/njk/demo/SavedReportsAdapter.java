package c.njk.demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SavedReportsAdapter extends RecyclerView.Adapter<SavedReportsAdapter.viewHolder> {

     ArrayList<Health> mHealth = new ArrayList<>();

    @NonNull
    @Override
    public SavedReportsAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_saved_reports, parent,false);
        return new SavedReportsAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedReportsAdapter.viewHolder holder, int position) {

        holder.heartBeat.setText(mHealth.get(position).getHeartBeat());
        holder.temperature.setText(mHealth.get(position).getTemperature());
        holder.steps.setText(mHealth.get(position).getSteps());
        holder.date.setText(mHealth.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return mHealth.size();
    }

    public void setData(ArrayList<Health> health){
        mHealth.addAll(health);
        notifyDataSetChanged();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView heartBeat, temperature, steps, date;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            heartBeat = itemView.findViewById(R.id.textView2);
            temperature = itemView.findViewById(R.id.textView4);
            steps = itemView.findViewById(R.id.textView6);
            date = itemView.findViewById(R.id.date);
        }
    }
}
