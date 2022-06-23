package com.example.crichaet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import java.util.List;

public class Register extends AppCompatActivity {

    private UserDao userdao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        AppDB db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "Daba")
                .allowMainThreadQueries().build();
        userdao = db.userDao();

        EditText username = findViewById(R.id.RegUsername);
        EditText pass = findViewById(R.id.RegPassword);
        EditText vpass = findViewById(R.id.RegVerPassword);
        EditText nick = findViewById(R.id.RegNickname);
        Button Register = findViewById(R.id.Register);
        TextView errsh = findViewById(R.id.errorShow);

        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getToken().observe(this, token -> {
            if(token != null) {
                String str = username.getText().toString();
                Intent in = new Intent(this, Chats.class);
                in.putExtra("message_key", str);
                in.putExtra("token", token);
                startActivity(in);
            }
        });

        Register.setOnClickListener(v -> {
            List<User> all = userdao.index();
            Boolean flag = true;
            for(int i = 0; i < all.size(); i++) {
                if (all.get(i).getId().equals(username.getText().toString())) {
                    errsh.setText("This username is already taken");
                    flag = false;
                }
            }
            if (!pass.getText().toString().equals(vpass.getText().toString())) {
                errsh.setText("Already forgot your password??");
                flag = false;
            }
            if(flag) {
                User user = new User(username.getText().toString(), nick.getText().toString(),
                        pass.getText().toString(), null);
                userdao.insert(user);
                userViewModel.register(username.getText().toString(), nick.getText().toString(), pass.getText().toString(), null, userViewModel.getToken());
            }

        });

        Button RegToLog = findViewById(R.id.RegToLog);
        RegToLog.setOnClickListener(v -> {

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });
    }
}