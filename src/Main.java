import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:<PORT>/<YOUR_DATABASE>";
        String user =  "postgres";
        String password = "password";

        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            // EXAMPLE - PLACEHOLDER
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM students");
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()){
                System.out.print(resultSet.getString("id") + "\t");
                System.out.print(resultSet.getString("name") + "\t");
                System.out.println(resultSet.getInt("tot_cred"));
            }
        }
        catch(Exception e){
//            System.out.println(e);    // won't error but does not print all info; there are hidden info
            e.printStackTrace();
        }
    }
}

// this text is to test re-commit; to see if it will update