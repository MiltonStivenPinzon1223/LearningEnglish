package com.edu.ue.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.edu.ue.myproject.model.Words;
import com.edu.ue.myproject.model.Users;

public class Done extends AppCompatActivity {

    PracticeFragment practiceFragment = new PracticeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        Button btn = findViewById(R.id.btnDone);
        btn.setOnClickListener(this::returnar);
    }

    private void returnar(View view) {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }
}