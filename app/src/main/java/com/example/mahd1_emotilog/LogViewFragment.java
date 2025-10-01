package com.example.mahd1_emotilog;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mahd1_emotilog.databinding.LogViewerBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class LogViewFragment extends Fragment {
    private LogViewerBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = LogViewerBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<EmojiEvent> emojiEvents = ((MainActivity) requireActivity()).getEmojiEvents();
        ListView list = binding.logList;
        LogArrayAdapter adapter = new LogArrayAdapter(requireContext(), emojiEvents);
        list.setAdapter(adapter);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}