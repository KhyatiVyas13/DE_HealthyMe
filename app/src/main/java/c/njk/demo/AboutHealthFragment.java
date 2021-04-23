package c.njk.demo;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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


public class AboutHealthFragment extends Fragment {

    private TextView healthTitle;
    private ArrayList<adviceModel> healthAdvice;
    //private Button saveBtn;
    String userId;
    RecyclerView recyclerView;
    private String steps, temperature, heartBeat;

    public AboutHealthFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about_health, container, false);
        healthTitle = view.findViewById(R.id.healthTitle);
        //saveBtn = view.findViewById(R.id.saveReportButton);
        recyclerView = view.findViewById(R.id.healthRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        healthAdvice = new ArrayList<>();


        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference stores = database.getReference("healthPredict");

        //      stores.push().setValue(new AdviceModel1("Perform relaxation techniques","Keep hydrated","Avoid excessive alcohol use", "Exercise regularly.", "102","100","101"));
        ///       stores.push().setValue(new AdviceModel1("Reduce or eliminate stimulant intake","Keep electrolytes balanced","drink plenty of fluids and rest", "Exercise regularly.", "102","100","101"));
//        stores.push().setValue(new AdviceModel1("You can take a fever reducer","Call your doctor if your Hearbeat  is over 110 F","drink plenty of fluids and rest", "doesn't go down within an hour after you take a fever-reducing medication..", "102","100","101"));


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();



        if (user != null) {
            userId = user.getUid();

            FirebaseDatabase database1 = FirebaseDatabase.getInstance();

            DatabaseReference stores1 = database1.getReference("healthData");

            stores1.child(userId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    steps = snapshot.getValue(Health.class).getSteps();
                    temperature = snapshot.getValue(Health.class).getTemperature();
                    heartBeat = snapshot.getValue(Health.class).getHeartBeat();


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

        stores.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds: snapshot.getChildren()){

                    if (Integer.parseInt(heartBeat.substring(0,3)) ==100){

                        if (Integer.parseInt( ds.getValue(AdviceModel1.class).getHeartBeat()) == 100){
                            healthAdvice.add(new adviceModel(ds.getValue(AdviceModel1.class).advice1));
                            healthAdvice.add(new adviceModel(ds.getValue(AdviceModel1.class).advice2));
                            healthAdvice.add(new adviceModel(ds.getValue(AdviceModel1.class).advice3));
                            healthAdvice.add(new adviceModel(ds.getValue(AdviceModel1.class).advice4));
                        }

                    }else if (Integer.parseInt(heartBeat.substring(0,3)) >=101 && Integer.parseInt(heartBeat.substring(0,3)) <=102){
                        if (Integer.parseInt( ds.getValue(AdviceModel1.class).getHeartBeat()) >= 101 && Integer.parseInt( ds.getValue(AdviceModel1.class).getHeartBeat()) <= 102){
                            healthAdvice.add(new adviceModel(ds.getValue(AdviceModel1.class).advice1));
                            healthAdvice.add(new adviceModel(ds.getValue(AdviceModel1.class).advice2));
                            healthAdvice.add(new adviceModel(ds.getValue(AdviceModel1.class).advice3));
                            healthAdvice.add(new adviceModel(ds.getValue(AdviceModel1.class).advice4));
                        }

                    }else if (Integer.parseInt(heartBeat.substring(0,3)) >=103 && Integer.parseInt(heartBeat.substring(0,3)) <=110){
                        if (Integer.parseInt( ds.getValue(AdviceModel1.class).getHeartBeat()) >= 103 && Integer.parseInt( ds.getValue(AdviceModel1.class).getHeartBeat()) <= 110){
                            healthAdvice.add(new adviceModel(ds.getValue(AdviceModel1.class).advice1));
                            healthAdvice.add(new adviceModel(ds.getValue(AdviceModel1.class).advice2));
                            healthAdvice.add(new adviceModel(ds.getValue(AdviceModel1.class).advice3));
                            healthAdvice.add(new adviceModel(ds.getValue(AdviceModel1.class).advice4));
                        }

                    }

                }

                recyclerView.setAdapter(new AboutHealthAdapter(healthAdvice));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




//        adviceModel ad1 = new adviceModel("You need to do some exercise");
//        healthAdvice.add(ad1);
//
//        adviceModel ad2 = new adviceModel("Walk more");
//        healthAdvice.add(ad2);
//
//        adviceModel ad3 = new adviceModel("Complete your 8 Hrs Sleep");
//        healthAdvice.add(ad3);
//
//        adviceModel ad4 = new adviceModel("You need to do some exercise");
//        healthAdvice.add(ad4);
//
//        adviceModel ad5 = new adviceModel("Walk more");
//        healthAdvice.add(ad5);
//
//        adviceModel ad6 = new adviceModel("Complete your 8 Hrs Sleep");
//        healthAdvice.add(ad6);
//
//        recyclerView.setAdapter(new AboutHealthAdapter(healthAdvice));

        Typeface kohoFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/KoHo-Medium.ttf");
        healthTitle.setTypeface(kohoFont);

//        saveBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getContext(), "Report saved!", Toast.LENGTH_SHORT).show();
//            }
//        });

        // Inflate the layout for this fragment
        return view;
    }

}