package edu.gatech.team55.buzzfilm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONException;

public class SearchMovies extends AppCompatActivity {

    private EditText searchField;
    private CheckBox movieSearch;
    private CheckBox seriesSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);

        searchField = (EditText) findViewById(R.id.searchQuery);
        //searchField.getText.toString();

        movieSearch = (CheckBox) findViewById(R.id.movieBox);
        seriesSearch = (CheckBox) findViewById(R.id.seriesBox);

        Button searchButton = (Button) findViewById(R.id.startSearch);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    initiateSearch();
                } catch (JSONException e) {
                    //Do something with error
                }
            }
        });
    }

    /**
     * Initiates movie search based on if the user checks if he or she wants to search for a movie or series.
     * Populates screen with movie or series titles based on the user's input
     */
    private void initiateSearch() throws JSONException {
        boolean tmpMovie = false;
        boolean tmpSeries = false;

        if (movieSearch.isChecked()) {
            tmpMovie = true;
        }

        if (seriesSearch.isChecked()) {
            tmpSeries = true;
        }

        String search = searchField.getText().toString();

        String[] titles;
        if (tmpMovie && tmpSeries) {
            APISearch newSearch = new APISearch(this, search);
            titles = newSearch.getTitles();
        } else if (tmpMovie) {
            APISearch newSearch = new APISearch(this, search, true);
            titles = newSearch.getTitles();
        } else if (tmpSeries) {
            APISearch newSearch = new APISearch(this, search, false);
            titles = newSearch.getTitles();
        } else {
            Toast.makeText(getApplicationContext(), "Please check one of the boxes!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(SearchMovies.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}