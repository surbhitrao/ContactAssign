package com.example.surbhit.searchview.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.surbhit.searchview.R;
import com.squareup.picasso.Picasso;

public class ContactDetail extends AppCompatActivity {

    ImageView imageViewUserImage;
    TextView textViewShowName;
    TextView textViewPhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textViewShowName = (TextView) findViewById(R.id.name);
        textViewPhoneNumber = (TextView) findViewById(R.id.no);
        imageViewUserImage = (ImageView) findViewById(R.id.pic);



        String Cname= getIntent().getStringExtra("name");
        String Cphone= getIntent().getStringExtra("phone");
        String Cimage= getIntent().getStringExtra("image");







        textViewShowName.setText(Cname);
        textViewPhoneNumber.setText(Cphone);


        if (Cimage.equals("null")) {
            Picasso.with(this).load(R.drawable.avatar).into(imageViewUserImage);
        }else {
            Picasso.with(this).load(Cimage).into(imageViewUserImage);
        }

    }

}
