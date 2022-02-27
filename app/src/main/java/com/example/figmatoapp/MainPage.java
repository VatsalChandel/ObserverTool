package com.example.figmatoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPage extends AppCompatActivity {

    Button camera;
    Button notes;
    Button profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        camera = (Button) findViewById(R.id.cameraButton);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCameraPage();

            }
        });

        notes = (Button) findViewById(R.id.noteButton);
        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNotePage();
            }
        });




    }

    public void openCameraPage(){
        Intent intent = new Intent(this, PicturePage.class);
        startActivity(intent);
    }

    public void openNotePage(){
        Intent intent = new Intent(this, NotePage.class);
        startActivity(intent);
    }



}