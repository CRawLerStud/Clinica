package com.example.clinica.repository;

import com.example.clinica.models.Medic;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MedicRepository {

    private final String url;
    private final String username;
    private final String password;

    public MedicRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Iterable<Medic> findAllInSectie(Long idSectie){

        List<Medic> medici = new ArrayList<>();
        Connection connection = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM medici WHERE id_sectie = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, idSectie);

            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Long ID = resultSet.getLong("id");
                Long id_sectie = resultSet.getLong("id_sectie");
                String nume = resultSet.getString("nume");
                String prenume = resultSet.getString("prenume");
                boolean rezident = resultSet.getBoolean("rezident");
                Integer vechime = resultSet.getInt("vechime");

                medici.add(new Medic(ID, id_sectie, nume, prenume, rezident, vechime));
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            DBUtils.closeResultSet(resultSet);
            DBUtils.closeConnection(connection);
        }

        return medici;

    }

    public Iterable<Medic> findAll() {
        Set<Medic> allMedics = new HashSet<>();
        Connection connection = null;
        ResultSet resultSet = null;

        try{

            connection = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM medici";
            PreparedStatement statement = connection.prepareStatement(query);

            resultSet = statement.executeQuery();

            while(resultSet.next()){
                Long ID = resultSet.getLong("id");
                Long id_sectie = resultSet.getLong("id_sectie");
                String nume = resultSet.getString("nume");
                String prenume = resultSet.getString("prenume");
                boolean rezident = resultSet.getBoolean("rezident");
                Integer vechime = resultSet.getInt("vechime");

                allMedics.add(new Medic(ID, id_sectie, nume, prenume, rezident, vechime));
            }

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            DBUtils.closeResultSet(resultSet);
            DBUtils.closeConnection(connection);
        }

        return allMedics;
    }
}
