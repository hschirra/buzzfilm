package edu.gatech.team55.buzzfilm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

public class BuzzRegister extends AppCompatActivity {

    private EditText mUserView;
    private EditText mPasswordView;
    private EditText mNameView;
    private EditText mMajorView;
    private String name;
    private String major;
    private String user;
    private String password;
    private static UserList userList = new UserList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzz_register);

        mUserView = (EditText) findViewById(R.id.registerUsername);
        mPasswordView = (EditText) findViewById(R.id.registerPassword);
        mNameView = (EditText) findViewById(R.id.registerName);
        mMajorView = (EditText) findViewById(R.id.registerMajor);

        //attempt to register the user
        Button registerButton = (Button) findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Store values at the time of the login attempt.
                name = mNameView.getText().toString();
                major = mMajorView.getText().toString();
                user = mUserView.getText().toString();
                password = mPasswordView.getText().toString();
                attemptRegister();
            }
        });

        //cancel registration, back to login screen
        Button cancelButton = (Button) findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCancel = new Intent(BuzzRegister.this, BuzzLoginActivity.class);
                startActivity(intentCancel);
            }
        });
    }

    /**
     * returns list of users
     * @return UserList object holding all users
     */
    public static UserList getUserList() {
        return userList;
    }

    /**
     * attempt to register user
     */
    private void attemptRegister() {

        // Reset errors.
        mUserView.setError(null);
        mPasswordView.setError(null);
        mNameView.setError(null);
        mMajorView.setError(null);

        View focusView = null;
        boolean cancel = false;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError("Enter a password.");
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid username.
        if (TextUtils.isEmpty(user) || !isUserNameValid(user)) {
            mUserView.setError("This username is invalid.");
            focusView = mUserView;
            cancel = true;

        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }

        if (!cancel) {
            //successful registration
            User buzzUser;
            if (TextUtils.isEmpty(major)) {
                buzzUser = new User(name, user, password);
            } else {
                buzzUser = new User(name, user, password, major);
            }
            try {
                userList.addUser(buzzUser);
            } catch (IOException e) {
                System.out.println("IO Exception");
            }
            Intent i = new Intent(BuzzRegister.this, BuzzLoginActivity.class);
            startActivity(i);
        }
    }

    /**
     * Determines whether or not the username is valid
     * @param userName username to be checked
     * @return true if username is valid, false if it is not
     */
    private boolean isUserNameValid(String userName) {
        //check username exists
        return !userList.contains(userName);
    }
}
