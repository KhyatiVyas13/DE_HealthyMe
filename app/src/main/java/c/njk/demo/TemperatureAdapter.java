package c.njk.demo;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class TemperatureAdapter extends RecyclerView.Adapter<TemperatureAdapter.viewHolder>{

    private ArrayList<String> tName = new ArrayList<>();
    private ArrayList<String> tValue = new ArrayList<>();
    private ArrayList<Integer> tImage = new ArrayList<>();
    private Context context;

    public TemperatureAdapter(Context context,ArrayList<String> tName, ArrayList<Integer> tImage, ArrayList<String> tValue ) {
        this.tName = tName;
        this.tValue = tValue;
        this.tImage = tImage;
        this.context = context;
    }

    @NonNull
    @Override
    public TemperatureAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.temp_sleep_cards, parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TemperatureAdapter.viewHolder holder, final int position) {

        //Setting images
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_launcher_background);
        Glide.with(context).load(tImage.get(position)).apply(requestOptions).into(holder.cardImage);

        //Setting names
        holder.titleName.setText(tName.get(position));
        holder.value.setText(tValue.get(position));

        Typeface kohoFont = Typeface.createFromAsset(context.getAssets(),"fonts/KoHo-Medium.ttf");
        holder.value.setTypeface(kohoFont);

        Typeface kohoFont1 = Typeface.createFromAsset(context.getAssets(),"fonts/KoHo-Bold.ttf");
        holder.titleName.setTypeface(kohoFont1);

        holder.cardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "You clicked " +hName.get(position) , Toast.LENGTH_SHORT).show();

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                if(position == 2) {
                    Fragment heartFragment = new HeartRateFragment();
                    activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.mainLayout, heartFragment).addToBackStack(null).commit();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return tImage.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView cardImage;
        TextView titleName, value;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            cardImage = itemView.findViewById(R.id.imageIcon2);
            titleName = itemView.findViewById(R.id.title_name);
            value = itemView.findViewById(R.id.tv_value);
        }
    }
}
