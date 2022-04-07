package Services;

import DAO.NotificationDAO;
import Util.DataBaseConnector;
import entities.Notification;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationService extends DataBaseConnector implements NotificationDAO {

    private static String query;

    @Override
    public synchronized void add(Notification notification) {

        query = "insert into mono.notifications values (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, notification.getSendID());
            preparedStatement.setString(2, notification.getText());
            preparedStatement.setString(3, notification.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized List<Notification> getAll() {
        List<Notification> notificationsList = new ArrayList<>();
        query = "select * from mono.notifications";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Notification notification = new Notification();
                notification.setSendID(resultSet.getLong("sendID"));
                notification.setText(resultSet.getString("message"));
                notification.setStatus(resultSet.getString("status"));
                notificationsList.add(notification);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return notificationsList;
    }

    @Override
    public synchronized Notification getById(Long sendId) {
        query = "select * from mono.notifications where sendID = ?";
        Notification notification = new Notification();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, sendId);
            ResultSet resultSet = preparedStatement.executeQuery();
            notification.setSendID(resultSet.getLong("sendID"));
            notification.setText(resultSet.getString("message"));
            notification.setStatus(resultSet.getString("status"));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notification;
    }

    @Override
    public synchronized void update(Notification notification) {
        query = "update mono.notifications set message = ?, status = ? where sendID = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {


            preparedStatement.setString(1, notification.getText());
            preparedStatement.setString(2, notification.getStatus());
            preparedStatement.setLong(3, notification.getSendID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void remove(Notification notification) {
        query = "delete from mono.notifications where sendID = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, notification.getSendID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
