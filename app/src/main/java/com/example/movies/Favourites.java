package com.example.movies;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Favourites extends AppCompatActivity{

    TextView textBoxes;
    private CheckBox checkBox;
    int checked = 0;
    ScrollView scrollView;
    LinearLayout linear;
    String getName;
    Button addToFavourite;
    List<CheckBox> checkBoxList = new LinkedList<>(); //To store check boxes
    List <MovieModel> allSelect;
    ArrayList <String> favouritesList = new ArrayList<>();
    DataBaseHelper dataBaseHelper;

    ArrayList<String> sortedList = new ArrayList();

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        addToFavourite = (Button) findViewById(R.id.button9);

        //Get data from data base
        dataBaseHelper = new DataBaseHelper(this);
        allSelect = dataBaseHelper.getAllMovies();

        scrollView = (ScrollView) findViewById(R.id.sv);
        linear = (LinearLayout) findViewById(R.id.ll);

        //remove duplicates values from ArrayList
        HashSet<String> hashSet = new HashSet<String>();
        //Add data to sortedList
        for (int i = 0; i < allSelect.size(); i++) {
            int r = 0;
            getName = String.valueOf(allSelect.get(i).getFavourites());
            if (!getName.equals("null")) {
                favouritesList.add(getName);
                sortedList.add(getName);
                hashSet.addAll(sortedList);
                sortedList.clear();
                sortedList.addAll(hashSet);
            }
        }


        Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER);
        for (int x = 0; x< sortedList.size(); x++) {

            textBoxes = new TextView(this);
            textBoxes.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            textBoxes.setTextColor(Color.parseColor("#FF494575"));
            textBoxes.setPadding(50, 80, 0, 0);
            linear.addView(textBoxes);

            //Create Check boxes

            checkBox = new CheckBox(getApplicationContext());
            checkBox.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            checkBox.setChecked(true);
            checkBox.setText(sortedList.get(x));
            checkBox.setId(x);
            checkBox.setBackgroundColor(Color.parseColor("#F0750F"));
            checkBoxList.add(checkBox);
            checkBox.setTextColor(Color.WHITE);
            checkBox.setTextSize(17);
            checkBox.setPadding(50, 0, 0, 0);
            linear.setPadding(20, 0, 20, 0 );
            linear.addView(checkBox);
        }
        addToFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(CheckBox checkBox: checkBoxList){
                    if(!checkBox.isChecked()) {
                        dataBaseHelper.deleteMovie(checkBox.getText().toString(), null);
                        checkBox.setEnabled(false);
                        checkBox.setBackgroundColor(Color.BLACK);
                    }
                }
            }
        });


    }

}