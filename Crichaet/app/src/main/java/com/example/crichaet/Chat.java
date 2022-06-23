package com.example.crichaet;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Chat extends AppCompatActivity {

    private MessageDao messagedao;
    private ContactDao contactdao;
    private List<Message> messages;
    private ArrayAdapter adapter;
    private ChatViewModel chatViewModel;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        this.messages = new ArrayList<>();
        if (Chats.t) {
            ImageView pic = findViewById(R.id.imageView2);
            pic.setImageResource(R.drawable.template);
        } else {
            ImageView pic = findViewById(R.id.imageView2);
            pic.setImageResource(R.drawable.template_2);
        }

        AppDB db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "Daba")
                .allowMainThreadQueries().build();
        messagedao = db.messageDao();
        contactdao = db.contactDao();

        TextView contactName = findViewById(R.id.contactName);
        Intent intent = getIntent();
        String str;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            str = Chats.talk_with;
        } else {
            str = intent.getStringExtra("talk");
        }

        String master = intent.getStringExtra("message_key");
        //String str = intent.getStringExtra("talk");
        String tok = intent.getStringExtra("token");
        MutableLiveData<String> token = new MutableLiveData<>();
        token.setValue(tok);
        this.chatViewModel = new ChatViewModel(token);
        //Log.i("Msg", str);
        MutableLiveData<Contact> contact = new MutableLiveData<>();
        ListView messageList = findViewById(R.id.MsgScreen);
        contact.observe(this, contactWith -> {
            contactName.setText(contactWith.getNickName());
//        messages = messagedao.index(c.getUserid(), c.getIdname());
            MutableLiveData<List<Message>> lista = new MutableLiveData<>();
            lista.observe(this, MessageList -> {
                this.messages = MessageList;
                if(this.messages == null) {
                    this.messages = new ArrayList<>();
                }

                adapter = new ChatListAdapter(this,
                        this.messages,
                        contactWith.getUserid());
                if (adapter != null && messageList != null) {
                    messageList.setAdapter(adapter);

                    messageList.setOnItemLongClickListener((adapterView, view, i, l) -> {
                        Message delmes = messages.remove(i);
//                          messagedao.delete(delmes);
                        this.chatViewModel.deleteMessage(str, delmes.getId());
                        adapter.notifyDataSetChanged();
                        return true;
                    });
                }

            });

            this.chatViewModel.getChat(lista, str);

            ImageButton Sender = findViewById(R.id.Sender);
            Sender.setOnClickListener(v -> {
                EditText sendBox = findViewById(R.id.textBox);
                String content = sendBox.getText().toString();
                if (!content.equals("")) {
                    String user1, user2;
                    Boolean sent;
                    user1 = contactWith.getUserid();
                    user2 = contactWith.getIdname();
                    sent = true;
                    if (!messages.isEmpty() && !messages.get(0).getUser1().equals(contactWith.getUserid())) {
                        user1 = contactWith.getIdname();
                        user2 = contactWith.getUserid();
                        sent = false;
                    }
                    LocalDateTime myTime = LocalDateTime.now();
                    String created = myTime.toString().substring(0, 16);
                    Message m = new Message(0, user1, user2, sendBox.getText().toString(), created, sent);
                    if(contactWith.getServer().equals("localhost:6132")) {
                        this.chatViewModel.CreateMessage(str, content);
                    } else {
                        this.chatViewModel.transfer(master, str, content);
                    }
                    adapter.add(m);
                    adapter.notifyDataSetChanged();
                    sendBox.setText("");
                    messageList.setSelection(adapter.getCount()-1);
                }

            });

        });

        this.chatViewModel.getContact(contact, str);

        Button ChatToChats = findViewById(R.id.ChatToChats);
        ChatToChats.setOnClickListener(v -> {
            finish();
        });



    }

    @Override
    public void onConfigurationChanged(@NotNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        messages.clear();
        Intent intent = getIntent();
        String str = intent.getStringExtra("talk_with");
        MutableLiveData<List<Message>> list = new MutableLiveData<>();
        list.observe(this, mes -> {
            List<Message> mess = mes;
            if (mess == null) {
                mess = new ArrayList<>();
            }
            messages.addAll(mess);
        });
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
        this.chatViewModel.getChat(list, str);
    }
}