package com.example.babytracker;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.babytracker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    RecyclerView mRecyclerView;
    SleepTrackerAdapter sAdapter;
    FoodTrackerAdapter fAdapter;
    DiaperTrackerAdapter dAdapter;
    SQLiteOpenHelper dBH;
    Integer mStackLevel = 0;
    private static MainActivity instance;

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
                openAlert();
            }
        });
    }

    private void openAddItemDialog(String choice) {
        FragmentManager fm = getSupportFragmentManager();
        switch(choice){
            case "Sleep":
                sAdapter = SleepFragment.newInstance().giveAdapter();
                AddSleepFragment addSleepFragment = new AddSleepFragment(this, sAdapter);
                addSleepFragment.show(fm, "dialog_sleep");
                break;
            case "Food":
                fAdapter = FoodFragment.newInstance().giveAdapter();
                AddFoodFragment addFoodFragment = new AddFoodFragment(this, fAdapter);
                addFoodFragment.show(fm, "dialog_food");
                break;
            case "Diaper":
                dAdapter = DiaperFragment.newInstance().giveAdapter();
                AddDiaperFragment addDiaperFragment = new AddDiaperFragment(this, dAdapter);
                addDiaperFragment.show(fm, "dialog_diaper");
                break;
        }
    }

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

    private void openAlert(){
        androidx.appcompat.app.AlertDialog.Builder dialogBox = new androidx.appcompat.app.AlertDialog.Builder(this);
        dialogBox.setTitle("What are you logging?");
        String[] categories = {"Sleep", "Food", "Diaper"};
        int checkedItem = 1;
        dialogBox.setSingleChoiceItems(categories, checkedItem, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which) {
                    case 0:
                        openAddItemDialog("Sleep");
                        break;
                    case 1:
                        openAddItemDialog("Food");
                        break;
                    case 2:
                        openAddItemDialog("Diaper");
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