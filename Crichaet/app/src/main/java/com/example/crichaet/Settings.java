package com.example.crichaet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {

    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (Chats.t) {
            ImageView pic = findViewById(R.id.imageView2);
            pic.setImageResource(R.drawable.template);
            pic = findViewById(R.id.bugs);
            pic.setImageResource(R.mipmap.cricki_foreground);
        } else {
            ImageView pic = findViewById(R.id.imageView2);
            pic.setImageResource(R.drawable.template_2);
            pic = findViewById(R.id.bugs);
            pic.setImageResource(R.drawable.pngegg);
        }

        Intent intent = getIntent();
        String str = intent.getStringExtra("message_key");
        flag = true;
        Button BackToChats = findViewById(R.id.BackToChats);
        BackToChats.setOnClickListener(v -> {
            finish();
        });

        Button toCricket = findViewById(R.id.toCricket);
        toCricket.setOnClickListener(v -> {
            ImageView pic = findViewById(R.id.imageView2);
            pic.setImageResource(R.drawable.template);
            pic = findViewById(R.id.bugs);
            pic.setImageResource(R.mipmap.cricki_foreground);
            flag = true;

        });

        Button toLadybug = findViewById(R.id.toLadybug);
        toLadybug.setOnClickListener(v -> {
            ImageView pic = findViewById(R.id.imageView2);
            pic.setImageResource(R.drawable.template_2);
            pic = findViewById(R.id.bugs);
            pic.setImageResource(R.drawable.pngegg);
            flag = false;
        });

        Button SaveChanges = findViewById(R.id.SaveChanges);
        SaveChanges.setOnClickListener(v -> {
            ImageView pic;
            if(!flag) {
                Chats.t = false;
            } else {
                Chats.t = true;
            }
            finish();
        });
    }
}