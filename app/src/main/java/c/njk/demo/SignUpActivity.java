package c.njk.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private TextView msg,gotoLogin;
    private EditText name, email, phone, password;
    private Button signUpButton;
    private ImageView back;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.signUpName);
        email = findViewById(R.id.signUpEmail);
        phone = findViewById(R.id.signUpPhone);
        password = findViewById(R.id.signUpPassword);
        signUpButton = findViewById(R.id.signUpButton);
        back = findViewById(R.id.back);
        msg = findViewById(R.id.msg);
        gotoLogin = findViewById(R.id.gotoLogin);

        //for Customised font
        Typeface kohoFont = Typeface.createFromAsset(getAssets(),"fonts/KoHo-Medium.ttf");
        msg.setTypeface(kohoFont);

        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();
        //for signUp button
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
        //for back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this , LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //for go to login
        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
            }
        });

    }
    private void registerUser() {
        String inputname = name.getText().toString().trim();
        String inputemail = email.getText().toString().trim();
        String inputphon = phone.getText().toString().trim();
        String inputpassword =password.getText().toString().trim();


        if (inputname.isEmpty()) {
            name.setError("Name Field is Required");
            name.requestFocus();
            return;
        }
        else if (inputemail.isEmpty()) {
            email.setError("Email is Required");
            email.requestFocus();
            return;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(inputemail).matches()) {
            email.setError("Please enter a valid email");
            email.requestFocus();
            return;
        }

        else if (inputpassword.isEmpty()) {
            password.setError("Password is Required");
            password.requestFocus();
            return;
        }
        else if (inputpassword.length() < 6) {
            password.setError("Minimum length of password should be 6");
            password.requestFocus();
            return;
        }
        else {
            progressBar.setVisibility(View.VISIBLE);

            mAuth.createUserWithEmailAndPassword(inputemail, inputpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    DocumentReference documentReference =FirebaseFirestore.getInstance().collection("users").document(mAuth.getCurrentUser().getUid());
                    Map<String, Object> user = new HashMap<>();
                    user.put("fEmail", email.getText().toString());
                    user.put("fName", name.getText().toString());
                    user.put("fGender", "Female");
                    user.put("fHeight", "5.6");
                    user.put("fBirthDay", "10");

                    documentReference.set(user);

                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        onBackPressed();
                    } else {
                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }
    }
}