package com.harish.rssfeedapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {
    Button chennai,sports;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        chennai=(Button)findViewById(R.id.chennai);
        sports=(Button)findViewById(R.id.sports);
        chennai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chennaiintent=new Intent(FirstActivity.this,MainActivity.class);
                startActivity(chennaiintent);

            }
        });

        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sportsintent=new Intent(FirstActivity.this,SportsActivity.class);
                startActivity(sportsintent);

            }
        });
    }
}
