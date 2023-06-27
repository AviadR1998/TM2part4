package com.example.tm2ex3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ChatsAdapter extends BaseAdapter {
    private List<Chats> chats;
    private class ViewHolder {
        TextView name;
        TextView lastMessage;
        TextView date;
        ImageView img;
    }

    public ChatsAdapter(List<Chats> newChats) { this.chats = newChats; }

    @Override
    public int getCount() {
        return this.chats.size();
    }

    @Override
    public Object getItem(int position) {
        return this.chats.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private String shortenMessage(String str) {
        return str.length() > 10 ? str.substring(0, 9) + "..." : str;
    }

    private String pasreDate(String date) {
        if (date.equals("")){
            return "";
        }
        return date.substring(11, 16) + " " + date.substring(8, 10) + "/" + date.substring(5, 7) + "/" + date.substring(2, 4);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_item, parent, false);

            ChatsAdapter.ViewHolder viewHolder = new ChatsAdapter.ViewHolder();
            viewHolder.name = convertView.findViewById(R.id.nameXml);
            viewHolder.lastMessage = convertView.findViewById(R.id.lastMessageXml);
            viewHolder.date = convertView.findViewById(R.id.dateXml);
            viewHolder.img = convertView.findViewById(R.id.chatImageXml);

            convertView.setTag(viewHolder);
        }

        Chats c = chats.get(position);
        ChatsAdapter.ViewHolder viewHolder = (ChatsAdapter.ViewHolder) convertView.getTag();
        viewHolder.name.setText(c.getName());
        viewHolder.date.setText(pasreDate(c.getDate()));
        viewHolder.lastMessage.setText(shortenMessage(c.getLastMessage()));
        viewHolder.img.setImageBitmap(c.getProfileImage());


        return convertView;
    }
}
