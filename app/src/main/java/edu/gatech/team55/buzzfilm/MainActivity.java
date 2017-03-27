package edu.gatech.team55.buzzfilm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Main page seen upon login
 * @author team 55
 */
public class   MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.logoutButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSecondActivity();
            }
        });

        Button editButton = (Button) findViewById(R.id.edit_profile_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEditProfile = new Intent(MainActivity.this, UserProfile.class);
                startActivity(intentEditProfile);
            }
        });

        //search button
        Button searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSearch = new Intent(MainActivity.this, SearchMovies.class);
                startActivity(intentSearch);
            }
        });

        //get movie recommendations
        Button topButton = (Button) findViewById(R.id.get_movie_rec_button);
        topButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMovieRecs = new Intent(MainActivity.this,
                        GetMovieRecsActivity.class);
                startActivity(intentMovieRecs);
            }
        });
    }

    /**
     * If logout button is clicked, goes back to login page
     */
    private void goToSecondActivity() {
        Intent i = new Intent(this, BuzzLoginActivity.class);
        startActivity(i);
        finish();
    }

}
