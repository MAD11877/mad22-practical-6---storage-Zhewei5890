package sg.edu.np.mad.week3madpractical;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    public String name;
    public String description;
    public int id;
    public boolean followed;
    static ArrayList<User> userList;
}
