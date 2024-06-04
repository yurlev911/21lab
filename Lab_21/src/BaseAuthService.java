import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseAuthService implements AuthService {
    final static String connectionUrl = "jdbc:sqlite:sql\\sql.db";
    private static Connection connection;
    private static Statement stmt;
    @Override
    public void start() {
        System.out.println("Сервис аутентификации запущен");
    }
    @Override
    public void stop(){
        System.out.println("Сервис аутентификации остановлен");
    }
    public void connect() throws SQLException, ClassNotFoundException{
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:sql\\sql.db");
        stmt = connection.createStatement();
    }
    public void closeConnection(){
        try{
            if (stmt !=null){
                stmt.close();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        try{
            if(connection !=null){
                connection.close();
            }
        }
        catch (SQLException esql){
            esql.printStackTrace();
        }
    }
    @Override
    public String getNickByLoginPassDB(String login, String pass) throws SQLException, ClassNotFoundException{
        connect();
        String query = "SELECT * FROM users";
        String nickReturn = null;
        ResultSet rs= stmt.executeQuery(query);
        while (rs.next()) {
            if(rs.getString("login").equals(login) && rs.getString("pass").equals(pass)){
                nickReturn = rs.getString("nick");
                System.out.println(rs.getString("nick"));
            }
        }
        closeConnection();
        return nickReturn;
    }
}
