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

public class ProfileFragment extends Fragment {

    private TextView uName, uMail;
    private EditText uGender, uHeight, uWeight, uBirthday;
    private Button bCancel, bSave;
    private ImageView editImg;

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
            }
        });



        return view;
    }
}
