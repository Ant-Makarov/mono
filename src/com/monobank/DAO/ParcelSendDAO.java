package DAO;

import entities.ParcelSend;
import java.util.List;

public interface ParcelSendDAO {

    void add(ParcelSend parcelSend);

    List<ParcelSend> getAll();

    ParcelSend getById(Long sendId);

    void update(ParcelSend parcelSend);

    void remove(ParcelSend parcelSend);
}
