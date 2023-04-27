package com.example.springboot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Database {

    

    public Appointment[] getAppointments() {

        return null;
    }

    public Service[] getServices() {
        DataSource dataSource = null;
        Connection connection = null;
        ArrayList<Service> serviceList = new ArrayList<Service>();

        try {
           dataSource = DataSourceConfig.source();
           connection = dataSource.getConnection();
 
            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(
                "select * from services");
            int code;
            String title;
            String available;
            while (resultSet.next()) {
                code = resultSet.getInt("idServices");
                title = resultSet.getString("NameOfService").trim();
                available = resultSet.getString("AvailableToBook").trim();
                Service service = new Service(code, title, available);
                serviceList.add(service);
                System.out.println("idServices : " + code
                                   + " NameOfService : " + title);
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
        return serviceList.toArray(new Service[0]);
    }

    public Service[] setAppointment(Appointment appointment) {
        DataSource dataSource = null;
        Connection connection = null;
        PreparedStatement statement;

        try {
           dataSource = DataSourceConfig.source();
           connection = dataSource.getConnection();

            statement = connection.prepareStatement("insert into appointments(Name, Date, Services_idServices) values (?,?,?)");
            statement.setString(1,appointment.getName());
            statement.setString(2,appointment.getDate());
            statement.setInt(3,appointment.getService());
            
            //missing something to execute statement
            statement.executeUpdate();
            statement.close();
            connection.close();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
        return null;
    }

    public boolean availableAppointmentTime(Appointment appointment) {
        
        DataSource dataSource = null;
        Connection connection = null;
        boolean available = true;

        try {
           dataSource = DataSourceConfig.source();
           connection = dataSource.getConnection();
 
            PreparedStatement statement;
            statement = connection.prepareStatement("select * from appointments where Date = ?");
            
            statement.setString(1, appointment.getDate());

            ResultSet resultSet;
            resultSet = statement.executeQuery();
            
            if(resultSet.next()) {
                available = false;
            } else {
                available = true;
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }

        return available;
    }
    
}
