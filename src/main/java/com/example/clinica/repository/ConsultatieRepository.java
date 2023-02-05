package com.example.clinica.repository;

import com.example.clinica.models.Consultatie;
import com.example.clinica.models.Medic;
import com.example.clinica.utils.observer.ConcreteObservable;
import com.example.clinica.utils.utils.ChangeEventType;
import com.example.clinica.utils.utils.ConsultatieEvent;
import com.example.clinica.validation.ValidationException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsultatieRepository extends ConcreteObservable<ConsultatieEvent> {

    private final String url;
    private final String username;
    private final String password;

    public ConsultatieRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Iterable<Consultatie> findAllForMedic(Medic medic){
        List<Consultatie> consultatieList = new ArrayList<>();
        Connection connection = null;
        ResultSet resultSet = null;

        try {

            connection = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM consultatii " +
                    "WHERE id_medic = ? AND \"data\" >= CURRENT_DATE AND ora > DATE_PART('hour', CURRENT_TIME) " +
                    "ORDER BY \"data\", ora";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, medic.getId());

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long ID = resultSet.getLong("id");
                Long CNP = resultSet.getLong("cnp_pacient");
                String nume = resultSet.getString("nume_pacient");
                LocalDate data = resultSet.getDate("data").toLocalDate();
                Integer ora = resultSet.getInt("ora");

                consultatieList.add(new Consultatie(ID, medic.getId(), CNP, nume, data, ora));
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            DBUtils.closeResultSet(resultSet);
            DBUtils.closeConnection(connection);
        }

        return consultatieList;

    }

    public Long save(Consultatie consultatie) throws ValidationException{
        Long ID = null;
        ResultSet resultSet = null;
        Connection connection = null;

        try{

            if(!isValidConsultationDate(consultatie))
                throw new ValidationException("Data si ora sunt invalide!(Exista deja o consultatie in acest interval)");

            connection = DriverManager.getConnection(url, username, password);

            String query = "INSERT INTO consultatii(id_medic, cnp_pacient, nume_pacient, \"data\", ora) values (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, consultatie.getIdMedic());
            statement.setLong(2, consultatie.getCNPPacient());
            statement.setString(3, consultatie.getNumePacient());
            statement.setDate(4, Date.valueOf(consultatie.getData()));
            statement.setInt(5, consultatie.getOra());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next())
                ID = resultSet.getLong(1);

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            if(resultSet != null)
                DBUtils.closeResultSet(resultSet);
            if(connection != null)
                DBUtils.closeConnection(connection);
        }

        this.notifyObserver(new ConsultatieEvent(consultatie, ChangeEventType.ADD));

        return ID;
    }

    private boolean isValidConsultationDate(Consultatie consultatie){
        Connection connection = null;
        ResultSet resultSet = null;
        boolean valid = false;

        try{

            connection = DriverManager.getConnection(url, username, password);

            String query = "SELECT COUNT(*) as counter FROM consultatii CONS " +
                    "INNER JOIN medici MED on MED.id = CONS.id_medic " +
                    "INNER JOIN sectii SECT on SECT.id = MED.id_sectie " +
                    "WHERE SECT.id = ? AND CONS.data = ? AND (CONS.ora - SECT.durata_maxima_consultatie) < ? AND (CONS.ora + SECT.durata_maxima_consultatie) > ?";

            PreparedStatement statement = connection.prepareStatement(query);

            Long sectieID = getMedicSectie(consultatie.getIdMedic());

            statement.setLong(1, sectieID);
            statement.setDate(2, Date.valueOf(consultatie.getData()));
            statement.setInt(3, consultatie.getOra());
            statement.setInt(4, consultatie.getOra());

            resultSet = statement.executeQuery();
            if(resultSet.next())
                if(resultSet.getLong("counter") == 0)
                    valid = true;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            DBUtils.closeResultSet(resultSet);
            DBUtils.closeConnection(connection);
        }

        return valid;

    }

    private Long getMedicSectie(Long medicID){
        Connection connection = null;
        ResultSet resultSet = null;
        Long sectieID = null;

        try{

            connection = DriverManager.getConnection(url, username, password);

            String query = "SELECT S.id as id FROM medici M " +
                    "INNER JOIN sectii S ON S.id = M.id_sectie " +
                    "WHERE M.id = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, medicID);

            resultSet = statement.executeQuery();
            if(resultSet.next()){
                sectieID = resultSet.getLong("id");
            }

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            DBUtils.closeResultSet(resultSet);
            DBUtils.closeConnection(connection);
        }

        return sectieID;
    }

}
