package com.example.babytracker;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.babytracker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    SleepTrackerAdapter sAdapter;
    FoodTrackerAdapter fAdapter;
    DiaperTrackerAdapter dAdapter;
    SQLiteOpenHelper dBH;

    private static MainActivity instance;

    //Creates app with three categories, each in its own tab
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = binding.fab;


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChoiceAlert();
            }
        });
    }

    //Opens dialog box for the dialog box based on the category, it will use the update
    // fragment to fill in the information of the item called from the database.
    public void openUpdateItemDialog(Integer _id, String choice) {
        FragmentManager fm = getSupportFragmentManager();
        switch(choice){
            case "Sleep":
                sAdapter = SleepFragment.giveAdapter();
                UpdateSleepFragment updateSleepFragment = new UpdateSleepFragment(this, sAdapter, _id);
                updateSleepFragment.show(fm, "dialog_sleep");
                break;
            case "Food":
                fAdapter = FoodFragment.giveAdapter();
                UpdateFoodFragment updateFoodFragment = new UpdateFoodFragment(this, fAdapter, _id);
                updateFoodFragment.show(fm, "dialog_food");
                break;
            case "Diaper":
                dAdapter = DiaperFragment.giveAdapter();
                UpdateDiaperFragment updateDiaperFragment = new UpdateDiaperFragment(this, dAdapter, _id);
                updateDiaperFragment.show(fm, "dialog_diaper");
                break;
        }
    }

    //Dialog box that pops up when you click the plus to add an item, choose between
    // Sleep, Food and Diaper. The dialog box will close and open another dialog box.
    private void openChoiceAlert(){
        FragmentManager fm = getSupportFragmentManager();
        androidx.appcompat.app.AlertDialog.Builder dialogBox = new androidx.appcompat.app.AlertDialog.Builder(this);
        dialogBox.setTitle("What are you logging?");
        String[] categories = {"Sleep", "Food", "Diaper"};
        int checkedItem = 0;
        dialogBox.setSingleChoiceItems(categories, checkedItem, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which) {
                    case 0:
                        sAdapter = SleepFragment.newInstance().giveAdapter();
                        AddSleepFragment addSleepFragment = new AddSleepFragment(MainActivity.this, sAdapter);
                        addSleepFragment.show(fm, "dialog_sleep");
                        dialog.dismiss();
                        break;
                    case 1:
                        fAdapter = FoodFragment.newInstance().giveAdapter();
                        AddFoodFragment addFoodFragment = new AddFoodFragment(MainActivity.this, fAdapter);
                        addFoodFragment.show(fm, "dialog_food");
                        dialog.dismiss();
                        break;
                    case 2:
                        dAdapter = DiaperFragment.newInstance().giveAdapter();
                        AddDiaperFragment addDiaperFragment = new AddDiaperFragment(MainActivity.this, dAdapter);
                        addDiaperFragment.show(fm, "dialog_diaper");
                        dialog.dismiss();
                        break;
                }
            }
        });
        androidx.appcompat.app.AlertDialog alert = dialogBox.create();
        alert.show();
    }

    public static MainActivity getInstance(){
        return instance;
    }
}