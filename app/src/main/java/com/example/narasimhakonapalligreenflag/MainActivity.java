package com.example.narasimhakonapalligreenflag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView SignUp;
        SignUp=findViewById(R.id.CreateAnAccount);
        SignUp.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, SignUpPage.class)));
    }
}