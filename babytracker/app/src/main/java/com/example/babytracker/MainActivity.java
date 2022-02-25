package com.example.babytracker;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.babytracker.ui.main.SectionsPagerAdapter;
import com.example.babytracker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    RecyclerView mRecyclerView;
    TrackerAdapter mAdapter;
    SQLiteOpenHelper dBH;
    Button addButton;
    Integer mStackLevel = 0;
    private static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                //openAddItemDialog();
                openAlert();
            }
        });
    }

    private void openAddItemDialog(String choice) {
        FragmentManager fm = getSupportFragmentManager();
        AddItemFragment addItemFragment = new AddItemFragment(this, mAdapter, choice);
        addItemFragment.show(fm, "dialog_item");
    }

    public void openUpdateItemDialog(Integer _id) {
        FragmentManager fm = getSupportFragmentManager();
        UpdateItemFragment updateItemFragment = new UpdateItemFragment(this, mAdapter, _id);
        updateItemFragment.show(fm, "dialog_item");
    }

    private void openAlert(){
        androidx.appcompat.app.AlertDialog.Builder dialogBox = new androidx.appcompat.app.AlertDialog.Builder(this);
        //dialogBox.setMessage("What are you logging?");
        dialogBox.setTitle("What are you logging?");
        String[] categories = {"Sleep", "Food", "Diaper"};
        int checkedItem = 1;
        dialogBox.setSingleChoiceItems(categories, checkedItem, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which) {
                    case 0: Toast.makeText(MainActivity.this,
                            "Clicked on sleep",
                            Toast.LENGTH_LONG).show();
                            openAddItemDialog("Sleep");
                        break;
                    case 1: Toast.makeText(MainActivity.this,
                            "Clicked on food",
                            Toast.LENGTH_LONG).show();
                        openAddItemDialog("Food");
                        break;
                    case 2: Toast.makeText(MainActivity.this,
                            "Clicked on diaper",
                            Toast.LENGTH_LONG).show();
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