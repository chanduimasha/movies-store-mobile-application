package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText movieName, yearEditText, movieDirector, movieActrs, ratingEditText, reviews;
    Button saveButton;
    int yearValidate;
    int ratingValidate;
    String checkYear = "";
    String checkRating = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        movieName = (EditText) findViewById(R.id.editTextTextPersonName);
        yearEditText = (EditText) findViewById(R.id.editTextTextPersonName3);
        movieDirector = (EditText) findViewById(R.id.editTextTextPersonName4);
        movieActrs = (EditText) findViewById(R.id.editTextTextPersonName5);
        ratingEditText = (EditText) findViewById(R.id.editTextTextPersonName6);
        reviews = (EditText) findViewById(R.id.editTextTextPersonName7);
        saveButton = (Button) findViewById(R.id.button7);
        saveButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) throws NumberFormatException{

        MovieModel movie;

        //Validation parts
        try {
            String year = yearEditText.getText().toString();
            yearValidate = Integer.parseInt(year);
            if (yearValidate > 1895 ) {
                checkYear = "YearOk";

            }
            else {
                yearEditText.setText("");
            }
        }
        catch (NumberFormatException e) {
        }

        try {
            String rating = ratingEditText.getText().toString();
            ratingValidate = Integer.parseInt(rating);
            if (ratingValidate > 1 && ratingValidate < 10) {
                checkRating = "RatingsOk";
            }
            else {
                ratingEditText.setText("");
            }
        }
        catch (NumberFormatException e) {
        }

        //Insert data into database
        if (!movieName.getText().toString().equals("") && !yearEditText.getText().toString().equals("") && !movieDirector.getText().toString().equals("") && !movieActrs.getText().toString().equals("") && !ratingEditText.getText().toString().equals("") && !reviews.getText().toString().equals("")
        && checkYear.equals("YearOk") && checkRating.equals("RatingsOk")) {
            movie = new MovieModel(-1, movieName.getText().toString(), Integer.parseInt(yearEditText.getText().toString()), movieDirector.getText().toString(), movieActrs.getText().toString(), Integer.parseInt(ratingEditText.getText().toString()), reviews.getText().toString(), null);
            Toast.makeText(this, "Register Successfully" , Toast.LENGTH_SHORT).show();
            DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
            boolean addSuccessFully = dataBaseHelper.addMovieToTheDataBase(movie);
            Log.d("Database", String.valueOf(addSuccessFully));
            movieName.setText(""); yearEditText.setText(""); movieDirector.setText(""); movieActrs.setText(""); ratingEditText.setText(""); reviews.setText("");
        }
        else {
            //Shows empty field that user couldn't fill
            if (movieName.getText().toString().equals("")) {
                movieName.setText("");
            }
            if (movieDirector.getText().toString().equals("")) {
                movieDirector.setText("");
            }
            if (movieActrs.getText().toString().equals("")) {
                movieActrs.setText("");
            }
            if (reviews.getText().toString().equals("")) {
                reviews.setText("");
            }
        }
    }
}