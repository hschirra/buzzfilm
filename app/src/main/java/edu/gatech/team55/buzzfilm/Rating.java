package edu.gatech.team55.buzzfilm;

/**
 * This class represents a Rating object
 */
public class Rating {
    private String major;
    private String comments;
    private int rating;

    /**
     * creates a new rating
     */
    public Rating(int rating, String major, String comments) {
        this.rating = rating;
        this.major = major;
        this.comments = comments;
    }

    /**
     * Returns the numerical rating
     * @return numerical rating
     */
    public int getRatingValue() {
        return rating;
    }

    /**
     * Returns the major of the student who gave the rating
     * @return major
     */
    public String getMajor() {
        return major;
    }

    /**
     * Returns the comments of the rating
     * @return comments
     */
    public String getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "Rating: " + rating
                + "/5.\nComments: " + comments;
    }
}
