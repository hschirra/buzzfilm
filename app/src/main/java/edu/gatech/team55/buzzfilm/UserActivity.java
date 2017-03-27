package edu.gatech.team55.buzzfilm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * This controls the flow of the Admin's activity once they have
 * selected a specific user from the user list. From there they are
 * given the option to ban or unlock a user.
 */
public class UserActivity extends AppCompatActivity {

    private User currentUser = AdminMainActivity.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        //display the user's status (active, locked, or banned)
        TextView userText = (TextView)findViewById(R.id.userNameText);
        userText.setText(currentUser.getUserStatus());

        //unlock user
        Button unlockButton = (Button) findViewById(R.id.unlock_button);
        unlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.lockUser(false); //unlock user
                Intent intent = new Intent(UserActivity.this, UserActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //ban user
        Button banButton = (Button) findViewById(R.id.ban_button);
        banButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.banUser(true); //ban user
                Intent intent = new Intent(UserActivity.this, UserActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //unban user
        Button unbanButton = (Button) findViewById(R.id.unban_user_button);
        unbanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.banUser(false); //ban user
                Intent intent = new Intent(UserActivity.this, UserActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //lock user
        Button lockButton = (Button) findViewById(R.id.lock_user_button);
        lockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.lockUser(true); //ban user
                Intent intent = new Intent(UserActivity.this, UserActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //go back to admin main activity
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UserActivity.this, AdminMainActivity.class);
        startActivity(intent);
        finish();
    }
}
