package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class UpdateMovie extends AppCompatActivity {

    String getRatings;
    String getDirector;
    String getId;
    String getReviews;
    String getActors;
    String getName;
    String getFavStatus;
    int getYear;
    int ratingScore = 0;
    int x;
    TextView textView;
    EditText editDirector;
    EditText editYear;
    EditText editActor;
    EditText editRatings;
    EditText editReviews;
    Button edit1;
    Button edit2;
    Button edit3;
    Button edit4;
    Button edit5;
    Button favourites;
    DataBaseHelper dataBaseHelper;

    MovieModel addFavourites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_movie);

        RatingBar simpleRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        textView = (TextView) findViewById(R.id.textView10);
        editDirector = (EditText) findViewById(R.id.editTextTextPersonName8);
        editYear = (EditText) findViewById(R.id.editTextTextPersonName9);
        editActor = (EditText) findViewById(R.id.editTextTextPersonName10);
        editRatings = (EditText) findViewById(R.id.editTextTextPersonName11);
        editReviews = (EditText) findViewById(R.id.editTextTextPersonName13);
        edit1 = (Button) findViewById(R.id.button10);
        edit2 = (Button) findViewById(R.id.button12);
        edit3 = (Button) findViewById(R.id.button13);
        edit4 = (Button) findViewById(R.id.button14);
        edit5 = (Button) findViewById(R.id.button15);
        favourites = (Button) findViewById(R.id.button11);

        ArrayList<String> receivedData = (ArrayList<String>) getIntent().getSerializableExtra("ReceiveData");

        for (int i = 0; i < receivedData.size(); i++) {

            getId = receivedData.get(0);
            getName = receivedData.get(1);
            getYear = Integer.parseInt(receivedData.get(2));
            getDirector = receivedData.get(3);
            getActors = receivedData.get(4);
            getRatings = receivedData.get(5);
            getReviews = receivedData.get(6);
            getFavStatus = receivedData.get(7);
        }

        textView.setText(getName);
        editYear.setText(String.valueOf(getYear));
        editActor.setText(getActors);
        editRatings.setText(getRatings);
        editReviews.setText(getReviews);
        editDirector.setText(getDirector);

        editYear.setEnabled(false);
        editActor.setEnabled(false);
        editRatings.setEnabled(false);
        editReviews.setEnabled(false);
        editDirector.setEnabled(false);

        editRatings.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    x = Integer.parseInt(editRatings.getText().toString());
                }
                catch (NumberFormatException e) {
                    simpleRatingBar.setRating((float) 0.0);
                }
                simpleRatingBar.setRating(x);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        int n = Integer.parseInt(getRatings);
        simpleRatingBar.setRating(n);
        simpleRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingScore = (int) ratingBar.getRating();
            }
        });

        //create database object
        this.dataBaseHelper = new DataBaseHelper(this);

        final int[] buttonState = {0};
        final int[] updateState = {0};
        final int[] buttonState1 = {0};
        final int[] updateState1 = {0};
        final int[] buttonState2 = {0};
        final int[] updateState2 = {0};
        final int[] buttonState3 = {0};
        final int[] updateState3 = {0};
        final int[] buttonState4 = {0};
        final int[] updateState4 = {0};

        edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (buttonState[0] == 0) {
                    editDirector.setEnabled(true);
                    edit1.setText(R.string.update);
                    buttonState[0] = buttonState[0] + 1;
                }
                updateState[0] = updateState[0] + 1;
                if (updateState[0] == 2) {
                    try {
                        String n = editDirector.getText().toString();
                        Log.d("n", n);
                        boolean isUpdated = UpdateMovie.this.dataBaseHelper.update(getId, editDirector.getText().toString(), Integer.parseInt(editYear.getText().toString()), editActor.getText().toString(),
                                Integer.parseInt(editRatings.getText().toString()), editReviews.getText().toString());
                        editDirector.setEnabled(false);
                        edit1.setText(R.string.edit);
                        buttonState[0] = 0;
                        updateState[0] = 0;
                        if (isUpdated) {
                            Log.d("Updated", "OK");
                        }

                    } catch (NumberFormatException e) {
                        buttonState[0] = 0;
                        updateState[0] = 0;
                    }
                }
            }
        });

        edit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (buttonState1[0] == 0) {
                    editYear.setEnabled(true);
                    edit2.setText(R.string.update);
                    buttonState1[0] = buttonState1[0] + 1;
                }
                updateState1[0] = updateState1[0] + 1;
                if (updateState1[0] == 2) {
                    try {
                        boolean isUpdated = UpdateMovie.this.dataBaseHelper.update(getId, editDirector.getText().toString(), Integer.parseInt(editYear.getText().toString()), editActor.getText().toString(),
                                Integer.parseInt(editRatings.getText().toString()), editReviews.getText().toString());
                        editYear.setEnabled(false);
                        edit2.setText(R.string.edit);
                        buttonState1[0] = 0;
                        updateState1[0] = 0;
                        if (isUpdated) {
                            Log.d("Updated", "OK");
                        }
                    }
                    catch (NumberFormatException e) {
                        Log.d("Updated", "No");
                        buttonState1[0] = 0;
                        updateState1[0] = 0;
                    }
                }
            }
        });

        edit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (buttonState2[0] == 0) {
                    editActor.setEnabled(true);
                    edit3.setText(R.string.update);
                    buttonState2[0] = buttonState2[0] + 1;
                }
                updateState2[0] = updateState2[0] + 1;
                try {
                    if (updateState2[0] == 2) {
                        boolean isUpdated = UpdateMovie.this.dataBaseHelper.update(getId, editDirector.getText().toString(), Integer.parseInt(editYear.getText().toString()), editActor.getText().toString(),
                                Integer.parseInt(editRatings.getText().toString()), editReviews.getText().toString());
                        editActor.setEnabled(false);
                        edit3.setText(R.string.edit);
                        buttonState2[0] = 0;
                        updateState2[0] = 0;
                        if (isUpdated) {
                            Log.d("Updated", "OK");
                        }

                    }
                }
                catch(NumberFormatException e){
                    buttonState2[0] = 0;
                    updateState2[0] = 0;
                }
            }
        });

        edit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (buttonState3[0] == 0) {
                    editRatings.setEnabled(true);
                    edit4.setText(R.string.update);
                    buttonState3[0] = buttonState3[0] + 1;
                }
                updateState3[0] = updateState3[0] + 1;
                if (updateState3[0] == 2) {
                    try {
                        boolean isUpdated = UpdateMovie.this.dataBaseHelper.update(getId, editDirector.getText().toString(), Integer.parseInt(editYear.getText().toString()), editActor.getText().toString(),
                                Integer.parseInt(editRatings.getText().toString()), editReviews.getText().toString());
                        editRatings.setEnabled(false);
                        edit4.setText(R.string.edit);
                        buttonState3[0] = 0;
                        updateState3[0] = 0;
                        if (isUpdated) {
                            Log.d("Updated", "OK");
                        }
                    }
                    catch (NumberFormatException e) {
                        buttonState3[0] = 0;
                        updateState3[0] = 0;
                    }
                }
            }
        });

        edit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (buttonState4[0] == 0) {
                    editReviews.setEnabled(true);
                    edit5.setText(R.string.update);
                    buttonState4[0] = buttonState4[0] + 1;
                }
                updateState4[0] = updateState4[0] + 1;
                if (updateState4[0] == 2) {
                    try {
                        boolean isUpdated = UpdateMovie.this.dataBaseHelper.update(getId, editDirector.getText().toString(), Integer.parseInt(editYear.getText().toString()), editActor.getText().toString(),
                                Integer.parseInt(editRatings.getText().toString()), editReviews.getText().toString());
                        editReviews.setEnabled(false);
                        edit5.setText(R.string.edit);
                        buttonState4[0] = 0;
                        updateState4[0] = 0;
                        if (isUpdated) {
                            Log.d("Updated", "OK");
                        }
                    }
                    catch (NumberFormatException e) {
                        buttonState4[0] = 0;
                        updateState4[0] = 0;
                    }
                }
            }
        });
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        if (getFavStatus.equalsIgnoreCase("Not in Favourites")) {
            favourites.setText(R.string.addToFavourites);
            favourites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addFavourites = new MovieModel(-1, null, 0, null, null, 0, null, getName);
                    boolean addSuccessFully = dataBaseHelper.addMovieToTheDataBase(addFavourites);
                    favourites.setText(R.string.addedToFavourites);
                    favourites.setEnabled(false);
                }
            });
        }

        else {
            favourites.setText(R.string.Favourites);
            favourites.setEnabled(false);
        }

    }
}