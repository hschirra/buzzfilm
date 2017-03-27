package edu.gatech.team55.buzzfilm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class AdminMainActivity extends AppCompatActivity {

    private static User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        ListView userList = (ListView) findViewById(R.id.usersListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(AdminMainActivity.this, R.layout.user_list_text_view, BuzzRegister.getUserList().getUsersArray());

        userList.setClickable(true);

        userList.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                String item = ((TextView) view).getText().toString(); //supposed to be username
                //set current user being examined
                currentUser = BuzzRegister.getUserList().getUser(item);
                //Go to new activity with user info

                Intent i = new Intent(AdminMainActivity.this, UserActivity.class);
                startActivity(i);
                finish();
            }

        });


    }

    /**
     * Returns the User of the inputted current username
     *
     * @return User
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * If logout button is clicked, goes back to login page
     */
    private void logout() {
        Intent i = new Intent(this, BuzzLoginActivity.class);
        startActivity(i);
        finish();
    }
}
