package com.example.figmatoapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PicturePage extends AppCompatActivity {


    private static final int SELECT_PICTURE = 200;
    Bitmap captureImage;
    String path;
    Uri screenshot;

    ImageView imageView;
    Button btOpen;

    EditText animalIn;
    String animal;

    EditText sexIn;
    String sex;

    EditText colorIn;
    String color;

    EditText weightIn;
    String weight;

    EditText heightIn;
    String height;

    EditText healthIn;
    String health;

    EditText otherIn;
    String other;

    EditText quantityIn;
    String quantity;

    EditText nicknameIn;
    String nickname;

    Button saveBtn;
    Button backBtn;
    Button prevImages;

    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_page);

        email = getIntent().getStringExtra("EMAIL_TO_SEND");

        animalIn = (EditText) findViewById(R.id.animalIn);
        sexIn = (EditText) findViewById(R.id.sexIn);
        colorIn = (EditText) findViewById(R.id.colorIn);
        weightIn = (EditText) findViewById(R.id.weightIn);
        heightIn = (EditText) findViewById(R.id.heightIn);
        healthIn = (EditText) findViewById(R.id.healthIn);
        otherIn = (EditText) findViewById(R.id.otherIn);
        quantityIn = (EditText) findViewById(R.id.quantityIn);
        nicknameIn = (EditText) findViewById(R.id.nicknameIn);

        saveBtn = (Button) findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animal = animalIn.getText().toString();
                if(animal.equals("")){
                    animal = "N/a";
                }

                sex = sexIn.getText().toString();
                if(sex.equals("")){
                    sex = "N/a";
                }

                color = colorIn.getText().toString();
                if(color.equals("")){
                    color = "N/a";
                }

                weight = weightIn.getText().toString();
                if(weight.equals("")){
                    weight = "N/a";
                }

                height = heightIn.getText().toString();
                if(height.equals("")){
                    height = "N/a";
                }

                health = healthIn.getText().toString();
                if(health.equals("")){
                    health = "N/a";
                }

                other = otherIn.getText().toString();
                if(other.equals("")) {
                    other = "N/a";
                }

                quantity = quantityIn.getText().toString();
                if(quantity.equals("")){
                    quantity = "N/a";
                }

                nickname = nicknameIn.getText().toString();
                if(nickname.equals("")){
                    nickname = "N/a";
                }


                sendMail();

            }
        });



        imageView = findViewById(R.id.image_view);
        btOpen = findViewById(R.id.bt_open);
        if (ContextCompat.checkSelfPermission(PicturePage.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PicturePage.this, new String[]{
                            Manifest.permission.CAMERA
                    },
                    100);
        }
        btOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            }
        });


        backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(PicturePage.this);

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


        prevImages = (Button) findViewById(R.id.prevImages);
        prevImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            captureImage = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(captureImage);

            path = MediaStore.Images.Media.insertImage(getContentResolver(), captureImage,"animal",null);
            screenshot = Uri.parse(path);
        }

        if(requestCode == SELECT_PICTURE){
            Uri imageUri = data.getData();
            imageView.setImageURI(imageUri);
            screenshot = imageUri;
        }
    }

    private void sendMail(){
        String subject = nickname +" who is a(n) "+animal;
        String message = "Animal: "+ animal + "\n Sex: "+sex+ "\n Color: "+color+ "\n Weight: "+weight+ "\n Height: "+height+ "\n Other: "+other+ "\n Quantity: "+quantity+ "\n Nickname: "+nickname;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] { email });
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.putExtra(Intent.EXTRA_STREAM, screenshot);


        intent.setType("text/png");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }

    public void openMainPage(){
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }

    public void imageChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }



}
