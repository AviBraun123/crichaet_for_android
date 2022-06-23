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

public class ChatsListAdapter extends ArrayAdapter<Contact> {

    // invoke the suitable constructor of the ArrayAdapter class
    public ChatsListAdapter(@NonNull Context context, List<Contact> arrayList) {
        super(context, 0, arrayList);
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
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.chatitem, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        Contact currentNumberPosition = getItem(position);

        // then according to the position of the view assign the desired image for the same
        assert currentNumberPosition != null;

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView chatsName = currentItemView.findViewById(R.id.chatsName);
        chatsName.setText(currentNumberPosition.getNickName());

        // then according to the position of the view assign the desired TextView 2 for the same
        TextView chatsLastMsg = currentItemView.findViewById(R.id.chatsLastMsg);
        if (currentNumberPosition.getLast() == null || currentNumberPosition.getLast().length() < 15) {
            chatsLastMsg.setText(currentNumberPosition.getLast());
        } else {
            chatsLastMsg.setText(currentNumberPosition.getLast().substring(0,15) + "...");
        }


        TextView chatsTime = currentItemView.findViewById(R.id.chatsTime);
        if (currentNumberPosition.getLastdate() != null) {
            chatsTime.setText(currentNumberPosition.getLastdate().substring(11, 16) + "   " + currentNumberPosition.getLastdate().substring(0, 10));
        }


        // then return the recyclable view
        return currentItemView;
    }
}
