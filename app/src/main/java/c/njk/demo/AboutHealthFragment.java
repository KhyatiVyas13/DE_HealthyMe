package c.njk.demo;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class AboutHealthFragment extends Fragment {

    private TextView healthTitle;
    private ArrayList<adviceModel> healthAdvice;
    private Button saveBtn;

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
        saveBtn = view.findViewById(R.id.saveReportButton);
        RecyclerView recyclerView = view.findViewById(R.id.healthRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        healthAdvice = new ArrayList<>();

        adviceModel ad1 = new adviceModel("You need to do some exercise");
        healthAdvice.add(ad1);

        adviceModel ad2 = new adviceModel("Walk more");
        healthAdvice.add(ad2);

        adviceModel ad3 = new adviceModel("Complete your 8 Hrs Sleep");
        healthAdvice.add(ad3);

        adviceModel ad4 = new adviceModel("You need to do some exercise");
        healthAdvice.add(ad4);

        adviceModel ad5 = new adviceModel("Walk more");
        healthAdvice.add(ad5);

        adviceModel ad6 = new adviceModel("Complete your 8 Hrs Sleep");
        healthAdvice.add(ad6);

        recyclerView.setAdapter(new AboutHealthAdapter(healthAdvice));

        Typeface kohoFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/KoHo-Medium.ttf");
        healthTitle.setTypeface(kohoFont);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Report saved!", Toast.LENGTH_SHORT).show();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}