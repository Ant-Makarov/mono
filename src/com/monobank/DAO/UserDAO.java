package DAO;

import entities.User;
import java.util.List;

public interface UserDAO {

    void add(User user);

    List<User> getAll();

    User getByID(Long userID);

    void update(User user);

    void remove(User user);
}
