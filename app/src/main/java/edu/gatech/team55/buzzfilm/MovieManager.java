package edu.gatech.team55.buzzfilm;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * This class manages Movie objects
 * @author team 55
 */
public class MovieManager {

    private Map<String, Movie> movieHashMap;

    /**
     * Creates a new MovieManager object
     */
    public MovieManager() {
        movieHashMap = new HashMap<>();
        //load data from text file
//        Scanner movieScan = new Scanner("../../../../../assets/Movies.txt");
//        while (movieScan.hasNext()) {
//            String line = movieScan.nextLine();
//            String[] info = line.split("/");
//            if (info.length > 2) {
//                String title = info[0];
//                Movie m = new Movie(title);
//                movieHashMap.put(title, m);
//                String[] ratings = info[1].split("..~..");
//                //for loop to add ratings to Movie
//                for (int i = 0; i < ratings.length; i++) {
//                    String[] rating = ratings[i].split("..`..");
//                    m.addRating(new Rating(Integer.parseInt(rating[0]), rating[1], rating[2]));
//                }
//            }
//        }
    }

    /**
     * Adds a movie to the list of movies that have ratings
     * @param movie the movie to be added
     */
    public void addMovie(Movie movie) {
        movieHashMap.put(movie.getTitle(), movie);
        //add Movie to text file
//        try {
//            FileWriter fw = new FileWriter("../../../../../assets/Movies.txt", true);
//            fw.write(movie.getTitle() + "/");
//            List<Rating> list = movie.getRatings();
//            for (int i = 0; i < list.size() - 1; i++) {
//                Rating r = list.get(i);
//                fw.write(r.getRatingValue() + "..`.." + r.getMajor()
//                        + "..`.." + r.getComments() + "..~..");
//            }
//            Rating ra = list.get(list.size() - 1);
//            fw.write(ra.getRatingValue() + "..`.." + ra.getMajor()
//                    + "..`.." + ra.getComments() + "\n");
//            fw.close();
//        } catch (IOException e) {
//            //catch exception
//        }
    }

    /**
     * Finds a movie by title
     * @param title title of a movie
     * @return Movie object with corresponding title
     */
    public Movie getMovie(String title) {
        return movieHashMap.get(title);
    }

    /**
     * Checks to see whether or not this movie has been rated
     * @param title title of Movie
     * @return true if it has ratings, false if not
     */
    public boolean contains(String title) {
        return movieHashMap.containsKey(title);
    }

    /**
     * Sorts the movies by rating
     * @return a priority queue of the sorted movies
     */
    public PriorityQueue<Movie> getRecByRating() {
        PriorityQueue<Movie> recByRating = new PriorityQueue<>(20,
                Collections.reverseOrder());
        for (Movie m : movieHashMap.values()) {
            recByRating.add(m);
        }
        return recByRating;
    }

    /**
     * Sorts movies by major
     * @param major Major of the user requestings recs
     * @return a list of movies sorted by major
     */
    public List<Movie> getRecByMajor(String major) {
        ArrayList<Movie> recByMajor = new ArrayList<>();
        for (Movie m : getRecByRating()) {
            boolean added = true;
            int i = 0;
            while (added && i < m.getRatings().size()) {
                if (m.getRatings().get(i).getMajor().equals(major)) {
                    recByMajor.add(m);
                    added = false;
                }
            }
        }
        return recByMajor;
    }
}
