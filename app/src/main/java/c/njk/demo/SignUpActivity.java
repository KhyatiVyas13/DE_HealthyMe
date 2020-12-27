package c.njk.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SignUpActivity extends AppCompatActivity {


    private EditText name, email, phone, password;
    private Button signUpButton;
    private ImageView back;

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

        getSupportActionBar().hide();
        //for signUp button
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this , LoginActivity.class);
                startActivity(intent);
            }
        });

        //for back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this , LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}