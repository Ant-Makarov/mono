package Services;

import DAO.ParcelSendDAO;
import Util.DataBaseConnector;
import entities.ParcelSend;
import entities.PostOffice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SendParcelService extends DataBaseConnector implements ParcelSendDAO {

    private static String query;

    @Override
    public void add(ParcelSend parcelSend) {
        query = "insert into parcel_sendings values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, parcelSend.getParcelSendID());
            preparedStatement.setLong(2, parcelSend.getSenderID());
            preparedStatement.setLong(3, parcelSend.getSenderPostOffice());
            preparedStatement.setLong(4, parcelSend.getReceiverPostOffice());
            preparedStatement.setString(5, parcelSend.getReceiverFullName());
            preparedStatement.setString(6, parcelSend.getReceiverPhoneNumber());
            preparedStatement.setString(7, parcelSend.getSendStatus());
            preparedStatement.setTimestamp(8, parcelSend.getCreationDate());
            preparedStatement.setTimestamp(9, parcelSend.getChangeDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ParcelSend> getAll() {
        List<ParcelSend> parcelSendList = new ArrayList<>();
        query = "select * from parcel_sendings";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                ParcelSend parcelSend = new ParcelSend();
                parcelSend.setParcelSendID(resultSet.getLong("sendID"));
                parcelSend.setSenderID(resultSet.getLong("senderID"));
                parcelSend.setSenderPostOffice(resultSet.getLong("sender_PO_ID"));
                parcelSend.setReceiverPostOffice(resultSet.getLong("receiver_PO_ID"));
                parcelSend.setReceiverPhoneNumber(resultSet.getString("receiver_FIO"));
                parcelSend.setReceiverFullName(resultSet.getString("receiverPhone"));
                parcelSend.setSendStatus("status");
                parcelSend.setCreationDate(resultSet.getTimestamp("creation_dateTime"));
                parcelSend.setChangeDate(resultSet.getTimestamp("statusChange_dateTime"));
                parcelSendList.add(parcelSend);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parcelSendList;
    }

    @Override
    public ParcelSend getById(Long sendId) {
        query = "select * from parcel_sendings where sendID = ?";
        ParcelSend parcelSend = new ParcelSend();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, sendId);
            ResultSet resultSet = preparedStatement.executeQuery();
            parcelSend.setParcelSendID(resultSet.getLong("sendID"));
            parcelSend.setSenderID(resultSet.getLong("senderID"));
            parcelSend.setSenderPostOffice(resultSet.getLong("sender_PO_ID"));
            parcelSend.setReceiverPostOffice(resultSet.getLong("receiver_PO_ID"));
            parcelSend.setReceiverPhoneNumber(resultSet.getString("receiver_FIO"));
            parcelSend.setReceiverFullName(resultSet.getString("receiverPhone"));
            parcelSend.setSendStatus("status");
            parcelSend.setCreationDate(resultSet.getTimestamp("creation_dateTime"));
            parcelSend.setChangeDate(resultSet.getTimestamp("statusChange_dateTime"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return parcelSend;
    }

    @Override
    public void update(ParcelSend parcelSend) {
        query = "update parcel_sendings set senderID = ?, sender_PO_ID = ?, receiver_PO_ID = ?," +
                " receiver_FIO = ?, receiverPhone = ?, status = ?, creation_dateTime = ?," +
                " statusChange_dateTime = ? where sendID = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, parcelSend.getSenderID());
            preparedStatement.setLong(2, parcelSend.getSenderPostOffice());
            preparedStatement.setLong(3, parcelSend.getReceiverPostOffice());
            preparedStatement.setString(4, parcelSend.getReceiverFullName());
            preparedStatement.setString(5, parcelSend.getReceiverPhoneNumber());
            preparedStatement.setString(6, parcelSend.getSendStatus());
            preparedStatement.setTimestamp(7, parcelSend.getCreationDate());
            preparedStatement.setTimestamp(8, parcelSend.getChangeDate());
            preparedStatement.setLong(9, parcelSend.getParcelSendID());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void remove(ParcelSend parcelSend) {
        query = "delete from parcel_sendings where sendID = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, parcelSend.getParcelSendID());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
