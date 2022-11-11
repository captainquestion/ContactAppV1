package com.example.contactapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.gms.maps.MapView;

public class PhotoDetailActivity extends AppCompatActivity {


    ImageView imageViewPhoto;
    Uri uri;
    String stringUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);

        imageViewPhoto = findViewById(R.id.imageViewPhoto);

        //uri = Uri.parse(stringUri);

        Bundle extras = getIntent().getExtras();

        uri = Uri.parse(extras.getString("image"));
        //uri= Uri.parse(extras.getString("image"));
        imageViewPhoto.setImageURI(uri);



    }


}