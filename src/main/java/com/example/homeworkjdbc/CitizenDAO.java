package com.example.homeworkjdbc;

import java.sql.SQLException;

import java.util.List;

public interface CitizenDAO {

    void addCitizen(Citizen citizen) throws SQLException;

    void deleteCitizenById(int id) throws SQLException;

    Citizen getCitizenById(int id) throws SQLException;

    List<Citizen> getCitizensByAddressId(int addressId) throws SQLException;

    void updateCitizenById(int id, Citizen citizen) throws SQLException;
}
