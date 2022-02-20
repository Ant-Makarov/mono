package com.monobank.Services;

import com.monobank.DAO.NotificationDAO;
import com.monobank.Util.DataBaseConnector;
import com.monobank.entities.Notification;
import com.monobank.entities.PostOffice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationService extends DataBaseConnector implements NotificationDAO {

    private static String query;

    @Override
    public void add(Notification notification) {

        query = "insert into notifications values (?, ?, ?)";

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
    public List<Notification> getAll() {
        List<Notification> notificationsList = new ArrayList<>();
        query = "select * from notifications";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Notification notification = new Notification();
                notification.setSendID(resultSet.getLong("sendID"));
                notification.setStatus(resultSet.getString("message"));
                notification.setStatus(resultSet.getString("status"));
                notificationsList.add(notification);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return notificationsList;
    }

    @Override
    public Notification getById(Long sendId) {
        query = "select * from notifications where sendID = ?";
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
    public void update(Notification notification) {
        query = "update notifications set text = ?, status = ?, where id = ?";

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
    public void remove(Notification notification) {
        query = "delete from notifications where id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, notification.getSendID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
