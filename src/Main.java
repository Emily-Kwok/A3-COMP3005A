import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;

import java.sql.SQLException;

public class Main {
    static String[] init(){
        String[] connection_metadata = new String[3];
        String URL = "jdbc:postgresql://localhost:";
        Scanner input = new Scanner(System.in);

        try{
            System.out.print("Enter PORT: ");
            String PORT = input.nextLine();
            System.out.print("Enter USER: ");
            String USER = input.nextLine();
            System.out.print("Enter PASSWORD: ");
            String PWD = input.nextLine();
            System.out.print("Enter DATABASE: ");
            String DB = input.nextLine();

            URL += PORT + "/" + DB;

            connection_metadata[0] = URL;
            connection_metadata[1] = USER;
            connection_metadata[2] = PWD;
        }
        catch(Exception e){
            System.out.println("\nERROR occurred in init().");
            e.printStackTrace();
        }

        return connection_metadata;
    }

    static int menu(){
        Scanner input = new Scanner(System.in);
        int opt = -1;

        try{
            boolean flag = true;
            while(flag){
                System.out.println("\n-----[MAIN MENU]-----");
                System.out.println("Enter an integer corresponding to desired action.");
                System.out.println("[1] Get All Students");
                System.out.println("[2] Add Student");
                System.out.println("[3] Update Student Email");
                System.out.println("[4] Delete Student");
                System.out.println("[0] Exit");
                System.out.println("---------------------");
                System.out.print(": ");
                if(input.hasNextInt()) {
                    opt = input.nextInt();
                    if( opt <= 4 && opt >= 0 ) flag = false;
                    else System.out.println("\nERROR. Integer not found. Try again.");
                }
                else{
                    System.out.println("\nERROR. Input is not an integer. Try again.");
                    input.next();
                }
            }
            System.out.println("");
        }
        catch(Exception e){
            System.out.println("\nERROR occurred in menu().");
            e.printStackTrace();
        }

        return opt;
    }

    static void getAllStudents(Connection c){     // Retrieves and displays all records from the students table.
        String[] labels = {"ID", "First Name", "Last Name", "Email", "Enrollment Date"};
        String[] columns = {String.format("| %-3s |", labels[0]),
                String.format(" %-15s |", labels[1]),
                String.format(" %-15s |", labels[2]),
                String.format(" %-30s |", labels[3]),
                String.format(" %-20s |", labels[4])
        };

        try{
            Statement statement = c.createStatement();
            statement.executeQuery("SELECT * FROM students");

            ResultSet resultSet = statement.getResultSet();

            System.out.println(columns[0] + columns[1] + columns[2] + columns[3] + columns[4]);
            while(resultSet.next()){
                int id = resultSet.getInt("student_id");
                String fname = resultSet.getString("first_name");
                String lname = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                Date enroll_date = resultSet.getDate("enrollment_date");
                System.out.println(String.format("| %-3s |", id) +
                                   String.format(" %-15s |", fname) +
                                   String.format(" %-15s |", lname) +
                                   String.format(" %-30s |", email) +
                                   String.format(" %-20s |", enroll_date)
                );
            }
        }
        catch(SQLException s){
            System.out.println("\nSQL ERROR occurred in getAllStudents().");
            s.printStackTrace();
        }
        catch(Exception e){
            System.out.println("\nERROR occurred in getAllStudents().");
            e.printStackTrace();
        }
    }

    static boolean addStudent(String fname, String lname, String email, Date enrollment_date, Connection c){     // Inserts a new student record into the students table.
        try{
            Statement statement = c.createStatement();
            statement.executeUpdate("INSERT INTO students (first_name, last_name, email, enrollment_date) " +
                    "VALUES ('" + fname + "', '" + lname + "', '" + email + "', '" + enrollment_date + "')");

            return true;
        }
        catch(SQLException s){
            System.out.println("\nSQL ERROR occurred in getAllStudents().");
            s.printStackTrace();
        }
        catch(Exception e){
            System.out.println("\nERROR occurred in getAllStudents().");
            e.printStackTrace();
        }
        return false;
    }
    static boolean updateStudentEmail(int student_id, String new_email, Connection c){     // Updates the email address for a student with the specified student_id.
        return false;
    }
    static boolean deleteStudent(int student_id, Connection c){     // Deletes the record of the student with the specified student_id.
        return false;
    }
    public static void main(String[] args) {
//        String[] connection_metadata = init();
        String url = "jdbc:postgresql://localhost:5432/assignment3";
        String user =  "postgres";
        String password = "password";

        try{
            Class.forName("org.postgresql.Driver");
//            Connection connection = DriverManager.getConnection(connection_metadata[0], connection_metadata[1], connection_metadata[2]);
            Connection connection = DriverManager.getConnection(url, user, password);

            while(true){
                int opt = menu();

                switch(opt) {
                    case 1:
                        getAllStudents(connection);
                        break;
                    case 2:
                        Scanner input = new Scanner(System.in);

                        System.out.println("NOTE: Enter date in YYYY-MM-DD format.\n");

                        System.out.print("Enter first name: ");
                        String fname = input.nextLine();
                        System.out.print("Enter last name: ");
                        String lname = input.nextLine();
                        System.out.print("Enter email: ");
                        String email = input.nextLine();
                        System.out.print("Enter enrollment date: ");
                        String date = input.nextLine();
                        Date enrollment_date = Date.valueOf(date);

                        if(addStudent(fname, lname, email, enrollment_date, connection)){
                            System.out.println("SUCCESS! Student is added.");
                        }
                        else{
                            System.out.println("ERROR. Student was NOT added.");
                        }
                        break;
                    case 3:
//                        updateStudentEmail(id, new_email, c);
                        break;
                    case 4:
//                        deleteStudent(id, c);
                        break;
                    default:
                        System.out.println("\nGood Bye!");
                        System.exit(0);
                }
            }

            // EXAMPLE - PLACEHOLDER
//            Statement statement = connection.createStatement();
//            statement.executeQuery("SELECT * FROM students");
//            ResultSet resultSet = statement.getResultSet();
//            while(resultSet.next()){
//                System.out.println(resultSet.getString("first_name"));
//            }
        }
        catch(Exception e){
            System.out.println("ERROR occurred in main().");
            e.printStackTrace();
        }
    }
}