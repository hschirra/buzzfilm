package edu.gatech.team55.buzzfilm;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Holds user information for all users.
 * @author team 55
 */
public class UserList {

    private List<User> users;

    /**
     * Creates a new instance of a userlist using the Users.txt file
     * that stores all user info
     */
    public UserList() {
        users = new ArrayList<>(0);
//        Scanner userScan = new Scanner("../../../../../assets/Users.txt");
//        while (userScan.hasNext()) {
//            String line = userScan.nextLine();
//            String[] info = line.split(",");
//            if (info.length > 1) {
//                users.add(new User(info[0], info[1], info[2], info[3]));
//            }
//        }
    }

    /**
     * Adds a user to the working arraylist of users
     * and appends the user info to the text file
     * @param u the user to be added
     */
    public void addUser(User u) throws IOException{
        users.add(u);
//        FileWriter fw = new FileWriter("../../../../../assets/Users.txt", true);
//        fw.write(u.getName() + "," + u.getUserName() + "," + u.getPassword()
//                + "," + u.getMajor() + "\n");
//        fw.close();
    }

    /**
     * Determines whether or not a username is already taken
     * @param userName username to be checked
     * @return true if name is in use, false if it is not
     */
    public boolean contains(String userName) {
        for (User u : users) {
            if (userName.equals(u.getUserName())) {
                return true;
            }
        }
        return false;
    }


    /**
     * Determines whether or not a user's info matches
     * @param userName user's username
     * @param password user's password
     * @return true if they both match, false if one or both do not
     */
    public boolean isValidUser (String userName, String password) {
        for (User u : users) {
            if (userName.equals(u.getUserName())) {
                return password.equals(u.getPassword()) && !u.isBanned() && !u.isLocked();
            }
        }
        return false;
    }

    /**
     * returns the user that matches the given username
     * @param userName username of user to be returned
     * @return user that matches username given
     */
    public User getUser(String userName) {
        for (User u : users) {
            if (userName.equals(u.getUserName())) {
                return u;
            }
        }
        return null;
    }

    /**
     * converts the userList arraylist to an array of usernames
     * @return string array of usernames
     */
    public String[] getUsersArray() {
        String[] arr = new String[users.size()];
        for (int i = 0; i < users.size(); i++) {
            arr[i] = users.get(i).getUserName();
        }
        return arr;
    }
}
