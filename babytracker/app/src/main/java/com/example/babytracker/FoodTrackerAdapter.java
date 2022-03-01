package com.example.babytracker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodTrackerAdapter extends RecyclerView.Adapter<FoodTrackerAdapter.ViewHolder>{

    private List<FoodTracker> myList;
    private int rowLayout;
    private Context mContext;
    private SQLiteOpenHelper dBH;
    private SQLiteDatabase db;
    private Cursor cursor;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategory;
        TextView tvDate;
        TextView tvTime;
        TextView tvOunces;
        TextView tvNotes;
        Button btnRemove;
        Button btnEdit;

        ViewHolder(View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.foodCategory);
            tvDate = itemView.findViewById(R.id.foodDate);
            tvTime = itemView.findViewById(R.id.foodTime);
            tvOunces = itemView.findViewById(R.id.foodOunces);
            tvNotes = itemView.findViewById(R.id.foodNotes);
            btnRemove = itemView.findViewById(R.id.foodBtnRemove);
            btnEdit = itemView.findViewById(R.id.foodBtnEdit);
        }
    }

    public FoodTrackerAdapter(List<FoodTracker> myList, int rowLayout, Context context){
        this.myList = myList;
        this.rowLayout = rowLayout;
        this.mContext = context;
        dBH = new DatabaseHelper(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i){
        FoodTracker entry = myList.get(i);
        viewHolder.tvCategory.setText(entry.getCategory());
        viewHolder.tvDate.setText(entry.getDate());
        viewHolder.tvTime.setText(entry.getTime());
        viewHolder.tvOunces.setText(entry.getOunces().toString());
        viewHolder.tvNotes.setText(entry.getNotes());

        viewHolder.btnRemove.setFocusableInTouchMode(false);
        viewHolder.btnRemove.setFocusable(false);
        viewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodTracker entry = (FoodTracker) v.getTag();
                myList.remove(entry);
                //((DatabaseHelper) dBH).deleteDataById(entry.getId());
                notifyDataSetChanged();
            }
        });

        viewHolder.btnEdit.setFocusableInTouchMode(false);
        viewHolder.btnEdit.setFocusable(false);
        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodTracker entry = (FoodTracker) v.getTag();
                MainActivity.getInstance().openUpdateItemDialog(entry.getId(), "Food");
            }
        });

        viewHolder.btnRemove.setTag(entry);
        viewHolder.btnEdit.setTag(entry);
    }

    @Override
    public int getItemCount(){
        return myList == null ? 0 : myList.size();
    }

    public void refreshList() {
        myList = ((DatabaseHelper) dBH).getAllFoodRecords();
        notifyDataSetChanged();
    }

}
