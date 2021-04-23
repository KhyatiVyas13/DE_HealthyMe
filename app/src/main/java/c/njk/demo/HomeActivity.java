package c.njk.demo;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    //private RecyclerView recyclerView;
    //RecyclerView.Adapter homeAdapter;
    //RecyclerView.LayoutManager layoutManager;
    private static final int NUM_COLS = 2;
    private ArrayList<String> hName = new ArrayList<>();
    private ArrayList<Integer> hImage = new ArrayList<>();
    public static final String mypreference = "mypref";
    public static final String Name = "loginKey";
    SharedPreferences sharedpreferences;


//    String[] Title = {"About your health", "Body Temperature", "Heart rate", "Sleep"};
//    String[] Description = {"You need to do some exercise \n Walk more", "96.7 f", "75 BPM", "6 Hrs 45 Mins"};
//    int[] vectors = {R.drawable.ic_health_prediction, R.drawable.ic_temprature_icon, R.drawable.ic_heartrate, R.drawable.ic_sleep};

    private ProgressBar mainProgressBar, caloriesProgressBar, distanceProgressBar,activeTimeProgressBar;
    private TextView stepTaken, totalSteps, caloriesBurnt, calories, distanceWalked, distance,activeTimeCount,activeTime;
    int i = 0;
    int j = 1;

    private SNavigationDrawer sNavigationDrawer;
    Class fragmentClass;
    public static Fragment fragment;
    String userId;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //NoActionbar
        getSupportActionBar().hide();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {
            // No session user
            return;
        }

        userId = user.getUid();

        Health health = new Health("90.8 f", "100 BPM", "100");

        //Example you need save a Store in

        //HealthData

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference healthData = database.getReference("healthData");

        healthData.child(userId).setValue(health);

        healthData.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

