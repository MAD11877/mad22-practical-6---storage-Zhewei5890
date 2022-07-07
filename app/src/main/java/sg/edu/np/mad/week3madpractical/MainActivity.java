package sg.edu.np.mad.week3madpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button followButton, messageButton;

    TextView helloWord, randomText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Finding the components by ID
        followButton = findViewById(R.id.follow);
        messageButton = findViewById(R.id.message);
        helloWord = findViewById(R.id.textView);
        randomText = findViewById(R.id.randomText);

        //Receiving intent from the RecyclerView
        Intent receivedFromListActivity = getIntent();
        User userObject = (User) receivedFromListActivity.getSerializableExtra("User Object");

        //Set Text to the String from Intent
        helloWord.setText(userObject.name);
        randomText.setText(userObject.description);

        UserDBHandler database = new UserDBHandler(MainActivity.this);

        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!userObject.followed){
                    followButton.setText("Unfollow");
                    Toast.makeText(MainActivity.this, "Follow", Toast.LENGTH_SHORT).show();
                    userObject.followed = true;
                }
                else{
                    followButton.setText("Follow");
                    Toast.makeText(MainActivity.this, "Unfollow", Toast.LENGTH_SHORT).show();
                    userObject.followed = false;
                }

                database.updateUser(userObject, userObject.followed);

            }
        });

        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(mainActivity);
            }
        });





    }




}