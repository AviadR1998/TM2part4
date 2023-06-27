package com.example.tm2ex3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MessagesAdapter extends BaseAdapter {
    private List<Messages> messages;
    public MessagesAdapter(List<Messages> newMessages) { this.messages = newMessages; }

    @Override
    public int getCount() {
        return this.messages.size();
    }

    @Override
    public Object getItem(int position) {
        return this.messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (messages.get(position).isMyMessage()) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_item, parent, false);
        } else {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_item_reverse, parent, false);
        }

        ViewHolder viewHolder = new ViewHolder();
        viewHolder.text = convertView.findViewById(R.id.textMessageItem);
        //viewHolder.img = convertView.findViewById(R.id.imgMessageItem);

        convertView.setTag(viewHolder);

        Messages m = messages.get(position);
        ViewHolder viewHolder2 = (ViewHolder) convertView.getTag();
        viewHolder2.text.setText(m.getMessage());
        //viewHolder2.img.setImageBitmap(m.getProfileImage());

        return convertView;
    }

    private class ViewHolder {
        TextView text;
        ImageView img;
    }

}