//                for(DataSnapshot ds : snapshot.getChildren()){
//                    Log.e("DataSnapShot", ds.getValue(Store.class).getUrl());
//                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        //Progressbar for step counted
//        mainProgressBar = findViewById(R.id.step_progress_bar);
//        stepTaken = findViewById(R.id.stepsTaken);
//        totalSteps = findViewById(R.id.totalSteps);
//
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                if (i <= 6000){
//                    stepTaken.setText("" + i);
//                    mainProgressBar.setProgress(i);
//                    i++;
//                    handler.postDelayed(this, 1200);
//                }else {
//                    handler.removeCallbacks(this);
//                }
//            }
//        },1200);
//
//        //Calories Progressbar
//        caloriesProgressBar = findViewById(R.id.calories_progress_bar);
//        caloriesBurnt = findViewById(R.id.caloriesBurnt);
//
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                if (i <= 110){
//                    caloriesBurnt.setText("" + j);
//                    caloriesProgressBar.setProgress(i);
//                    i++;
//                    handler.postDelayed(this, 300);
//                }else {
//                    handler.removeCallbacks(this);
//                }
//            }
//        },300);
//
//        //Distance progressbar
//
//        distanceProgressBar = findViewById(R.id.distance_progress_bar);
//        distanceWalked = findViewById(R.id.distanceWalked);
//
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (i <= 108){
//                    distanceProgressBar.setProgress(i);
//                    distanceWalked.setText("" + 1);
//                    i++;
//                    handler.postDelayed(this,300);
//                }else{
//                    handler.removeCallbacks(this);
//                }
//            }
//        },300);

//        //Active time progressbar
//        activeTimeProgressBar = findViewById(R.id.active_time_progress_bar);
//        activeTimeCount = findViewById(R.id.activeTimeCount);
//        activeTime = findViewById(R.id.activeTime);

//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (i <= 105){
//                    activeTimeCount.setText("" + 1);
//                    activeTimeProgressBar.setProgress(i);
//                    i++;
//                    handler.postDelayed(this,300);
//                }else{
//                    handler.removeCallbacks(this);
//                }
//            }
//        },300);

        //Initializing vectors in recycler view
        //initImages();

        //navigation drawer
        sNavigationDrawer = findViewById(R.id.navDrawer);
        sNavigationDrawer.setAppbarTitleTV("HealthyMe");
        Typeface kohoFont = Typeface.createFromAsset(getAssets(),"fonts/KoHo-Medium.ttf");
        sNavigationDrawer.setAppbarTitleTypeface(kohoFont);

        //Adding menu items
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Home",R.drawable.ic_bg));
        menuItems.add(new MenuItem("Profile",R.drawable.ic_bg));
        menuItems.add(new MenuItem("Saved Reports",R.drawable.ic_bg));
        menuItems.add(new MenuItem("FAQs",R.drawable.ic_bg));
        menuItems.add(new MenuItem("About us",R.drawable.ic_bg));
        menuItems.add(new MenuItem("Logout",R.drawable.ic_bg));

        sNavigationDrawer.setMenuItemList(menuItems);

        fragmentClass = HomeFragment.class;
        try {
            fragment = (Fragment)fragmentClass.newInstance();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InstantiationException e){
            e.printStackTrace();
        }

        if(fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.mainLayout,fragment).commit();

        }

        //Menu Item selection listener
        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
                                                         @Override
                                                         public void onMenuItemClicked(int position) {
                                                             System.out.println("Position " + position);

                                                             switch (position) {
                                                                 case 0: {
                                                                      fragmentClass = HomeFragment.class;
                                                                     break;
                                                                 }
                                                                 case 1: {
                                                                      fragmentClass = ProfileFragment.class;
                                                                     break;
                                                                 }
                                                                 case 2: {
                                                                      fragmentClass = SavedReportsFragment.class;
                                                                     break;
                                                                 }
                                                                 case 3: {
                                                                     fragmentClass = SavedReportsFragment.class;
                                                                     break;
                                                                 }

                                                                 case 4:{
                                                                     fragmentClass = SavedReportsFragment.class;
                                                                     break;
                                                                 }
                                                                 //case 5 is for logout
                                                                 case 5:{
                                                                     sharedpreferences = getSharedPreferences(mypreference,
                                                                             Context.MODE_PRIVATE);
                                                                     SharedPreferences.Editor editor = sharedpreferences.edit();

                                                                     editor.putBoolean(Name, false);
                                                                     editor.commit();

                                                                     FirebaseAuth.getInstance().signOut();
                                                                     startActivity(new Intent(HomeActivity.this,LoginActivity.class));
                                                                     finish();
                                                                     break;

                                                                 }

                                                             }


                                                             sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {
                                                                 @Override
                                                                 public void onDrawerOpening() {

                                                                 }

                                                                 @Override
                                                                 public void onDrawerClosing() {
                                                                     try {
                                                                         fragment = (Fragment) fragmentClass.newInstance();
                                                                     } catch (IllegalAccessException e) {
                                                                         e.printStackTrace();
                                                                     } catch (InstantiationException e) {
                                                                         e.printStackTrace();
                                                                     }

                                                                     if (fragment != null) {
                                                                         FragmentManager fragmentManager = getSupportFragmentManager();
                                                                         fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.mainLayout, fragment).commit();

                                                                     }
                                                                 }

                                                                 @Override
                                                                 public void onDrawerOpened() {

                                                                 }

                                                                 @Override
                                                                 public void onDrawerClosed() {

                                                                 }

                                                                 @Override
                                                                 public void onDrawerStateChanged(int newState) {
                                                                     System.out.println("State "+newState);
                                                                 }
                                                             });
                                                         }
                                                     });


//        //Recycler view
//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        //homeAdapter = new homeAdapter(this,Title,Description,vectors);
//        recyclerView.setAdapter(homeAdapter);

    }

    //for vectors
//    private void initImages(){
//
//        hImage.add(R.drawable.health);
//        hName.add("About your Health");
//
//        hImage.add(R.drawable.heart_rate);
//        hName.add("Heart rate");
//
//        hImage.add(R.drawable.temperature);
//        hName.add("Body Temperature");
//
//        hImage.add(R.drawable.sleep);
//        hName.add("Sleep-O-Meter");
//
//        initRecyclerView();
//    }

//    private void initRecyclerView(){
//
//        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//        StaggeredHomeAdapter staggeredHomeAdapter = new StaggeredHomeAdapter(this,hName,hImage);
//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLS,LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(staggeredGridLayoutManager);
//        recyclerView.setAdapter(staggeredHomeAdapter);
//    }

}