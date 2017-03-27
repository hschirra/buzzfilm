package edu.gatech.team55.buzzfilm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Shows the movie + description + overall rating + individual ratings
 * @author team 55
 */
public class RatingResults extends AppCompatActivity {

    private Movie currentMovie = SearchResultsActivity.getCurrentMovie();
    private String[] titles;
    private String title = currentMovie.getTitle();
    private double ovrRating = currentMovie.getAverageRating();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_results);

        Log.w("API", "In RatingResults.class");

        Bundle bundle = this.getIntent().getExtras();
        titles = bundle.getStringArray("Titles");

        //display movie title and average rating
        TextView titleText = (TextView)findViewById(R.id.movieTitle);
        titleText.setText(title);
        TextView ratingText = (TextView)findViewById(R.id.averageRating);
        String rating = "Avg. Rating: " + ovrRating + " / 5";
        ratingText.setText(rating);

        //click rate movie, go to movie rating activity
        Button rateButton = (Button) findViewById(R.id.rate_button);
        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRate = new Intent(RatingResults.this, MovieRating.class);
                Bundle bundle = new Bundle();
                bundle.putStringArray("Titles", titles);
                intentRate.putExtras(bundle);
                Log.w("API", "Start MovieRating");
                startActivity(intentRate);
                finish();
            }
        });

        final Button cancelButton = (Button) findViewById(R.id.back_to_search);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backtoSearchIntent = new Intent(RatingResults.this,
                        SearchResultsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArray("Titles", titles);
                backtoSearchIntent.putExtras(bundle);
                startActivity(backtoSearchIntent);
                finish();
            }
        });
    }
}
