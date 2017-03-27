package edu.gatech.team55.buzzfilm;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds admin information for all admins.
 */
public class AdminList {

    private List<Admin> admins;

    public AdminList() {
        admins = new ArrayList<>(0);
    }

    /**
     * Adds an admin to the list of admins
     * @param a the admin to be added
     */
    public void addAdmin(Admin a) {
        admins.add(a);
    }

    /**
     * Checks if an Admin is contained in the list of admins
     * @param userName the admin to be checked
     * @return boolean whether admin is in the admin list or not
     */
    public boolean contains(String userName) {
        for (Admin a : admins) {
            if (userName.equals(a.getUserName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether an Admin is a valid admin or not
     * @param userName the admin username to be checked
     * @param password the admin password to be checked
     * @return boolean whether admin is a valid admin or not
     */
    public boolean isValidAdmin (String userName, String password) {
        for (Admin a : admins) {
            if (userName.equals(a.getUserName())) {
                return password.equals(a.getPassword());

            }
        }
        return false;
    }

    /**
     * Returns the admin of the inputted username
     * @return Admin
     */
    public Admin getAdmin (String userName) {
        for (Admin a : admins) {
            if (userName.equals(a.getUserName())) {
                return a;
            }
        }
        return null;
    }
}