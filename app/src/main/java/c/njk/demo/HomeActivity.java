package c.njk.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    //private RecyclerView recyclerView;
    //RecyclerView.Adapter homeAdapter;
    //RecyclerView.LayoutManager layoutManager;
    private static final int NUM_COLS = 2;
    private ArrayList<String> hName = new ArrayList<>();
    private ArrayList<Integer> hImage = new ArrayList<>();

//    String[] Title = {"About your health", "Body Temperature", "Heart rate", "Sleep"};
//    String[] Description = {"You need to do some exercise \n Walk more", "96.7 f", "75 BPM", "6 Hrs 45 Mins"};
//    int[] vectors = {R.drawable.ic_health_prediction, R.drawable.ic_temprature_icon, R.drawable.ic_heartrate, R.drawable.ic_sleep};

    private ProgressBar mainProgressBar, caloriesProgressBar, distanceProgressBar,activeTimeProgressBar;
    private TextView stepTaken, totalSteps, caloriesBurnt, calories, distanceWalked, distance,activeTimeCount,activeTime;
    int i = 0;
    int j = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //NoActionbar
        getSupportActionBar().hide();

        //Progressbar for step counted
        mainProgressBar = findViewById(R.id.step_progress_bar);
        stepTaken = findViewById(R.id.stepsTaken);
        totalSteps = findViewById(R.id.totalSteps);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (i <= 100){
                    stepTaken.setText("" + j);
                    mainProgressBar.setProgress(i);
                    i++;
                    handler.postDelayed(this, 300);
                }else {
                    handler.removeCallbacks(this);
                }
            }
        },300);

        //Calories Progressbar
        caloriesProgressBar = findViewById(R.id.calories_progress_bar);
        caloriesBurnt = findViewById(R.id.caloriesBurnt);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (i <= 110){
                    caloriesBurnt.setText("" + j);
                    caloriesProgressBar.setProgress(i);
                    i++;
                    handler.postDelayed(this, 300);
                }else {
                    handler.removeCallbacks(this);
                }
            }
        },300);

        //Distance progressbar

        distanceProgressBar = findViewById(R.id.distance_progress_bar);
        distanceWalked = findViewById(R.id.distanceWalked);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (i <= 108){
                    distanceProgressBar.setProgress(i);
                    distanceWalked.setText("" + 1);
                    i++;
                    handler.postDelayed(this,300);
                }else{
                    handler.removeCallbacks(this);
                }
            }
        },300);

        //Active time progressbar
        activeTimeProgressBar = findViewById(R.id.active_time_progress_bar);
        activeTimeCount = findViewById(R.id.activeTimeCount);
        activeTime = findViewById(R.id.activeTime);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (i <= 105){
                    activeTimeCount.setText("" + 1);
                    activeTimeProgressBar.setProgress(i);
                    i++;
                    handler.postDelayed(this,300);
                }else{
                    handler.removeCallbacks(this);
                }
            }
        },300);

        initImages();

//        //Recycler view
//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        //homeAdapter = new homeAdapter(this,Title,Description,vectors);
//        recyclerView.setAdapter(homeAdapter);

    }

    private void initImages(){

        hImage.add(R.drawable.health);
        hName.add("About you Health");

        hImage.add(R.drawable.heart_rate);
        hName.add("Heart rate");

        hImage.add(R.drawable.temperature);
        hName.add("Body Temperature");

        hImage.add(R.drawable.sleep);
        hName.add("Sleep-O-Meter");

        initRecyclerView();
    }

    private void initRecyclerView(){

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        StaggeredHomeAdapter staggeredHomeAdapter = new StaggeredHomeAdapter(this,hName,hImage);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLS,LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(staggeredHomeAdapter);
    }

}