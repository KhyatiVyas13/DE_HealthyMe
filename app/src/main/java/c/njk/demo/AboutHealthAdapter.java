package c.njk.demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AboutHealthAdapter extends RecyclerView.Adapter<AboutHealthAdapter.viewHolder> {

    ArrayList<adviceModel> healthAdvice;

    public AboutHealthAdapter(ArrayList<adviceModel> healthAdvice) {
        this.healthAdvice = healthAdvice;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.health_prediction_items,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.hAdvice.setText(healthAdvice.get(position).getAdvice());
    }

    @Override
    public int getItemCount() {
        return healthAdvice.size();
    }

    class viewHolder extends RecyclerView.ViewHolder{

        TextView hAdvice;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            hAdvice = itemView.findViewById(R.id.healthAdvice);
        }
    }
}
