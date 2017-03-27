package edu.gatech.team55.buzzfilm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * activity for user's profile
 */
public class UserProfile extends AppCompatActivity {
    private String newName;
    private String newUser;
    private String newPass;
    private String newMajor;
    private User currentUser = BuzzLoginActivity.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //confirm changes
        Button confirmButton = (Button) findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get attributes from text fields
                EditText nameField = (EditText) findViewById(R.id.change_name);
                newName = nameField.getText().toString();
                EditText userField = (EditText) findViewById(R.id.change_username);
                newUser = userField.getText().toString();
                EditText passField = (EditText) findViewById(R.id.change_password);
                newPass = passField.getText().toString();
                EditText majorField = (EditText) findViewById(R.id.change_major);
                newMajor = majorField.getText().toString();

                makeChanges();

                //go back to main activity
                Intent intentConfirm = new Intent(UserProfile.this, MainActivity.class);
                startActivity(intentConfirm);
            }
        });

        //cancel changes, go back to main activity
        Button cancelButton = (Button) findViewById(R.id.cancel_changes_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCancel = new Intent(UserProfile.this, MainActivity.class);
                startActivity(intentCancel);
            }
        });
    }

    /**
     * make changes to user
     */
    private void makeChanges() {
        if (!TextUtils.isEmpty(newName)) {
            currentUser.setName(newName);
        }

        if (!TextUtils.isEmpty(newUser)) {
            currentUser.setUserName(newUser);
        }

        if (!TextUtils.isEmpty(newPass)) {
            currentUser.setPassword(newPass);
        }

        if (!TextUtils.isEmpty(newMajor)) {
            currentUser.setMajor(newMajor);
        }
    }
}
