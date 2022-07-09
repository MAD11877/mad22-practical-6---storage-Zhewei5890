package sg.edu.np.mad.week3madpractical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class UserDBHandler extends SQLiteOpenHelper {

    Context context;

    public UserDBHandler(Context c)
    {
        super (c, "users.db", null, 1);
        this.context = c;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * CREATE TABLE USER (name TEXT, description TEXT, id INTEGER PRIMARY KEY AUTOINCREMENT, followed BOOLEAN)
         */
        db.execSQL("CREATE TABLE USER (name TEXT, description TEXT, id INTEGER PRIMARY KEY AUTOINCREMENT, followed BOOLEAN)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USER");
        onCreate(db);
    }

    public void insertUsers(User u)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", u.name);
        cv.put("description", u.description);
        cv.put("followed", u.followed);

        long result = db.insert("USER", null, cv);

        if (result == -1){
            Toast.makeText(context, "Insert Failed", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(context, "Insert Successful", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public ArrayList<User> getUsers()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<User> list = new ArrayList<>();

        /**
         * SELECT * FROM USER
         */

        Cursor cursor = db.rawQuery("SELECT * FROM USER", null);
        while(cursor.moveToNext())
        {
            User u = new User();
            u.name = cursor.getString(0);
            u.description = cursor.getString(1);
            list.add(u);
        }

        return list;
    }

    public void updateUser(User u, Boolean newFollowStatus)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("followed", newFollowStatus); //Update the follow status of the user


        String[] selectionArgs = {u.name};

        long result = db.update("USER", cv, "name=?", selectionArgs);

        if (result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Update Successful", Toast.LENGTH_SHORT).show();
        }

    }




}
