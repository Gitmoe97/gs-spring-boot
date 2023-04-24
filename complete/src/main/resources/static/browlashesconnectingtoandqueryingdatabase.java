import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


public class DisplayAppointments {
    public static void main(String args[]) {
        final String DATABASE_URL = "appointments";
        final String SELECT_QUERY =
            "SELECT appointmentID, firstName, lastName FROM appointments";

        try {
            Connection connection = DriverManager.getConnection(
                DATABASE_URL, "root","Hantash97");
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_QUERY); {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();

            System.out.printf("Appointments Table of Appointments Database:%n%n");

            for (int i = 1; i <= numberOfColumns; i++) {
                System.out.printf("%-8s\t", metaData.getColumnName(i));
            }
            System.out.println();
            }

    }
    catch (SQLException sqlException) {
        sqlException.printStackTrace();
    }


}

}
