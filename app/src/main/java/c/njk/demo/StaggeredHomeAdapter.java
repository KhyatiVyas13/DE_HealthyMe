package c.njk.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StaggeredHomeAdapter extends RecyclerView.Adapter<StaggeredHomeAdapter.ViewHolder> {

    //for names & images
    private ArrayList<String> hName = new ArrayList<>();
    private ArrayList<Integer> hImage = new ArrayList<>();
    private Context context;

    public StaggeredHomeAdapter( Context context,ArrayList<String> hName, ArrayList<Integer> hImage) {
        this.hName = hName;
        this.hImage = hImage;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_items, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        //Setting images
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_launcher_background);
        Glide.with(context).load(hImage.get(position)).apply(requestOptions).into(holder.image);

        //Setting names
        holder.name.setText(hName.get(position));
         holder.image.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Toast.makeText(context,hName.get(position)+"clicked",Toast.LENGTH_SHORT).show();
             }
         });
    }

    @Override
    public int getItemCount() {
        return hImage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageIcon);
            name = itemView.findViewById(R.id.tv_name);
        }
    }
}
