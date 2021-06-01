package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class EditMovies extends AppCompatActivity {

    List <Button> buttons = new LinkedList<>();
    List<MovieModel> allSelect;
    TextView textBoxes;
    LinearLayout linearLayout;
    ScrollView scrollView;
    String getName;
    String favourite;
    DataBaseHelper dataBaseHelper;
    int a;

    ArrayList<String> sortedList = new ArrayList();
    ArrayList<String> favouriteList = new ArrayList();
    ArrayList<String> data = new ArrayList();

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movies);

        linearLayout = (LinearLayout) findViewById(R.id.ll3);
        scrollView = (ScrollView) findViewById(R.id.sv3);

        //Get data from data base
        dataBaseHelper = new DataBaseHelper(this);
        allSelect = dataBaseHelper.getAllMovies();

        //Add data to sortedList
        for (int i = 0; i < allSelect.size(); i++) {
            getName = String.valueOf(allSelect.get(i).getMovieName());
            if (!getName.equals("null")) {
                sortedList.add(getName);
            }
        }

        for (int q = 0; q < allSelect.size(); q++) {
            favourite = String.valueOf(allSelect.get(q).getFavourites());
            if (!favourite.equals("null")) {
                favouriteList.add(favourite);
            }
        }

        Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER);
        Button [] btn = new Button[sortedList.size()];
        for (a = 0; a < sortedList.size(); a++) {

            textBoxes = new TextView(this);
            textBoxes.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            textBoxes.setTextColor(Color.WHITE);
            textBoxes.setPadding(50, 12, 0, 12);
            linearLayout.addView(textBoxes);

            btn[a] = new Button(this);
            btn[a].setBackgroundColor(Color.parseColor("#FF9800"));
            btn[a].setText(sortedList.get(a));
            btn[a].setId(a);
            btn[a].setTextColor(Color.WHITE);
            btn[a].setTextSize(15);
            btn[a].setPadding(50, 5, 0, 5);
            linearLayout.setPadding(60, 5 , 60, 5);
            linearLayout.addView(btn[a]);
            buttons.add(btn[a]);
            btn[a].setOnClickListener(handleOnClick(btn[a]));
        }
    }

    View.OnClickListener handleOnClick(final Button button) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), UpdateMovie.class);

                for (int k = 0; k < allSelect.size(); k++){

                    if (button.getText().toString().equalsIgnoreCase(allSelect.get(k).getMovieName())) {

                        String movieName = allSelect.get(k).getMovieName();
                        int id = allSelect.get(k).getId();
                        int year = allSelect.get(k).getMovieYear();
                        String dir = allSelect.get(k).getDirector();
                        String act = allSelect.get(k).getActors();
                        int ratings = allSelect.get(k).getRatings();
                        String reviews = allSelect.get(k).getReviews();

                        String status = "Not in Favourites";
                        for (int m = 0; m < favouriteList.size(); m ++) {
                            if (favouriteList.get(m).equalsIgnoreCase(button.getText().toString())) {
                                status = "In Favourites";
                                break;
                            }
                        }
                        data.clear();
                        data.add(String.valueOf(id));
                        data.add(movieName);
                        data.add(String.valueOf(year));
                        data.add(dir);
                        data.add(act);
                        data.add(String.valueOf(ratings));
                        data.add(reviews);
                        data.add(status);

                        intent.putExtra("ReceiveData", data);

                        startActivity(intent);
                    }
                }
            }
        };
    }
}