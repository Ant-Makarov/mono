package Services;

import DAO.PostOfficeDAO;
import Util.DataBaseConnector;
import entities.PostOffice;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostOfficeService extends DataBaseConnector implements PostOfficeDAO {

    private static String query;

    @Override
    public void add(PostOffice postOffice) {
        query = "insert into post_offices values(?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, postOffice.getPostOfficeID());
            preparedStatement.setString(2, postOffice.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PostOffice> getAll() {
        List<PostOffice> postOfficesList = new ArrayList<>();
        query = "select * from post_offices";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                PostOffice postOffice = new PostOffice();
                postOffice.setPostOfficeID(resultSet.getLong("post_office_id"));
                postOffice.setDescription(resultSet.getString("description"));
                postOfficesList.add(postOffice);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return postOfficesList;
    }

    @Override
    public PostOffice getById(Long postOfficeId) {
        query = "select * from post_offices where post_office_id = ?";
        PostOffice postOffice = new PostOffice();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, postOfficeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            postOffice.setPostOfficeID(resultSet.getLong("post_office_id"));
            postOffice.setDescription(resultSet.getString("description"));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postOffice;
    }

    @Override
    public void update(PostOffice postOffice) {
        query = "update postOffices set description = ? where post_office_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, postOffice.getDescription());
            preparedStatement.setLong(2, postOffice.getPostOfficeID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(PostOffice postOffice) {
        query = "delete from post_offices where id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, postOffice.getPostOfficeID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
