package edu.gatech.team55.buzzfilm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.*;

/*
* @author Group#55
* @version 1.0
*
* This class will take the search term given by the search activity and return
* an array of movie titles the search finds on OMDB per the API.
*/

public class APISearch {

    private static final String urlFirst = "http://www.omdbapi.com/?s=";
    private static final String urlSecond = "&y=&plot=short&r=json";
    private static final String urlFilm = "&type=movie&y=&plot=short&r=json";
    private static final String urlSeries = "&type=series&y=&plot=short&r=json";
    private final String searchURL;
    private String[] titles;
    private final Context context;
    private String search;

    /**
     * Creates an API search defaulted as a movie search
     * @param context : The current application context
     * @param search : The search the user passed
     * */
    public APISearch(Context context, String search) throws JSONException {
        this.context = context;
        this.search = search;
        search = search.replaceAll("\\s", "+");
        searchURL = urlFirst + search + urlSecond;
        if (isValidUrl(searchURL)) {
            this.queryOMDB(searchURL);
        } else {
            throw new JSONException("URL Invalid!");
        }
    }

    /**
     * Creates an API search that could be a movie or a show
     * @param context : current Application context
     * @param search : search string the user passed in
     * @param isMovie : True if this is a movie search, false if this is a tv show search
     * */
    public APISearch(Context context, String search, boolean isMovie) throws JSONException {
        this.context = context;
        this.search = search;
        search = search.replaceAll("\\s", "+");
        if (isMovie) {
            searchURL = urlFirst + search + urlFilm;
        } else {
            searchURL = urlFirst + search + urlSeries;
        }
        if (isValidUrl(searchURL)) {
            this.queryOMDB(searchURL);
        } else {
            throw new JSONException("URL Invalid!");
        }
    }

    /*
    * Checks if the search phrase is alphanumeric
    * @param url : the search term passed by the user
     */
    public boolean isValidUrl(String url) {
        String tmpSearch = search.replaceAll("\\s+","");
        return tmpSearch.matches("[A-Za-z0-9]+");
    }

    private void queryOMDB(String url) {

        RequestQueue mRequestQueue;

        Log.w("API", "Querying...");

        // Instantiate the cache
        Cache cache = new DiskBasedCache(context.getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);

        // Start the queue
        mRequestQueue.start();

        // Formulate the request and handle the response.
        final JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.toString().equals("{\"Response\"\"False\",\"Error\":\"Movie not found!\"}")) {
                            Toast.makeText(context, "No movies found!", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                JSONArray array = response.getJSONArray("Search");
                                titles = new String[array.length()];
                                for (int i = 0; i < array.length(); i++) {
                                    titles[i] = array.getJSONObject(i).getString("Title");

                                    Bundle bundle = new Bundle();
                                    bundle.putStringArray("Titles", titles);

                                    Intent intent = new Intent(context, SearchResultsActivity.class);
                                    intent.putExtras(bundle);

                                    context.startActivity(intent);
                                }
                            } catch (JSONException e) {
                                Log.w("API", "JSONException");
                                Toast.makeText(context, "No results found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.w("API", error.toString());
                    }
                });
        // Add the request to the RequestQueue.
        mRequestQueue.add(request);
    }

    /**
     * Returns an array of titles matching the search query
     * @return A string array representation of the titles returned by the search.
     * */
    public String[] getTitles() {
        return titles;
    }
}
