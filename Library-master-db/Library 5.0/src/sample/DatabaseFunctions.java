package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseFunctions {
    public static boolean login (Connection c, String user, String pass) {
        // check if user with login info in database
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM users where username = ? and password = ?";
        try {
            preparedStatement = c.prepareStatement(query);

            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return true;
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

        return false;
    }

    public static boolean register(Connection c, String username, String name, String address,String phoneNumber, String password){
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "insert into users (username, name, address, phone_number, card_number, password)" + " values (?, ?, ?, ?, ?, ?)";

        StringBuilder cardNum = new StringBuilder("L");
        String size = ""+userCount(c);

        for (int i = 0; i < (8-size.length()); i++){
            cardNum.append("0");
        }

        try {
            preparedStatement = c.prepareStatement(query);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setString(5, cardNum.toString());
            preparedStatement.setString(6, password);

            System.out.println("lala");

            preparedStatement.executeUpdate();

            System.out.println("baba");

            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static int userCount (Connection c) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            String query = "SELECT COUNT (id) FROM users";

            preparedStatement = c.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int a = resultSet.getInt(1);
                return a;
            }
            resultSet.next();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }

        return 0;
    }
}
