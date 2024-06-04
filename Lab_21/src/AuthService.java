import java.sql.SQLException;

public interface AuthService {
    void start();
    String getNickByLoginPassDB(String login, String pass) throws SQLException, ClassNotFoundException;
    void stop();
}
