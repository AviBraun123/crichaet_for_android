package com.example.crichaet;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Chats extends AppCompatActivity {

    List<String> list;
    private UserDao userdao;
    private MessageDao messagedao;
    private ContactDao contactdao;
    private List<Contact> contacts;
    private String str;
    private ArrayAdapter<Contact> adapter;
    private ChatViewModel chatViewModel;
    private MutableLiveData<List<Contact>> contact;
    private MutableLiveData<List<User>> users;
    private MutableLiveData<List<Message>> messages;
    private MutableLiveData<List<Contact>> lista;
    private MutableLiveData<String> token;
    private ArrayAdapter adaptera;
    static public Boolean t;

    static public String message_key = " ";
    static public String talk_with = " ";
    private List<Message> messagesa;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        t = true;

        AppDB db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "Daba")
                .allowMainThreadQueries().build();
        userdao = db.userDao();
        messagedao = db.messageDao();
        contactdao = db.contactDao();
        contact = new MutableLiveData<>();
        messages = new MutableLiveData<>();
        this.contacts = new ArrayList<>();

        TextView User_name = (TextView)findViewById(R.id.User_name);
        Intent intent = getIntent();
        str = intent.getStringExtra("message_key");
        User_name.setText(str);

        token = new MutableLiveData<>();
        token.setValue(intent.getStringExtra("token"));
        chatViewModel = new ChatViewModel(token);

        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@11111111111111111@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

//        lista = new MutableLiveData<>();
//        lista.observe(this, contactList -> {
//            this.contacts = contactList;
//
//            this.adapter = new ArrayAdapter<>(this,
//                    android.R.layout.simple_list_item_1,
//                    contacts);
//            ListView chatList = findViewById(R.id.chatList);
//            chatList.setAdapter(adapter);
//            chatList.setOnItemClickListener((adapterView, view, i, l) -> {
//                Intent in = new Intent(this, Chat.class);
//                in.putExtra("message_key", str);
//                in.putExtra("talk_with",(contacts.get(i).getIdname()));
//                in.putExtra("token", token.getValue());
//                startActivity(in);
//            });
//            //delete when we are done !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//            chatList.setOnItemLongClickListener((adapterView, view, i, l) -> {
//                Contact c = contacts.remove(i);
//                this.chatViewModel.deleteContact(c.getIdname());
//                //contactdao.delete(c);
//                adapter.notifyDataSetChanged();
//                return true;
//            });
//        });
//
//        chatViewModel.getContacts(lista, token);


        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@222222222222222222222@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        lista = new MutableLiveData<>();
        lista.observe(this, contactList -> {
            this.contacts = contactList;
            ListView chatList = findViewById(R.id.chatList);
            adapter = new ChatsListAdapter(this, contacts);
            chatList.setAdapter(adapter);
            chatList.setOnItemClickListener((adapterView, view, i, l) -> {
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                    findViewById(R.id.fineline).setVisibility(View.VISIBLE);
                    MutableLiveData<Contact> contact = new MutableLiveData<>();
                    ListView messageList = findViewById(R.id.MsgScreen);
                    contact.observe(this, contactWith -> {
                        TextView nameOfCon = findViewById(R.id.contactName);
                        nameOfCon.setText(contactWith.getNickName());
                        MutableLiveData<List<Message>> lista = new MutableLiveData<>();
                        lista.observe(this, MessageLista -> {
                            this.messagesa = MessageLista;
                            if (this.messagesa == null) {
                                this.messagesa = new ArrayList<>();
                            }
                            this.adaptera = new ChatListAdapter(this,
                                    this.messagesa,
                                    contactWith.getUserid());
                            if (this.adaptera != null && messageList != null) {
                                messageList.setAdapter(adaptera);

                                messageList.setOnItemLongClickListener((adapterViewa, viewa, ia, la) -> {
                                    Message delmes = this.messagesa.remove(ia);
//                          messagedao.delete(delmes);
                                    this.chatViewModel.deleteMessage(contactWith.getIdname(), delmes.getId());
                                    adapter.notifyDataSetChanged();
                                    adaptera.notifyDataSetChanged();
                                    return true;
                                });
                            }

                        });

                        this.chatViewModel.getChat(lista, contactWith.getIdname());


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
                                if (!this.messagesa.isEmpty() && !this.messagesa.get(0).getUser1().equals(contactWith.getUserid())) {
                                    user1 = contactWith.getIdname();
                                    user2 = contactWith.getUserid();
                                    sent = false;
                                }
                                LocalDateTime myTime = LocalDateTime.now();
                                String created = myTime.toString().substring(0, 16);
                                Message m = new Message(0, user1, user2, sendBox.getText().toString(), created, sent);
                                if(contactWith.getServer().equals("localhost:6132")) {
                                    this.chatViewModel.CreateMessage(contactWith.getIdname(),content);
                                } else {
                                    this.chatViewModel.transfer(str, contactWith.getIdname(), content);
                                }
                                this.adaptera.add(m);
                                this.adaptera.notifyDataSetChanged();
                                sendBox.setText("");
                                messageList.setSelection(adaptera.getCount()-1);
                                this.adapter.notifyDataSetChanged();

                            }

                        });
                    });
                    this.chatViewModel.getContact(contact, contacts.get(i).getIdname());
                } else {
                    Intent in = new Intent(this, Chat.class);
                    in.putExtra("message_key", str);
                    in.putExtra("talk_with", String.valueOf(contacts.get(i).getId()));
                    in.putExtra("talk", contacts.get(i).getIdname());
                    in.putExtra("token", token.getValue());
                    startActivity(in);
                    if(adapter != null) {
                        adapter.notifyDataSetChanged();
                    }
                }

            });
            //delete when we are done !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            chatList.setOnItemLongClickListener((adapterView, view, i, l) -> {
                Contact c = contacts.remove(i);
                contactdao.delete(c);
                adapter.notifyDataSetChanged();
                return true;
            });
        });

        chatViewModel.getContacts(lista, token);

        FloatingActionButton AddContact = findViewById(R.id.AddContact);
        AddContact.setOnClickListener(v -> {

            Intent i = new Intent(this, AddContact.class);
            i.putExtra("message_key", str);
            i.putExtra("token", token.getValue());
            startActivity(i);
        });

        Button Settings = findViewById(R.id.Settings);
        Settings.setOnClickListener(v -> {

            Intent i = new Intent(this, Settings.class);
            i.putExtra("message_key", str);
            i.putExtra("token", token.getValue());
            startActivity(i);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        contacts.clear();
        contacts.addAll(contactdao.index(str));
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
        if (adaptera != null) {
            adaptera.notifyDataSetChanged();
        }
        if (t) {
            ImageView pic = findViewById(R.id.imageView2);
            pic.setImageResource(R.drawable.template);
        } else {
            ImageView pic = findViewById(R.id.imageView2);
            pic.setImageResource(R.drawable.template_2);
        }
        this.chatViewModel.getContacts(this.lista, this.token);
    }


}












/*

Chats.message_key = str;
                    Chats.talk_with = String.valueOf(contacts.get(i).getId());
                    TextView contactName = findViewById(R.id.contactName);
                    Integer id = Integer.parseInt(talk_with);
                    Contact c = contactdao.get(id);
                    contactName.setText(c.getNickName());
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

                    ListView messageList = findViewById(R.id.MsgScreen);
                    ChatListAdapter adapter2 = new ChatListAdapter(this,
                            messages,
                            c.getUserid());
                    messageList.setAdapter(adapter2);
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
 */