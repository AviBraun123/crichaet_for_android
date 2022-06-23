package com.example.crichaet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

public class AddContact extends AppCompatActivity {

    private ContactDao contactdao;
    private ChatViewModel chatViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        if (Chats.t) {
            ImageView pic = findViewById(R.id.imageView2);
            pic.setImageResource(R.drawable.template);
        } else {
            ImageView pic = findViewById(R.id.imageView2);
            pic.setImageResource(R.drawable.template_2);
        }

        EditText nickname = findViewById(R.id.addNickname);
        EditText server = findViewById(R.id.addServer);
        EditText username = findViewById(R.id.addUsername);
        Button AddToChats = findViewById(R.id.AddBackToChats);
        Intent intent = getIntent();
        String str = intent.getStringExtra("message_key");
        MutableLiveData<String> token = new MutableLiveData<>();
        token.setValue(intent.getStringExtra("token"));
        this.chatViewModel = new ChatViewModel(token);
        AddToChats.setOnClickListener(v -> {
            finish();
        });

        AppDB db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "Daba")
                .allowMainThreadQueries().build();
        contactdao = db.contactDao();

        Button AddConToChats = findViewById(R.id.AddConToChats);
        AddConToChats.setOnClickListener(v -> {
            Contact con = new Contact(0, username.getText().toString(), nickname.getText().toString(),
                    server.getText().toString(), null, 0, null, str);
            contactdao.insert(con);
            if(server.getText().toString().equals("localhost:6132")) { //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                this.chatViewModel.CreateContact(username.getText().toString(), nickname.getText().toString(), server.getText().toString());
            } else {
                this.chatViewModel.invite(str, username.getText().toString(), server.getText().toString());
            }
            finish();
        });
    }
}