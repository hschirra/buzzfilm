package edu.gatech.team55.buzzfilm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A login screen that offers login via username/password.
 */
public class BuzzLoginActivity extends AppCompatActivity {

    // UI references.
    private String userName;
    private String password;
    EditText userNameView;
    EditText passwordView;
    private static User currentUser;
    private int attempts = 0;
    AdminList adminList = new AdminList();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzz_login);
        adminList.addAdmin(new Admin("name", "pass"));

        //click sign in button
        Button loginButton = (Button) findViewById(R.id.sign_in_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get username from text field
                userNameView = (EditText) findViewById(R.id.userName);
                userName = userNameView.getText().toString();

                //get password from text field
                passwordView = (EditText) findViewById(R.id.password);
                password = passwordView.getText().toString();
                attemptLogin();
            }
        });

        //click register button
        Button registerButton = (Button) findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(BuzzLoginActivity.this, BuzzRegister.class);
                startActivity(intentRegister);
            }
        });
    }

    /**
     * Gets validity of user
     * Returns true if user exists with given username and password
     *
     * @return validity value of true or false
     */
    public boolean isValidUser() {
        //check to see if there is a user with that username and password
        return (BuzzRegister.getUserList().isValidUser(userName, password) || adminList.isValidAdmin(userName, password));
    }

    /**
     * Attempt to log in user
     */
    public void attemptLogin() {
        // Reset errors.
        userNameView.setError(null);
        passwordView.setError(null);
        boolean userListEmpty = false;
        boolean userExists = true;

        View focusView = null;
        boolean cancel = false;

        if (!BuzzRegister.getUserList().contains(userName))  {
            userExists = false;
            focusView = userNameView;
            userNameView.setError("Welcome Admin");
        }

        try {
            BuzzRegister.getUserList().getUser(userName).isLocked();
        } catch (NullPointerException n) {
            userListEmpty = true;
            focusView = userNameView;
            if (userExists) {
                userNameView.setError("No users exist, register to be the first!");
            }
        }

        // Check to make sure the user entered a password.
        if (TextUtils.isEmpty(password)) {
            passwordView.setError("Enter a password.");
            focusView = passwordView;
            cancel = true;
        }

        // Check to make sure the user entered a username.
        if (TextUtils.isEmpty(userName)) {
            userNameView.setError("Enter a username.");
            focusView = userNameView;
            cancel = true;
        }

        // Check for a valid username and password combination.
        if (!isValidUser() && !userListEmpty) {
            if (BuzzRegister.getUserList().contains(userName)) {
                attempts++;
            }
            if (attempts > 2 || BuzzRegister.getUserList().getUser(userName).isLocked()) {
                BuzzRegister.getUserList().getUser(userName).lockUser(true);
                Toast.makeText(getApplicationContext(), "Account locked, contact an admin", Toast.LENGTH_SHORT).show();
            } else if (BuzzRegister.getUserList().getUser(userName).isBanned()) {
                Toast.makeText(getApplicationContext(), "Account is banned", Toast.LENGTH_SHORT).show();
            } else {
                userNameView.setError("Incorrect username and/or password.");
            }
            focusView = userNameView;
            cancel = true;
        }

        if (cancel || !userExists) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }

        if (!cancel && !userExists) {
            Intent i = new Intent(BuzzLoginActivity.this, AdminMainActivity.class);
            startActivity(i);
        }

        if (!cancel && userExists) {
            //successful login!
            attempts = 0;
            currentUser = BuzzRegister.getUserList().getUser(userName);
            if (currentUser != null) {
                Intent i = new Intent(BuzzLoginActivity.this, MainActivity.class);
                startActivity(i);
            } else {
                Intent i = new Intent(BuzzLoginActivity.this, AdminMainActivity.class);
                startActivity(i);
            }
        }
    }

    /**
     * Gets currently logged in user
     *
     * @return current user
     */
    public static User getCurrentUser() {
        return currentUser;
    }
}

