package Services;

import DAO.UserDAO;
import Util.DataBaseConnector;
import entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService extends DataBaseConnector implements UserDAO {

    private static String query;

    @Override
    public void add(User user) {
        query = "insert into mono.users values(?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, user.getUserID());
            preparedStatement.setString(2, user.getFullName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> usersList = new ArrayList<>();
        query = "select * from mono.users";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                User user = new User();
                user.setUserID(resultSet.getLong("id"));
                user.setFullName(resultSet.getString("fullName"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                usersList.add(user);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return usersList;
    }

    @Override
    public User getByID(Long userID) {
        query = "select * from mono.users where id = ?";
        User user = new User();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            user.setUserID(resultSet.getLong("id"));
            user.setFullName(resultSet.getString("fullName"));
            user.setEmail(resultSet.getString("email"));
            user.setPhoneNumber(resultSet.getString("phoneNumber"));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void update(User user) {
        query = "update mono.users set fullName = ?, email = ?, phoneNumber = ? where id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setLong(4, user.getUserID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(User user) {
        query = "delete from mono.users where id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, user.getUserID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
