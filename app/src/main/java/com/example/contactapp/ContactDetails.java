package com.example.contactapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;

import java.text.Format;
import java.util.Calendar;
import java.util.Locale;

public class ContactDetails extends AppCompatActivity {

    //view
    private TextView nameTv,phoneTv,emailTv,addedTimeTv,updatedTimeTv,noteTv, latitudeTv, longitudeTv;
    private ImageView profileIv;

    private String id;

    //database helper
    private DbHelper dbHelper;

    Uri uriImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        //init db
        dbHelper = new DbHelper(this);

        //get data from intent
        Intent intent = getIntent();
        id = intent.getStringExtra("contactId");

        //init view
        nameTv = findViewById(R.id.nameTv);
        phoneTv = findViewById(R.id.phoneTv);
        emailTv = findViewById(R.id.emailTv);
        addedTimeTv = findViewById(R.id.addedTimeTv);
        updatedTimeTv = findViewById(R.id.updatedTimeTv);
        noteTv = findViewById(R.id.noteTv);
        latitudeTv = findViewById(R.id.latitudeTV);
        longitudeTv = findViewById(R.id.longitudeTv);

        profileIv = findViewById(R.id.profileIv);

        loadDataById();

        profileIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(ContactDetails.this, PhotoDetailActivity.class);

                intent.putExtra("image", uriImage.toString());
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.main_top_menu_map,menu);

        MenuItem menuItem = menu.findItem(R.id.mapIcon);


        MapView mapView = (MapView) menuItem.getActionView();


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        Double lat  = Double.parseDouble(latitudeTv.getText().toString());
        Double lon = Double.parseDouble(longitudeTv.getText().toString());
        String name = nameTv.getText().toString();
        intent.putExtra("lat", lat);
        intent.putExtra("lon", lon);
        intent.putExtra("name", name);

        startActivity(intent);
        return true;
    }

    private void loadDataById() {

        //get data from database
        //query for find data by id
        String selectQuery =  "SELECT * FROM "+Constants.TABLE_NAME + " WHERE " + Constants.C_ID + " =\"" + id + "\"";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                //get data
                String name =  ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_NAME));
                String image = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_IMAGE));
                String phone = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_PHONE));
                String email = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_EMAIL));
                String note = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_NOTE));
                String addTime = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_ADDED_TIME));
                String updateTime = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_UPDATED_TIME));
                String latitude = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_LATITUDE));
                String longitude = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_LONGITUDE));

                //convert time to dd/mm/yy hh:mm:aa format
                Calendar calendar = Calendar.getInstance(Locale.getDefault());

                calendar.setTimeInMillis(Long.parseLong(addTime));
                String timeAdd = ""+ DateFormat.format("dd/MM/yy hh:mm:aa",calendar);

                calendar.setTimeInMillis(Long.parseLong(updateTime));
                String timeUpdate = ""+ DateFormat.format("dd/MM/yy hh:mm:aa",calendar);

                //set data
                nameTv.setText(name);
                phoneTv.setText(phone);
                emailTv.setText(email);
                noteTv.setText(note);
                addedTimeTv.setText(timeAdd);
                updatedTimeTv.setText(timeUpdate);
                latitudeTv.setText(latitude);
                longitudeTv.setText(longitude);

                if (image.equals("null")){
                    profileIv.setImageResource(R.drawable.ic_baseline_person_24);
                }else {
                    uriImage = Uri.parse(image);
                    profileIv.setImageURI(uriImage);
                }

            }while (cursor.moveToNext());
        }

        db.close();

    }


}