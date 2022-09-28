package DBUtil;

import java.sql.*;

public class InitDB implements Initialization{
    @Override
    public void init() {
        //Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connect = DriverManager.getConnection(JdbcConst.URL.getValue(), JdbcConst.USERNAME.getValue(), JdbcConst.PASSWORD.getValue());
             Statement statement = connect.createStatement()){
            statement.executeUpdate("DROP TABLE IF EXISTS Clients");
            statement.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS Clients
                    (id SMALLINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    Name VARCHAR(30) NOT NULL,
                    Country VARCHAR(30) NOT NULL,
                    City VARCHAR(30) NOT NULL,
                    Registration_date DATE NOT NULL);""");

            System.out.println("Table created.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void fill() {
        try (Connection connect = DriverManager.getConnection(JdbcConst.URL.getValue(), JdbcConst.USERNAME.getValue(), JdbcConst.PASSWORD.getValue());
             Statement statement = connect.createStatement()){
            statement.executeUpdate("""
                    INSERT INTO Clients (Name, Country, City, Registration_date)
                    VALUES ('Петро', 'Украина', 'Киев', '2021-01-05'),
                           ('Тарас', 'Украина', 'Киев', '2020-02-03'),
                           ('Иван', 'Беларусь', 'Гомель', '2019-12-04'),
                           ('Владимир', 'Украина', 'Одесса', '2021-12-04'),
                           ('Арсений', 'Украина', 'Львов', '2018-12-04'),
                           ('Степан', 'Украина', 'Херсон', '2020-11-05'),
                           ('Максим', 'Беларусь', 'Гродно', '2018-09-04'),
                           ('Роман', 'Украина', 'Одесса', '2019-12-04'),
                           ('Дмитрий', 'Украина', 'Киев', '2017-04-02');
                    """);

            System.out.println("Table filled with data.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
