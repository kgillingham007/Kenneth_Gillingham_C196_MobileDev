package com.example.kenneth_gillingham_c196_mobiledev.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kenneth_gillingham_c196_mobiledev.R;

public class MainActivity extends AppCompatActivity {

    public static int numberAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.mainActivityButton);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, TermList.class);
            startActivity(intent);
        });
    }


}