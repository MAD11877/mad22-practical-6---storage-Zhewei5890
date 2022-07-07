package sg.edu.np.mad.week3madpractical;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter
        extends RecyclerView.Adapter<UserViewHolder> {

    ArrayList<User> data;
    Context context;
    public UserAdapter(Context context, ArrayList<User> data)
    {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        String dataName = data.get(position).name;
        String lastCharacter = dataName.substring(dataName.length() - 1);
        return (lastCharacter.equals("7"))?1:0;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item;
        if (viewType == 1)
              item = LayoutInflater.from(context)
                .inflate(R.layout.mod_rcv, parent, false);
        else
            item = LayoutInflater.from(context)
                    .inflate(R.layout.user_details, null, false);
        return new UserViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder,
                                 int position) {

        User u = data.get(position);
        holder.username.setText(u.name);
        holder.description.setText(u.description);


        //Set onClickListener for image view
        holder.appIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ab = new AlertDialog.Builder(context);

                ab.setTitle("Profile");
                ab.setMessage(u.name);

                ab.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.putExtra("User Object", u);
                        context.startActivity(intent);

                    }
                });
                ab.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = ab.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
