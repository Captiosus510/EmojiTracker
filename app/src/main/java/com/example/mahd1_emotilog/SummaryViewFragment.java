package com.example.mahd1_emotilog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mahd1_emotilog.databinding.SummaryViewerBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SummaryViewFragment extends Fragment {

    private SummaryViewerBinding binding;
    private ArrayList<EmojiEvent> emojiEvents;
    private ArrayList<EmojiCount> emojiCounts;
    private SummaryArrayAdapter adapter;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = SummaryViewerBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // get the emoji events from the main activity
        emojiEvents = ((MainActivity) requireActivity()).getEmojiEvents();
        // count the number of times each emoji is used on the selected date
        Map<String, Integer> counts = new HashMap<>();
        for (EmojiEvent e : emojiEvents) {
            if (((MainActivity) requireActivity()).isSameDay(e.getTimestamp())) {
                counts.put(e.getEmoji(), counts.getOrDefault(e.getEmoji(), 0) + 1);
            }
        }
        // add the counts to a list of EmojiCount objects and set the adapter
        emojiCounts = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            emojiCounts.add(new EmojiCount(entry.getKey(), entry.getValue()));
        }
        adapter = new SummaryArrayAdapter(requireContext(), emojiCounts);
        binding.frequencyList.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}