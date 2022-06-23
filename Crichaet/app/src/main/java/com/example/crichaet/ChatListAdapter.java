package com.example.crichaet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ChatListAdapter extends ArrayAdapter<Message> {
    private String myUser;

    // invoke the suitable constructor of the ArrayAdapter class
    public ChatListAdapter(@NonNull Context context, List<Message> arrayList, String myUser) {
        super(context, 0, arrayList);
        this.myUser = myUser;
        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // if the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            if (getItem(position).getUser1().equals(myUser) && getItem(position).getSent()) {
                currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.sentmess, parent, false);
            } else if (getItem(position).getUser1().equals(myUser) && !getItem(position).getSent()) {
                currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.receivedmess, parent, false);
            } else if (getItem(position).getUser2().equals(myUser) && getItem(position).getSent()) {
                currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.receivedmess, parent, false);
            } else if (getItem(position).getUser2().equals(myUser) && !getItem(position).getSent()) {
                currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.sentmess, parent, false);
            }
        }

        // get the position of the view from the ArrayAdapter
        Message currentNumberPosition = getItem(position);

        // then according to the position of the view assign the desired image for the same
        assert currentNumberPosition != null;

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView textView1 = currentItemView.findViewById(R.id.messContent);
        textView1.setText(currentNumberPosition.getContent());

        // then according to the position of the view assign the desired TextView 2 for the same
        TextView textView2 = currentItemView.findViewById(R.id.messTime);
        textView2.setText(currentNumberPosition.getCreated().substring(11, 16));

        // then return the recyclable view
        return currentItemView;
    }
}

//    @Override
//    public View getView(int position, View convertView, ViewGroup container) {
//        if (convertView == null) {
//            convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
//        }
//
//        ((TextView) convertView.findViewById(android.R.id.text1))
//                .setText(getItem(position));
//        return convertView;
//    }

//    class ChatViewHolder extends RecyclerView.ViewHolder {
//        private final TextView tvAuthor;
//        private final TextView tvContent;
//
//        private ChatViewHolder(View itemView) {
//            super(itemView);
//            tvAuthor = itemView.findViewById(R.id.tvAuthor);
//            tvContent = itemView.findViewById(R.id.tvContent);
//        }
//    }
//
//    private final LayoutInflater mInflater;
//    private List<Message> messages;
//
//    public ChatListAdapter(Context context){mInflater=LayoutInflater.from(context);}
//
//    @Override
//    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
//        View itemView = mInflater.inflate(R.layout.message_item,parent,false);
//        return new ChatViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(ChatViewHolder holder,int position) {
//        if (messages != null) {
//            final Message current = messages.get(position);
//            holder.tvAuthor.setText(current.getAuthor());
//            holder.tvContent.setText(current.getContent());
//        }
//    }



