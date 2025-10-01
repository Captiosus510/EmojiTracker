package com.example.mahd1_emotilog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class LogArrayAdapter extends ArrayAdapter<EmojiEvent> {
    private final Context context;
    private final ArrayList<EmojiEvent> emojiEvents;
    public LogArrayAdapter(Context context, ArrayList<EmojiEvent> emojiEvents) {
        super(context, 0, emojiEvents);
        this.context = context;
        this.emojiEvents = emojiEvents;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // using built-in layout "simple_list_item_2" to display emoji and timestamp
        if(convertView == null){
            convertView = View.inflate(context, android.R.layout.simple_list_item_2, null);
        }
        // get the emoji and timestamp for the current position and set the textview elements
        EmojiEvent emojiEvent = emojiEvents.get(position);
        TextView emojiText = convertView.findViewById(android.R.id.text1);
        emojiText.setText(emojiEvent.getEmoji());

        TextView countText = convertView.findViewById(android.R.id.text2);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy h:m a", Locale.getDefault());
        String formattedTimestamp = sdf.format(emojiEvent.getTimestamp());
        countText.setText(formattedTimestamp);
        return convertView;
    }
}
