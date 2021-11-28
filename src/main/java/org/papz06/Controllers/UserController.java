package org.papz06.Controllers;

import org.papz06.Function;
import org.papz06.Models.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    List<User> userList = new ArrayList<>();

    public UserController() {
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery("select * from users;");
            while (rs.next()) {
                userList.add(
                        new User(rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5)
                        )
                );
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public boolean checkExistUser(String user) {
        for (User us : userList){
            if (us.getLogin().equals(user))
                return false;
        }
        return true;
    }

    public boolean checkExist(String user, String password) {
        for (User us : userList){
            if ((us.getLogin().equals(user)) && (us.getPassword().equals(password)))
                return true;
        }
        return false;
    }
    public void registerUser(User us){
        Function fc = new Function();
        try{
            // Implement
            fc.executeQuery("insert into users values (default, "
                    + us.getFirstName() + ", "
                    + us.getLastName() + ", "
                    + us.getLogin() + ", "
                    + us.getPassword() + ", "
                    + ");");
        } catch (Exception e) {System.out.println(e);}
    }
}
