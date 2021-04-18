package c.njk.demo;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileFragment extends Fragment {

    private TextView uName, uMail;
    private EditText uGender, uHeight, uWeight, uBirthday;
    private Button bCancel, bSave;
    private ImageView editImg;
    FirebaseFirestore fStore;
    FirebaseUser user;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        uName = view.findViewById(R.id.userName);
        uMail = view.findViewById(R.id.userEmail);
        uGender = view.findViewById(R.id.userGender);
        uHeight = view.findViewById(R.id.userHeight);
        uWeight = view.findViewById(R.id.userWeight);
        uBirthday = view.findViewById(R.id.userBirthday);
        bCancel = view.findViewById(R.id.cancelButton);
        bSave = view.findViewById(R.id.saveButton);
        editImg = view.findViewById(R.id.editInfoButton);

        fStore = FirebaseFirestore.getInstance();

        Task<QuerySnapshot> queryDocumentSnapshots = fStore.collection("users").get();

        queryDocumentSnapshots.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                List<DocumentSnapshot> myListOfDocuments = task.getResult().getDocuments();

                for (int i = 0; i< myListOfDocuments.size(); i++){
                    if ((myListOfDocuments.get(i).getString("fEmail").equals(user.getEmail()))){
                        uName.setText(myListOfDocuments.get(i).getString("fName"));
                        uGender.setText(myListOfDocuments.get(i).getString("fGender"));
                        uHeight.setText(myListOfDocuments.get(i).getString("fHeight"));
                        uWeight.setText(myListOfDocuments.get(i).getString("fBirthDay"));
                    }
                }
            }
        });
        user = FirebaseAuth.getInstance().getCurrentUser();

        uMail.setText(user.getEmail());

        //disabling editing
        uGender.setFocusable(false);
        uGender.setFocusableInTouchMode(false);
        uGender.setClickable(false);
        uGender.setCursorVisible(false);

        uHeight.setFocusable(false);
        uHeight.setFocusableInTouchMode(false);
        uHeight.setClickable(false);
        uHeight.setCursorVisible(false);

        uWeight.setFocusable(false);
        uWeight.setFocusableInTouchMode(false);
        uWeight.setClickable(false);
        uWeight.setCursorVisible(false);

        uBirthday.setFocusable(false);
        uBirthday.setFocusableInTouchMode(false);
        uBirthday.setClickable(false);
        uBirthday.setCursorVisible(false);

        //hiding buttons
        bCancel.setVisibility(view.GONE);
        bSave.setVisibility(view.GONE);

        editImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();

                //Enabling EditTexts
                uGender.setFocusable(true);
                uGender.setFocusableInTouchMode(true);
                uGender.setClickable(true);
                uGender.setCursorVisible(true);
                uGender.setEnabled(true);

                uHeight.setFocusable(true);
                uHeight.setFocusableInTouchMode(true);
                uHeight.setClickable(true);
                uHeight.setCursorVisible(true);
                uHeight.setEnabled(true);

                uWeight.setFocusable(true);
                uWeight.setFocusableInTouchMode(true);
                uWeight.setClickable(true);
                uWeight.setCursorVisible(true);
                uWeight.setEnabled(true);

                uBirthday.setFocusable(true);
                uBirthday.setFocusableInTouchMode(true);
                uBirthday.setClickable(true);
                uBirthday.setCursorVisible(true);
                uBirthday.setEnabled(true);

                //Enabling Buttons
                bCancel.setVisibility(view.VISIBLE);
                bSave.setVisibility(view.VISIBLE);
            }
        });

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getContext(), "Data Saved", Toast.LENGTH_SHORT).show();

                //disabling editing
                uGender.setFocusable(false);
                uGender.setFocusableInTouchMode(false);
                uGender.setClickable(false);
                uGender.setCursorVisible(false);

                uHeight.setFocusable(false);
                uHeight.setFocusableInTouchMode(false);
                uHeight.setClickable(false);
                uHeight.setCursorVisible(false);

                uWeight.setFocusable(false);
                uWeight.setFocusableInTouchMode(false);
                uWeight.setClickable(false);
                uWeight.setCursorVisible(false);

                uBirthday.setFocusable(false);
                uBirthday.setFocusableInTouchMode(false);
                uBirthday.setClickable(false);
                uBirthday.setCursorVisible(false);

                //hiding buttons
                bCancel.setVisibility(view.GONE);
                bSave.setVisibility(view.GONE);


                DocumentReference documentReference =FirebaseFirestore.getInstance().collection("users").document(user.getUid());
                Map<String, Object> user = new HashMap<>();
                user.put("fEmail", uMail.getText().toString());
                user.put("fName", uName.getText().toString());
                user.put("fGender", uGender.getText().toString());
                user.put("fHeight", uHeight.getText().toString());
                user.put("fBirthDay", uBirthday.getText().toString());

                documentReference.set(user);

            }
        });

        return view;
    }
}
