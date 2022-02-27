package com.example.figmatoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class NotePage extends AppCompatActivity {

    Button saveBtn2;
    Button backBtn2;

    EditText animalIn2;
    String animal;

    EditText sexIn2;
    String sex;

    EditText colorIn2;
    String color;

    EditText weightIn2;
    String weight;

    EditText heightIn2;
    String height;

    EditText healthIn2;
    String health;

    EditText otherIn2;
    String other;

    EditText quantityIn2;
    String quantity;

    EditText nicknameIn2;
    String nickname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_page);

        animalIn2 = (EditText) findViewById(R.id.animalIn2);
        sexIn2 = (EditText) findViewById(R.id.sexIn2);
        colorIn2 = (EditText) findViewById(R.id.colorIn2);
        weightIn2 = (EditText) findViewById(R.id.weightIn2);
        heightIn2 = (EditText) findViewById(R.id.heightIn2);
        healthIn2 = (EditText) findViewById(R.id.healthIn2);
        otherIn2 = (EditText) findViewById(R.id.otherIn2);
        quantityIn2 = (EditText) findViewById(R.id.quantityIn2);
        nicknameIn2 = (EditText) findViewById(R.id.nicknameIn2);

        saveBtn2 = (Button) findViewById(R.id.saveBtn2);
        saveBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                animal = animalIn2.getText().toString();
                if(animal.equals("")){
                    animal = "N/a";
                }

                sex = sexIn2.getText().toString();
                if(sex.equals("")){
                    sex = "N/a";
                }

                color = colorIn2.getText().toString();
                if(color.equals("")){
                    color = "N/a";
                }

                weight = weightIn2.getText().toString();
                if(weight.equals("")){
                    weight = "N/a";
                }

                height = heightIn2.getText().toString();
                if(height.equals("")){
                    height = "N/a";
                }

                health = healthIn2.getText().toString();
                if(health.equals("")){
                    health = "N/a";
                }

                other = otherIn2.getText().toString();
                if(other.equals("")) {
                    other = "N/a";
                }

                quantity = quantityIn2.getText().toString();
                if(quantity.equals("")){
                    quantity = "N/a";
                }

                nickname = nicknameIn2.getText().toString();
                if(nickname.equals("")){
                    nickname = "N/a";
                }


                sendMail();


            }
        });

        backBtn2 = (Button) findViewById(R.id.backBtn2);
        backBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(NotePage.this);

                builder.setCancelable(true);
                builder.setTitle("Your Work!");
                builder.setMessage("If you go back now, you will lose your work!");

                builder.setNegativeButton("Noo I'll stay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton("Imma head out", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        openMainPage();
                    }
                });
                builder.show();
            }
        });

    }

    private void sendMail(){
        //String recipients = "bob@gmail.com";
        String subject = nickname +" who is a(n) "+animal;
        String message = "Animal: "+ animal + "\n Sex: "+sex+ "\n Color: "+color+ "\n Weight: "+weight+ "\n Height: "+height+ "\n Other: "+other+ "\n Quantity: "+quantity+ "\n Nickname: "+nickname;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] { "vatsalchandel@gmail.com" });
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);


        intent.setType("text/png");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }

    public void openMainPage(){
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }
}