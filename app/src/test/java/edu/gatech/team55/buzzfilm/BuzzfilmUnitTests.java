package edu.gatech.team55.buzzfilm;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class BuzzfilmUnitTests {
    private UserList users;
    private MovieManager movies;
    private AdminList admins;
    private UserList emptyUsers;
    private APISearch search;

    @Before
    public void setUp() throws IOException, JSONException {
        emptyUsers = new UserList();
        users = new UserList();
        User user1 = new User("name", "user1", "pass", "major");
        users.addUser(user1);
        User user2 = new User("name", "user2", "pass", "major");
        User user3 = new User("name", "user3", "pass", "major");
        User user4 = new User("name", "user4", "pass", "major");
        users.addUser(user2);
        user2.lockUser(true);
        users.addUser(user3);
        user3.banUser(true);
        users.addUser(user4);

        admins = new AdminList();
        Admin admin1 = new Admin("chris", "halima");
        admins.addAdmin(admin1);
        Admin admin2 = new Admin("heather", "schirra");
        admins.addAdmin(admin2);
        Admin admin3 = new Admin("zach", "hancock");
        admins.addAdmin(admin3);
        Admin admin4 = new Admin("jasmine", "voigtlander");
        admins.addAdmin(admin4);
        Admin admin5 = new Admin("thomas", "lagrange");
        admins.addAdmin(admin5);

        movies = new MovieManager();

        Movie m = new Movie("A Cool Movie");
        Rating r1 = new Rating(5, "CS", "awesome");
        Rating r2 = new Rating(3, "Management", "lame");
        Rating r3 = new Rating(4, "Physics", "most excellent");
        m.addRating(r1);
        m.addRating(r2);
        m.addRating(r3);

        Movie m2 = new Movie("No One Has Seen It");

        movies.addMovie(m);
        movies.addMovie(m2);
    }

    /**
     * Tests isValidUser method in UserList
     * written by Heather Schirra
     */
    @Test
    public void testIsValidUser()  {
        assertEquals(true, users.isValidUser("user1", "pass"));
        assertEquals(false, users.isValidUser("user6", "pass"));
        assertEquals(false, users.isValidUser("user2", "pass"));
        assertEquals(false, users.isValidUser("user3", "pass"));
        assertEquals(false, users.isValidUser("user4", "p"));

    }

    /**
     * Tests getAverageRating method in Movie
     * written by Zach Hancock
     */
    @Test
    public void testGetAverageRating()  {
        assertEquals(4, movies.getMovie("A Cool Movie").getAverageRating(), .01);
        assertEquals(0, movies.getMovie("No One Has Seen It").getAverageRating(), .01);
    }


    /**
     * Tests isValidAdmin method in AdminList
     * written by Chris Halima
     */
    @Test
    public void testIsValidAdmin()  {
        assertEquals(true, admins.isValidAdmin("chris", "halima"));
        assertEquals(true, admins.isValidAdmin("heather", "schirra"));
        assertEquals(true, admins.isValidAdmin("zach", "hancock"));
        assertEquals(true, admins.isValidAdmin("jasmine", "voigtlander"));
        assertEquals(true, admins.isValidAdmin("thomas", "lagrange"));
        assertEquals(false, admins.isValidAdmin("user9", "pass"));
        assertEquals(false, admins.isValidAdmin("user3", "pass"));
        assertEquals(false, admins.isValidAdmin("user1", "pass"));
        assertEquals(false, admins.isValidAdmin("user2", "p"));
        assertEquals(false, admins.isValidAdmin("bob", "watersisawesome"));
    }

    /**
     * Tests getAdmin method in AdminList
     * written by Jasmin Voigtlander
     */
    @Test
    public void testGetAdmin()  {
        assertEquals(admin1, admins.getAdmin("chris"));
        assertEquals(admin2, admins.getAdmin("heather"));
        assertEquals(admin3, admins.getAdmin("zach"));
        assertEquals(admin4, admins.getAdmin("jasmin"));
        assertEquals(admin5, admins.getAdmin("thomas"));
        assertEquals(null, admins.getAdmin("user1"));
        assertEquals(null, admins.getAdmin("user2"));
        assertEquals(null, admins.getAdmin("user4"));
        assertEquals(null, admins.getAdmin("user9"));
    }

    /**
     * Test containts method in AdminList
     * written by Thomas Lagrange
     */
    @Test
    public void testContains() {
        assertEquals(false, new AdminList().contains("chris"));
        assertEquals(false, admins.contains("jeremy"));
        assertEquals(true, admins.contains("chris"));
        assertEquals(true, admins.contains("heather"));
        assertEquals(true, admins.contains("zach"));
        assertEquals(true, admins.contains("jasmine"));
        assertEquals(true, admins.contains("thomas"));
    }
}