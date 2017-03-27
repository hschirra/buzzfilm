package edu.gatech.team55.buzzfilm;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a rateable Movie object
 */
public class Movie {
    private String title;
    private List<Rating> ratings;

    /**
     * Creates a new instance of Movie
     *
     * @param title title of movie
     */
    public Movie(String title) {
        this.title = title;
        this.ratings = new ArrayList<>(0);
    }

    /**
     * Gets title of movie
     *
     * @return title of movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets average rating of movie
     *
     * @return average rating of movie
     */
    public double getAverageRating() {
        double averageRating = 0;
        int i;
        for (i = 0; i < ratings.size(); i++) {
            averageRating += ratings.get(i).getRatingValue();
        }
        if (i == 0) {
            return averageRating;
        } else {
            averageRating = averageRating / i;
            return averageRating;
        }
    }

    /**
     * Adds movie rating to list of ratings for movie
     *
     * @param rat movie rating
     */
    public void addRating(Rating rat) {
        ratings.add(rat);
    }

    /**
     * Gets list of total ratings of movie
     *
     * @return total ratings of movie
     */
    public List<Rating> getRatings() {
        return ratings;
    }
}
