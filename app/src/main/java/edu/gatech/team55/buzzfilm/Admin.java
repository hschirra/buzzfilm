package edu.gatech.team55.buzzfilm;

/**
 * This class represents an Admin who monitors BuzzFilm
 */
public class Admin {
    private String userName;
    private String password;

    /**
     * Creates a new instance of Admin
     *
     * @param userName username of admin to be added
     * @param password password of admin to be added
     */
    public Admin(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * Gets username of admin
     *
     * @return username of admin
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets password of admin
     *
     * @return password of admin
     */
    public String getPassword() {
        return password;
    }
}
