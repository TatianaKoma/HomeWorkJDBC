package com.example.homeworkjdbc;

import com.example.homeworkjdbc.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressDAOImpl implements AddressDAO {
    @Override
    public void addAddress(Address address) throws SQLException {
        String sql = "INSERT INTO address(postal_code, country, city, address_line)"
                + "VALUES(?, ?, ?, ?)";
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, address.getPostalCode());
            preparedStatement.setString(2, address.getCountry());
            preparedStatement.setString(3, address.getCity());
            preparedStatement.setString(4, address.getAddressLine());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                address.setId(generatedKeys.getInt(1));
            }
        }
    }

    @Override
    public void deleteAddressById(int id) throws SQLException {
        String sql = "DELETE FROM address WHERE id = ?";

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Address getAddressById(int id) throws SQLException {
        List<Address> addresses = getAllAddresses();
        for (Address address : addresses) {
            if (address.getId() == id) {
                return address;
            }
        }
        return null;
    }

    @Override
    public List<Address> getAllAddresses() throws SQLException {
        String sql = "SELECT * FROM address";
        List<Address> addresses = new ArrayList<>();
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Address address = new Address();
                address.setId(resultSet.getInt("id"));
                address.setPostalCode(resultSet.getString("postal_code"));
                address.setCountry(resultSet.getString("country"));
                address.setCity(resultSet.getString("city"));
                address.setAddressLine(resultSet.getString("address_line"));
                addresses.add(address);
            }
        }
        return addresses;
    }

    @Override
    public void updateAddressById(int id, Address address) throws SQLException {
        String sql = "UPDATE address SET postal_code =?, country =?, city =?, address_line =?"
                + "WHERE id =?";
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, address.getPostalCode());
            preparedStatement.setString(2, address.getCountry());
            preparedStatement.setString(3, address.getCity());
            preparedStatement.setString(4, address.getAddressLine());
            preparedStatement.setInt(5, id);

            preparedStatement.executeUpdate();
        }
    }
}
