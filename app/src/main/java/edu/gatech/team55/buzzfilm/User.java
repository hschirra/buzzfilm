package edu.gatech.team55.buzzfilm;

/**
 * This class represents a user
 */
public class User {
    private String name;
    private String major;
    private String userName;
    private String password;
    private boolean isBanned;
    private boolean isLocked;

    /**
     * Creates a user object
     * @param name: String representation of the user's real name
     * @param userName : String representation of the username
     * @param password : String representation of the user's password
     * @param major : String representation of the user's major
     */
    public User(String name, String userName, String password, String major) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.major = major;
    }

    /**
     * Creates a default user object with major set to undecided.
     * @param name: String representation of the user's real name
     * @param userName : String representation of the username
     * @param password : String representation of the user's password
     */
    public User(String name, String userName, String password) {
        this(name, userName, password, "Undecided");
    }

    /**
     * Returns the name of the user
     * @return name of user
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the major of the user
     * @return major of user
     */
    public String getMajor() {
        return major;
    }

    /**
     * Returns the username of the user
     * @return username of user
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Returns the password of the user
     * @return password : password of user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the ban status of the user
     * @return isBanned : ban status of user, true if banned.
     */
    public boolean isBanned() { return isBanned; }

    /**
     * Returns the lock status of the user
     * @return isLocked : lock status of user, true if locked.
     */
    public boolean isLocked() { return isLocked; }

    /**
     * Sets the major of the user
     * @param m : String representation of user major
     */
    public void setMajor(String m) {
        major = m;
    }

    /**
     * Sets the username of the user
     * @param u : String representation of user's username
     * */
    public void setUserName(String u) {
        userName = u;
    }

    /**
     * Sets the password of the user
     * @param p : String representation of user's password
     * */
    public void setPassword(String p) {
        password = p;
    }

    /**
     * Sets the name of the user
     * @param n : String representation of user's name
     * */
    public void setName(String n) {
        name = n;
    }

    /**
     * Sets the ban status of the user
     * @param isBanned : Pass true if user should be banned, false if otherwise
     * */
    public void banUser(boolean isBanned) {
        this.isBanned = isBanned;
    }

    /**
     * Sets the lock status of the user
     * @param isLocked : Pass true if user should be locked, false if otherwise
     * */
    public void lockUser(boolean isLocked) {
        this.isLocked = isLocked;
    }

    /**
     * Returns the user's status legibly
     * @return The user status with custom string
     * */
    public String getUserStatus() {
        return "Username: " + userName + ".\nActive: " + (!isBanned && !isLocked)
                + ".\nIs Banned: " + isBanned
                + ".\nIs Locked: " + isLocked + ".";
    }

}
