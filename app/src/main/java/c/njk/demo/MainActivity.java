package c.njk.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static int SPLASH_SCREEN = 4000;
    private TextView textView;
    private ImageView vector;

    public static final String mypreference = "mypref";
    SharedPreferences sharedpreferences;
    public static final String Name = "loginKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //NoActionbar
        getSupportActionBar().hide();

        //Initialization
        textView = findViewById(R.id.title);
        vector = findViewById(R.id.vectorImg);

        //for Customised font
        Typeface kohoFont = Typeface.createFromAsset(getAssets(),"fonts/KoHo-Medium.ttf");
        textView.setTypeface(kohoFont);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);



        //Splash_Screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

//            Intent intent;

            if (!sharedpreferences.getBoolean(Name,false)){
                Intent intent = new Intent(MainActivity.this , LoginActivity.class);
                startActivity(intent);
                finish();
            }else{
                Intent intent = new Intent(MainActivity.this , HomeActivity.class);
                startActivity(intent);
                finish();
            }

//            startActivity(intent);
//            finish();

            }
        } , SPLASH_SCREEN);
    }
}
