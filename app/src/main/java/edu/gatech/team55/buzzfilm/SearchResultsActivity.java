package edu.gatech.team55.buzzfilm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This class displays list of movies prompted by user input
 */
public class SearchResultsActivity extends AppCompatActivity {
    private String[] titles;
    ListView titlesList;
    private static MovieManager movies = new MovieManager();
    private static Movie currentMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Titles is a String array that contains all the titles from the API
        //It is successfully passed to this class.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        //Following line is only to create dummy recommendations
        createDummyRatings();

        Bundle bundle = this.getIntent().getExtras();

        titles = bundle.getStringArray("Titles");

        titlesList = (ListView) findViewById(R.id.titlesView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(SearchResultsActivity.this, R.layout.movie_results_text_view, titles);

        titlesList.setClickable(true);

        titlesList.setAdapter(adapter);

        titlesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                String item = ((TextView) view).getText().toString(); //supposed to be movie title
                //check if movie is already in hashmap
                if (!movies.contains(item)) {
                    currentMovie = new Movie(item);
                } else {
                    currentMovie = movies.getMovie(item);
                }
                //Go to new activity with descriptions
                Log.w("API", "Inside onItemClick()");
                Bundle bundle = new Bundle();
                bundle.putStringArray("Titles", titles);

                Intent intent = new Intent(SearchResultsActivity.this, RatingResults.class);
                intent.putExtras(bundle);

                startActivity(intent);
                finish();
                //Hit back to see search results
            }

        });
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(SearchResultsActivity.this, SearchMovies.class);
        startActivity(intent);
        finish();
    }

    /**
     * Gets movie currently being viewed
     *
     * @return current movie
     */
    public static Movie getCurrentMovie() {
        return currentMovie;
    }

    /**
     * Gets list total movie objects
     *
     * @return total movie objects
     */
    public static MovieManager getMovies() {
        return movies;
    }

    /**
     * Creates dummy ratings
     * Helper method for testing search results
     */
    private void createDummyRatings() {
        Rating tmpRatingOne = new Rating(4, "Undecided", "");
        Rating tmpRatingTwo = new Rating(3, "Undecided", "");
        Rating tmpRatingThree = new Rating(5, "CS", "");
        Rating tmpRatingFour = new Rating(4 ,"CS", "");

        Movie tmpMovieOne = new Movie("Toy Story");
        tmpMovieOne.addRating(tmpRatingOne);
        tmpMovieOne.addRating(tmpRatingTwo);

        Movie tmpMovieTwo = new Movie("The Graduate");
        tmpMovieTwo.addRating(tmpRatingThree);
        tmpMovieTwo.addRating(tmpRatingFour);

        movies.addMovie(tmpMovieOne);
        movies.addMovie(tmpMovieTwo);
    }
}
