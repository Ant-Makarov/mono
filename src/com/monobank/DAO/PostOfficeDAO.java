package com.monobank.DAO;

import com.monobank.entities.PostOffice;
import java.util.List;

public interface PostOfficeDAO {

    void add(PostOffice postOffice);

    List<PostOffice> getAll();

    PostOffice getById(Long postOfficeId);

    void update(PostOffice postOffice);

    void remove(PostOffice postOffice);


}
