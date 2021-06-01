package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toRegisterActivity = findViewById(R.id.button);
        Button toDisplayActivity = findViewById(R.id.button2);
        Button toFavourites = findViewById(R.id.button3);
        Button toEditActivity = findViewById(R.id.button4);
        Button toSearchActivity = findViewById(R.id.button5);
        Button toRatingsActivity = findViewById(R.id.button6);

        //Move between activities
        toRegisterActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveToRegisterActivity();
            }
        });

        toDisplayActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveToDisplayActivity();
            }
        });

        toFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveToFavourites();
            }
        });

        toEditActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveTOEditMovies();
            }
        });

        toSearchActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveToSearchActivity();
            }
        });

        toRatingsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveToRatingsActivity();
            }
        });
    }

    //Move between activities
    private void MoveToRegisterActivity() {
        Intent register = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(register);
    }

    private void MoveToDisplayActivity() {
        Intent display = new Intent(getApplicationContext(), DisplayMovies.class);
        startActivity(display);
    }

    private void MoveToFavourites() {
        Intent Favourites = new Intent(getApplicationContext(), Favourites.class);
        startActivity(Favourites);
    }

    private void MoveTOEditMovies() {
        Intent editMovies = new Intent(getApplicationContext(), EditMovies.class);
        startActivity(editMovies);
    }

    private void MoveToSearchActivity() {
        Intent searchActivity = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(searchActivity);
    }

    private void MoveToRatingsActivity() {
        Intent ratingsActivity = new Intent(getApplicationContext(), Ratings.class);
        startActivity(ratingsActivity);
    }

}