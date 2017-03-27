package edu.gatech.team55.buzzfilm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;
import java.util.PriorityQueue;

/**
 * This class controls whether or not the user gets recs by major or rating
 */
public class MovieRecsDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_recs_display);

        if (GetMovieRecsActivity.getRecBy() == 'r') {
            //get PriorityQueue<Movie> based on avg rating
            PriorityQueue<Movie> movies = SearchResultsActivity.getMovies().getRecByRating();
        } else {
            //get ArrayList<Movie> based on major (is also in order by rating)
            List<Movie> movies = SearchResultsActivity.getMovies().getRecByMajor(BuzzLoginActivity.getCurrentUser().getMajor());
        }

        //TODO: display movies in MovieRecsDisplay.xml
    }
}
