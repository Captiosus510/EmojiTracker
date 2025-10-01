package com.example.mahd1_emotilog;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mahd1_emotilog.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private ArrayList<EmojiEvent> emojiEvents;
    private long selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // initialize emojiEvents
        emojiEvents = new ArrayList<>();

        BottomNavigationView bottomNav = binding.bottomNav;
        NavigationUI.setupWithNavController(bottomNav, navController);

        // initialize to today
        selectedDate = System.currentTimeMillis();
        // set on click listener for date picker fab
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openDatePicker();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void addEmojiEvent(String emoji){ // used by fragments to add emojis
        emojiEvents.add(new EmojiEvent(emoji));
    }

    public ArrayList<EmojiEvent> getEmojiEvents(){ // used by fragments to get history of emojis
        return emojiEvents;
    }

    private void openDatePicker(){
        // use built-in date picker from material components
        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select a date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();
        // show the picker
        datePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
        datePicker.addOnPositiveButtonClickListener(selection -> {
            selectedDate = selection;
        });
    }

    public boolean isSameDay(long inputDate){
        // compare input date with currently selected date from the date picker.
        // Converts to same time zone as material date picker uses UTC while system uses local times zone
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(inputDate);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(selectedDate - TimeZone.getDefault().getOffset(selectedDate));
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH) &&
                calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);
    }
}