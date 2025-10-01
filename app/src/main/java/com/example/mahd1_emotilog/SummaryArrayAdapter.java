package com.example.mahd1_emotilog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class SummaryArrayAdapter extends ArrayAdapter<EmojiCount> {
    private Context context;
    private ArrayList<EmojiCount> emojiCounts;

    public SummaryArrayAdapter(Context context, ArrayList<EmojiCount> emojiCounts) {
        super(context, 0, emojiCounts);
        this.context = context;
        this.emojiCounts = emojiCounts;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // use custom layout in each list item
        if(convertView == null){
            convertView = View.inflate(context, R.layout.frequency_content, null);
        }
        // get the emoji and count for the current position and set the textview elements
        EmojiCount emojiCount = emojiCounts.get(position);
        TextView emojiText = convertView.findViewById(R.id.emoji_text);
        emojiText.setText(emojiCount.getEmoji());

        TextView countText = convertView.findViewById(R.id.count_text);
        countText.setText(String.valueOf(emojiCount.getCount()));
        return convertView;
    }


}
