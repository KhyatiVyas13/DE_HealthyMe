package c.njk.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static int SPLASH_SCREEN = 4000;
    private TextView textView;
    private ImageView vector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //NoActionbar
        getSupportActionBar().hide();

        //Initialization
        textView = findViewById(R.id.title);
        vector = findViewById(R.id.vectorImg);

        //Customised font
        Typeface kohoFont = Typeface.createFromAsset(getAssets(),"fonts/KoHo-Medium.ttf");
        textView.setTypeface(kohoFont);

        //Splash_Screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this , LoginActivity.class);
                startActivity(intent);
                finish();
            }
        } , SPLASH_SCREEN);
    }
}
