package edu.gatech.team55.buzzfilm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This class allows a user to rate a movie.
 */
public class MovieRating extends AppCompatActivity {

    private String major;
    private String comments;
    private int rating;
    private String[] titles;
    private Movie currentMovie = SearchResultsActivity.getCurrentMovie();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_rating);
        Bundle bundle = this.getIntent().getExtras();
        titles = bundle.getStringArray("Titles");
        TextView titleText = (TextView)findViewById(R.id.movieTitle);
        titleText.setText(currentMovie.getTitle());
        Log.w("API", "Inside onCreate for MovieRating");  //once you confirm you want to give the rating, the info is stored and a new Rating object has been created
        Button confirmButton = (Button) findViewById(R.id.giveRatingButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ratingField = (EditText) findViewById(R.id.numRating);
                rating = Integer.parseInt(ratingField.getText().toString());
                major = BuzzLoginActivity.getCurrentUser().getMajor();
                EditText commentsField = (EditText) findViewById(R.id.comments);
                comments = commentsField.getText().toString();
                Rating newRating = new Rating(rating, major, comments); //add rating
                currentMovie.addRating(newRating);
                if (!SearchResultsActivity.getMovies().contains(currentMovie.getTitle())) {
                    SearchResultsActivity.getMovies().addMovie(currentMovie);
                }
                Intent intentConfirm = new Intent(MovieRating.this, SearchResultsActivity.class); //back to search results
                Bundle bundle = new Bundle();
                bundle.putStringArray("Titles", titles);
                intentConfirm.putExtras(bundle);
                startActivity(intentConfirm);
            }
        });
        //cancel giving a rating and go back to main screen
        Button cancelButton = (Button) findViewById(R.id.cancelRatingButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCancel = new Intent(MovieRating.this, RatingResults.class);
                startActivity(intentCancel);
            }
        });
    }

    /**
     * Returns the rating of the movie rating
     * @return int
     */
    public int getRating() { return rating; }
}
