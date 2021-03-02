package c.njk.demo;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Handler;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class HomeFragment extends Fragment implements StaggeredHomeAdapter.HomeAdapterEvents {

    private ProgressBar mainProgressBar;
    private TextView stepTaken,totalSteps;
    private View view;
    int i = 0;
    String userId;

    private static final int NUM_COLS = 2;
    private ArrayList<String> hName = new ArrayList<>();
    private ArrayList<Integer> hImage = new ArrayList<>();

    private ArrayList<String> tName = new ArrayList<>();
    private ArrayList<String> tValue = new ArrayList<>();
    private ArrayList<Integer> tImage = new ArrayList<>();
    TemperatureAdapter temperatureAdapter;
    private String steps, temperature, heartBeat;
    private boolean called = true;
    RecyclerView recyclerView2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //initializing vectors
        hImage.add(R.drawable.ic_health_);
        hName.add("Save Report");

//        hImage.add(R.drawable.heart_rate);
//        hName.add("Heart rate");

//        hImage.add(R.drawable.temperature);
//        hName.add("Body Temperature");
//
//        hImage.add(R.drawable.sleep);
//        hName.add("Sleep-O-Meter");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home,container,false);

        //Progressbar for step counted
        mainProgressBar = view.findViewById(R.id.step_progress_bar);
        stepTaken = view.findViewById(R.id.stepsTaken);
        totalSteps = view.findViewById(R.id.totalSteps);

        recyclerView2 = view.findViewById(R.id.secondRecyclerView);
        temperatureAdapter = new TemperatureAdapter(getContext());
        StaggeredGridLayoutManager staggeredGridLayoutManager2 = new StaggeredGridLayoutManager(NUM_COLS, LinearLayoutManager.VERTICAL);
        //recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView2.setLayoutManager(staggeredGridLayoutManager2);
        recyclerView2.setAdapter(temperatureAdapter);

        /*final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (i <= 6000){
                    stepTaken.setText("" + i);
                    mainProgressBar.setProgress(i);
                    i++;
                    handler.postDelayed(this, 1200);
                }else {
                    handler.removeCallbacks(this);
                }
            }
        },1200);*/

        //initializing recyclerview
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        final StaggeredHomeAdapter staggeredHomeAdapter = new StaggeredHomeAdapter(getContext(),hName,hImage);
        staggeredHomeAdapter.setHomeClickEvents(this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLS, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(staggeredHomeAdapter);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            userId = user.getUid();

            FirebaseDatabase database = FirebaseDatabase.getInstance();

            DatabaseReference stores = database.getReference("healthData");

            stores.child(userId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    steps = snapshot.getValue(Health.class).getSteps();
                    temperature = snapshot.getValue(Health.class).getTemperature();
                    heartBeat = snapshot.getValue(Health.class).getHeartBeat();

                    setData();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }



        Typeface kohoFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/KoHo-Medium.ttf");
        stepTaken.setTypeface(kohoFont);

        return view;
    }

    public void setData(){
        stepTaken.setText("" + steps);

        mainProgressBar.setProgress(Integer.parseInt(steps));

        tImage.add(R.drawable.ic_thermometer);
        tName.add("Body Temperature");
        tValue.add(temperature);

        tImage.add(R.drawable.ic_moon);
        tName.add("Sleep");
        tValue.add("7 Hrs 35 Min");

        tImage.add(R.drawable.ic_heart);
        tName.add("Heart Rate");
        tValue.add(heartBeat);

        tImage.add(R.drawable.ic_active_time);
        tName.add("Active Time");
        tValue.add("35 Min");

        //another recyclerView
        temperatureAdapter.setData(tName,tImage,tValue);

    }

    @Override
    public void onCardClicked() {

        Date d = new Date();
        CharSequence s  = DateFormat.format("MMMM d, yyyy ", d.getTime());

        Health health = new Health(temperature, heartBeat, steps, s.toString());

        //Example you need save a Store in

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference stores = database.getReference("healthSavedData");

        stores.child(userId).push().setValue(health);

        Toast.makeText(requireContext(), "Report Saved SuccessFully", Toast.LENGTH_SHORT).show();
    }
}