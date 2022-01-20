import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connection {
    private static final String url = "jdbc:mysql://localhost";
    private static final String databaseName = "HY359";
    private static int port = 3308;
    private static final String username = "root";
    private static final String password = "";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url+":"+port+"/"+databaseName, username, password);
        
    }

    public static Connection getInitialConnection() throws SQLException,
    ClassNotFoundException {
        return DriverManager.getConnection(url+":"+port, username, password);
    }
}