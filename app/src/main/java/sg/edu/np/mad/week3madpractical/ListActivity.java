package sg.edu.np.mad.week3madpractical;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Random rd = new Random();
        // Create a ArrayList storing User objects
        ArrayList<User> userList = User.userList;
        userList = new ArrayList<>();

        UserDBHandler db = new UserDBHandler(ListActivity.this);
        //Use a For Loop to add 20 User objects into the ArrayLists
        for (int count = 0; count < 20; count++)
        {
            User randomUserObj = new User();
            //Randomize the respective attributes
            int randomInt =  rd.nextInt(2000000000 - 100000000);
            String randomName = Integer.toString(randomInt);
            randomUserObj.name = "Name" + randomName;
            int randomNumber =  rd.nextInt(2000000000 - 100000000);
            String randomDesc = Integer.toString(randomNumber);
            randomUserObj.description = "Description " + randomDesc;
            randomUserObj.followed = rd.nextBoolean();
            //Add User object into the ArrayList
            //userList.add(randomUserObj);
            //db.insertUsers(randomUserObj);
        }

        userList = db.getUsers();

        RecyclerView rv = findViewById(R.id.recyclerView);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        UserAdapter adapter = new UserAdapter(this, userList);
        rv.setLayoutManager(layout);
        rv.setAdapter(adapter);


    }
}