package edu.gatech.team55.buzzfilm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * This class allows user to choose movie recommendations based on overall ratings or their major
 * @author team 55
 */
public class GetMovieRecsActivity extends AppCompatActivity {

    private static char recBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_movie_recs);

        //get movie recs by rating
        Button recByRating = (Button) findViewById(R.id.recs_by_rating_button);
        recByRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recBy = 'r';
                Intent intentMovieRecsByRating = new Intent(GetMovieRecsActivity.this,
                        MovieRecsDisplay.class);
                startActivity(intentMovieRecsByRating);
            }
        });

        //get movie recs by major
        Button recByMajor = (Button) findViewById(R.id.recs_by_major_button);
        recByMajor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recBy = 'm';
                Intent intentMovieRecsByMajor = new Intent(GetMovieRecsActivity.this,
                        MovieRecsDisplay.class);
                startActivity(intentMovieRecsByMajor);
            }
        });

        Button backButton = (Button) findViewById(R.id.back_to_main_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recBy = 'm';
                Intent backToMain = new Intent(GetMovieRecsActivity.this, MainActivity.class);
                startActivity(backToMain);
            }
        });
    }

    /**
     * Getter for which type of filter user wants to
     * appy to movie recs
     * @return 'r' for by rating or 'm' for by major
     */
    public static char getRecBy() {
        return recBy;
    }
}
