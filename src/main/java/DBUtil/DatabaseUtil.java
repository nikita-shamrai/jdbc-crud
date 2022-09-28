package DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

public class DatabaseUtil {

    public Connection getConnection(){
        try {
            return DriverManager.getConnection(JdbcConst.URL.getValue(), JdbcConst.USERNAME.getValue(), JdbcConst.PASSWORD.getValue());
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            throw new RuntimeException(e);
        }
    }

    public String showAllClause(String tableName){
        return "SELECT * FROM " + tableName;
    }

    public String showRowByIdClause(String tableName, int id){
        return "SELECT * FROM " + tableName + " WHERE id="+id;
    }

    public String createRowClause(String tableName, String name, String country, String city, String regDate) {
        return "INSERT INTO " + tableName + " (Name, Country, City, Registration_date) VALUES('" + name + "', '"
                + country + "', '" + city + "', '" + regDate+"')";
    }

    public String updateRowClause(String tableName) {
        return "UPDATE " + tableName + " SET Name=?, Country=?, City=?, Registration_date=? WHERE id=?";
    }

    public String deleteRowClause(String tableName) {
        return "DELETE FROM "+ tableName + " WHERE id=?";
    }

}
