package DAO;

import entities.Notification;
import java.util.List;

public interface NotificationDAO {

    void add(Notification notification);

    List<Notification> getAll();

    Notification getById(Long sendId);

    void update(Notification notification);

    void remove(Notification notification);
}
