package com.monobank.Services;

import com.monobank.DAO.ParcelSendDAO;
import com.monobank.Util.DataBaseConnector;
import com.monobank.entities.ParcelSend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
            preparedStatement.setString(5, parcelSend.getReceiverPhoneNumber());
            preparedStatement.setString(6, parcelSend.getReceiverFullName());
            preparedStatement.setString(7, parcelSend.getSendStatus());
            preparedStatement.setDate(8, parcelSend.getCreationDate());
            preparedStatement.setDate(9, parcelSend.getChangeDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ParcelSend> getAll() {
        return null;
    }

    @Override
    public ParcelSend getById(Long sendId) {
        return null;
    }

    @Override
    public void update(ParcelSend parcelSend) {

    }

    @Override
    public void remove(ParcelSend parcelSend) {

    }

    @Override
    public Connection getConnection() {
        return super.getConnection();
    }
}
