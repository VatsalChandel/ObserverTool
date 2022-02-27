package com.example.figmatoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//edit
    String username;
    String password;

    String myUsername = "";
    String myPassword = "";


    EditText userIn;
    EditText passIn;

    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userIn = (EditText) findViewById(R.id.userIn);
        passIn = (EditText) findViewById(R.id.passIn);

        login = (Button) findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = userIn.getText().toString();
                password = passIn.getText().toString();

                if(username.equals(myUsername) && password.equals(myPassword)){
                    openMainPage();

                }else{
                    Toast.makeText(MainActivity.this, "Wrong Username/Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void openMainPage(){
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }
}