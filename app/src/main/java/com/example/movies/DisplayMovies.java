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
import java.util.LinkedList;
import java.util.List;

public class DisplayMovies extends AppCompatActivity implements View.OnClickListener {

    TextView textBoxes;
    private CheckBox checkBox;
    int checked = 0;
    int a;
    ScrollView scroll;
    LinearLayout linear;
    String getName;
    Button favouriteBtn;
    List<CheckBox> checkBoxList = new LinkedList<>();

    ArrayList<String> sortedList = new ArrayList();

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movies);
        favouriteBtn = (Button) findViewById(R.id.button8);
        favouriteBtn.setOnClickListener(this);


        //Get data from the data base
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        List<MovieModel> allMovies = dataBaseHelper.getAllMovies();

        scroll = (ScrollView) findViewById(R.id.sv);
        linear = (LinearLayout) findViewById(R.id.ll);

        //Add data to sortedList
        for (int i = 0; i < allMovies.size(); i++) {
            getName = String.valueOf(allMovies.get(i).getMovieName());
            if (!getName.equals("null")) {
                sortedList.add(getName);
            }
        }

        Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER);
        for (a = 0; a < sortedList.size(); a++) {

            textBoxes = new TextView(this);
            textBoxes.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            textBoxes.setTextColor(Color.BLACK);
            textBoxes.setPadding(50, 80, 0, 0);
            linear.addView(textBoxes);

            checkBox = new CheckBox(getApplicationContext());
            checkBox.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            checkBox.setText(sortedList.get(a));
            checkBox.setBackgroundColor(Color.parseColor("#F0750F"));
            checkBoxList.add(checkBox);
            checkBox.setTextColor(Color.WHITE);
            checkBox.setTextSize(17);
            checkBox.setPadding(50, 0, 0, 0);
            linear.setPadding(20, 0, 20, 0 );
            linear.addView(checkBox);
        }

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        MovieModel addToFavourites;
        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isChecked()) {
                checked = checked + 1;
                addToFavourites = new MovieModel(-1, null, 0, null, null, 0, null, checkBox.getText().toString());
                DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
                boolean addSuccessFully = dataBaseHelper.addMovieToTheDataBase(addToFavourites);
            }
        }
    }
}