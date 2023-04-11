package com.example.comp_assignment1;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* user clicks on this button to start their order **/
        button = findViewById(R.id.startButton);

        /* move to landing activity **/
        button.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, LandingActivity.class)));
    }
}