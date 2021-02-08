package c.njk.demo;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ProgressBar mainProgressBar;
    private TextView stepTaken,totalSteps;
    private View view;
    int i = 0;

    private static final int NUM_COLS = 2;
    private ArrayList<String> hName = new ArrayList<>();
    private ArrayList<Integer> hImage = new ArrayList<>();

    private ArrayList<String> tName = new ArrayList<>();
    private ArrayList<String> tValue = new ArrayList<>();
    private ArrayList<Integer> tImage = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //initializing vectors
        hImage.add(R.drawable.ic_health_);
        hName.add("Health Advice");

//        hImage.add(R.drawable.heart_rate);
//        hName.add("Heart rate");

//        hImage.add(R.drawable.temperature);
//        hName.add("Body Temperature");
//
//        hImage.add(R.drawable.sleep);
//        hName.add("Sleep-O-Meter");

        tImage.add(R.drawable.ic_thermometer);
        tName.add("Body Temperature");
        tValue.add("98.7 f");

        tImage.add(R.drawable.ic_moon);
        tName.add("Sleep");
        tValue.add("7 Hrs 35 Min");

        tImage.add(R.drawable.ic_heart);
        tName.add("Heart Rate");
        tValue.add("80 BPM");

        tImage.add(R.drawable.ic_active_time);
        tName.add("Active Time");
        tValue.add("35 Min");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home,container,false);

        //Progressbar for step counted
        mainProgressBar = view.findViewById(R.id.step_progress_bar);
        stepTaken = view.findViewById(R.id.stepsTaken);
        totalSteps = view.findViewById(R.id.totalSteps);

        final Handler handler = new Handler();
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
        },1200);

        //initializing recyclerview
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        StaggeredHomeAdapter staggeredHomeAdapter = new StaggeredHomeAdapter(getContext(),hName,hImage);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLS, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(staggeredHomeAdapter);

        //another recyclerView
        RecyclerView recyclerView2 = view.findViewById(R.id.secondRecyclerView);
        TemperatureAdapter temperatureAdapter = new TemperatureAdapter(getContext(),tName,tImage,tValue);
        StaggeredGridLayoutManager staggeredGridLayoutManager2 = new StaggeredGridLayoutManager(NUM_COLS, LinearLayoutManager.VERTICAL);
        //recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView2.setLayoutManager(staggeredGridLayoutManager2);
        recyclerView2.setAdapter(temperatureAdapter);


        Typeface kohoFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/KoHo-Medium.ttf");
        stepTaken.setTypeface(kohoFont);

        return view;
    }
}