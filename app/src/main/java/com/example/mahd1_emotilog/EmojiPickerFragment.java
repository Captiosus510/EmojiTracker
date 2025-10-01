package com.example.mahd1_emotilog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mahd1_emotilog.databinding.EmojiPickerFragmentBinding;

public class EmojiPickerFragment extends Fragment {

    private EmojiPickerFragmentBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = EmojiPickerFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView[] emojiList = {binding.happyEmoji, binding.heartEmoji, binding.sillyEmoji,
                binding.angryEmoji, binding.sadEmoji, binding.sleepyEmoji,
                binding.moneyEmoji, binding.smartEmoji, binding.pooEmoji};

        for (TextView emoji : emojiList) {
            emoji.setOnClickListener(v -> {
                MainActivity activity = (MainActivity) getActivity();
                if (activity != null) {
                    activity.addEmojiEvent(emoji.getText().toString());
                }
            });
            }
        }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}