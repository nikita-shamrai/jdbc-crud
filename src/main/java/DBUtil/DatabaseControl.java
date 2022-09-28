package DBUtil;

import Domain.Client;
import java.sql.*;
import java.text.Format;
import java.text.SimpleDateFormat;

public class DatabaseControl {

    private final DatabaseUtil db = new DatabaseUtil();

    public void showAllDB(){
        try(Connection connection = db.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(db.showAllClause("Clients"));
            ResultSet resultSet = preparedStatement.executeQuery();
            showResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showRowById(int id) {
            try (Connection connection = db.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(db.showRowByIdClause("Clients", id));
                ResultSet resultSet = preparedStatement.executeQuery();
                showResultSet(resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    public boolean checkIfIdIsPresent(int id){
       try(Connection connection = db.getConnection();){
           PreparedStatement preparedStatement = connection.prepareStatement(db.showRowByIdClause("Clients", id));
           ResultSet resultSet = preparedStatement.executeQuery();
           return (resultSet.next());
       } catch (SQLException e) {
           System.out.println("Exception in CheckIfPresentMethod");
           throw new RuntimeException(e);
       }
    }

    public void createRowInDB(Client client){
        try(Connection connection = db.getConnection()){
            String clause = db.createRowClause("Clients",
                            client.getName(),
                            client.getCountry(),
                            client.getCity(),
                            new SimpleDateFormat("yyyy-MM-dd").format(client.getRegistrationDate()));
            PreparedStatement preparedStatement = connection.prepareStatement(clause);
            int update = preparedStatement.executeUpdate();
            if (update > 0){
                System.out.println("Row added successfully");
            } else {
                System.out.println("Error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };

    public void updateRowInDB(int id, Client client){
            try (Connection connection = db.getConnection()) {
                Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                PreparedStatement preparedStatement = connection.prepareStatement(db.updateRowClause("Clients"));
                preparedStatement.setString(1, client.getName());
                preparedStatement.setString(2, client.getCountry());
                preparedStatement.setString(3, client.getCity());
                preparedStatement.setString(4, formatter.format(client.getRegistrationDate()));
                preparedStatement.setInt(5, id);

                int executeUpdate = preparedStatement.executeUpdate();
                if (executeUpdate > 0) {
                    System.out.println("Row updated successfully");
                } else {
                    System.out.println("Error.");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

    }

    public void deleteRowById(int id){
        try(Connection connection = db.getConnection();){
        PreparedStatement preparedStatement = connection.prepareStatement(db.deleteRowClause("Clients"));
        preparedStatement.setInt(1, id);
            int executeUpdate = preparedStatement.executeUpdate();
        if (executeUpdate > 0){
            System.out.println("Row deleted successfully.");
        } else {
            System.out.println("Deleting failed.");
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            System.out.print(resultSet.getInt(1) + " : "
                    + resultSet.getString(2) + " | "
                    + resultSet.getString(3) + " | "
                    + resultSet.getString(4) + " | "
                    + resultSet.getString(5) + "\n");
            System.out.println("_____________________");
        }
    }

}
