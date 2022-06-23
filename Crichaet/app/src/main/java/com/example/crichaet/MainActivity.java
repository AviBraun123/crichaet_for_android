package com.example.crichaet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserDao userdao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText username = findViewById(R.id.LogUsername);
        EditText password = findViewById(R.id.LogPassword);

        AppDB db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "Daba")
                .allowMainThreadQueries().build();
        userdao = db.userDao();

        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        MutableLiveData<String> tok = new MutableLiveData<>();
        tok.observe(this, token -> {
            if(token != null) {
                String str = username.getText().toString();
                Intent in = new Intent(this, Chats.class);
                in.putExtra("message_key", str);
                in.putExtra("token", token);
                startActivity(in);
            }
        });
        Button Login = findViewById(R.id.Login);
        MutableLiveData<List<User>> list = new MutableLiveData<>();
        list.observe(this, listUser -> {
            boolean f = true;
            List<User> all = listUser;
            for(int i = 0; i < all.size(); i++){
                if(all.get(i).getId().equals(username.getText().toString())){
                    if(all.get(i).getPassword().equals(password.getText().toString())){
                        userViewModel.login(tok,username.getText().toString());
                        f = false;
                    }
                }
            }
            if(f) {
                TextView err = findViewById(R.id.errorText);
                err.setVisibility(View.VISIBLE);
            }
        });
        Login.setOnClickListener(v -> {
            userViewModel.getUsers(list);
        });

        Button LogToReg = findViewById(R.id.LogToReg);
        LogToReg.setOnClickListener(v -> {

            Intent i = new Intent(this, Register.class);
            startActivity(i);
        });
    }

}