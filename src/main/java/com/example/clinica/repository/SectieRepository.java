package com.example.clinica.repository;

import com.example.clinica.models.Sectie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SectieRepository {

    private final String url;
    private final String username;
    private final String password;

    public SectieRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Sectie findOne(Long ID) throws RepositoryException{
        Sectie sectie = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM sectii WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, ID);

            resultSet = statement.executeQuery();
            if(resultSet.next()){
                String nume = resultSet.getString("nume");
                Integer pret = resultSet.getInt("pret_per_consultatie");
                Integer durata = resultSet.getInt("durata_maxima_consultatie");
                Long id_sef_sectie = resultSet.getLong("id_sef_sectie");

                sectie = new Sectie(ID, nume,  id_sef_sectie, pret, durata);
            }

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            DBUtils.closeResultSet(resultSet);
            DBUtils.closeConnection(connection);
        }

        if(sectie == null)
            throw new RepositoryException("Sectia nu exista!");

        return sectie;
    }

    public Iterable<Sectie> findAll() {
        List<Sectie> sectii = new ArrayList<>();
        Connection connection = null;
        ResultSet resultSet = null;

        try{

            connection = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM sectii";
            PreparedStatement statement = connection.prepareStatement(query);

            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Long ID = resultSet.getLong("id");
                String nume = resultSet.getString("nume");
                Integer pret_per_consultatie = resultSet.getInt("pret_per_consultatie");
                Integer durata_maxima_consultatie = resultSet.getInt("durata_maxima_consultatie");
                Long id_sef_sectie = resultSet.getLong("id_sef_sectie");

                sectii.add(new Sectie(ID, nume, id_sef_sectie, pret_per_consultatie, durata_maxima_consultatie));
            }

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        finally{
            DBUtils.closeResultSet(resultSet);
            DBUtils.closeConnection(connection);
        }

        return sectii;
    }
}
