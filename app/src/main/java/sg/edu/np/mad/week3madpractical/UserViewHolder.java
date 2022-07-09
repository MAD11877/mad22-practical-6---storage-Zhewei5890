package sg.edu.np.mad.week3madpractical;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder{

    TextView username;
    TextView description;
    ImageView appIcon;

    public UserViewHolder(View item)
    {
        super(item);
        username = item.findViewById(R.id.name);
        description = item.findViewById(R.id.description);
        appIcon = item.findViewById(R.id.appicon);
    }

}
