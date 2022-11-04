package com.example.homeworkjdbc;

import com.example.homeworkjdbc.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CitizenDAOImpl implements CitizenDAO {
    @Override
    public void addCitizen(Citizen citizen) throws SQLException {
        String sql = "INSERT INTO citizen(name, surname, phone, address_id)"
                + "VALUES(?, ?, ?, ?)";
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, citizen.getName());
            preparedStatement.setString(2, citizen.getSurname());
            preparedStatement.setString(3, citizen.getPhone());
            preparedStatement.setInt(4, citizen.getAddressId());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                citizen.setId(generatedKeys.getInt(1));
            }
        }
    }

    @Override
    public void deleteCitizenById(int id) throws SQLException {
        String sql = "DELETE FROM citizen WHERE id = ?";

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Citizen getCitizenById(int id) throws SQLException {
        String sql = "SELECT * FROM citizen WHERE id = ?";
        Citizen citizen = new Citizen();
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                citizen.setId(resultSet.getInt(1));
                citizen.setName(resultSet.getString(2));
                citizen.setSurname(resultSet.getString(3));
                citizen.setPhone(resultSet.getString(4));
                citizen.setAddressId(resultSet.getInt(5));
            }
        }
        return citizen;
    }

    @Override
    public List<Citizen> getCitizensByAddressId(int addressId) throws SQLException {
        String sql = "SELECT * FROM citizen "
                + "WHERE address_id = ?";
        List<Citizen> citizens = new ArrayList<>();
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, addressId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Citizen citizen = new Citizen();
                citizen.setId(resultSet.getInt("id"));
                citizen.setName(resultSet.getString("name"));
                citizen.setSurname(resultSet.getString("surname"));
                citizen.setPhone(resultSet.getString("phone"));
                citizen.setAddressId(resultSet.getInt("address_id"));
                citizens.add(citizen);
            }
        }
        return citizens;
    }

    @Override
    public void updateCitizenById(int id, Citizen citizen) throws SQLException {
        String sql = "UPDATE citizen SET name =?, surname =?, phone =?, address_id =?"
                + "WHERE id = ?";

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, citizen.getName());
            preparedStatement.setString(2, citizen.getSurname());
            preparedStatement.setString(3, citizen.getPhone());
            preparedStatement.setInt(4, citizen.getAddressId());
            preparedStatement.setInt(5, id);

            preparedStatement.executeUpdate();
        }
    }
}
