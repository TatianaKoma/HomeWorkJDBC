package com.example.homeworkjdbc;

import java.sql.SQLException;
import java.util.List;

public interface AddressDAO {

    void addAddress(Address address) throws SQLException;

    void deleteAddressById(int id) throws SQLException;

    Address getAddressById(int id) throws SQLException;

    List<Address> getAllAddresses() throws SQLException;

    void updateAddressById(int id, Address address) throws SQLException;
}
