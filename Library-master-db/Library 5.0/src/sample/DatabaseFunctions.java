package sample;

import sample.images.Checkout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
//            System.out.println(e.getMessage());
            return false;
        }

        return false;
    }

    public static boolean register(Connection c, String username, String name, String address,String phoneNumber, String cardNumber, String password, String type, int borrow, double fine){
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String query = "insert into users (card_number, username, name, address, phone_number, password, type, borrow, fine)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = c.prepareStatement(query);

            preparedStatement.setString(1, cardNumber);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, phoneNumber);
            preparedStatement.setString(6, password);
            preparedStatement.setString(7, type);
            preparedStatement.setInt(8, borrow);
            preparedStatement.setDouble(9, fine);

            preparedStatement.executeUpdate();

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

    public static boolean addItem(Connection c, String itemID, String author, String title, String publisher, int edition, int year, boolean isBestSeller, double price, int numberCopies, int copiesAvailable, String type){
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "insert into books (item_id, author, title, publisher, edition, year, is_best_seller, price, number_of_copies, copies_available, type)" + " values (?,?,?,?,?,?,?,?,?,?,?)";

        try{
            preparedStatement = c.prepareStatement(query);

            preparedStatement.setString(1, itemID);
            //System.out.println(author);
            preparedStatement.setString(2, author);
            //System.out.println(title);
            preparedStatement.setString(3, title);
            //System.out.println(numberOfCopies);
            preparedStatement.setString(4, publisher);
            //System.out.println(price);
            preparedStatement.setInt(5, edition);
            preparedStatement.setInt(6, year);
            //System.out.println(author);
            preparedStatement.setBoolean(7, isBestSeller);
            //System.out.println(title);
            preparedStatement.setDouble(8, price);
            //System.out.println(numberOfCopies);
            preparedStatement.setInt(9, numberCopies);
            //System.out.println(price);
            preparedStatement.setInt(10, copiesAvailable);
            preparedStatement.setString(11, type);
            //System.out.println(bestSeller);
//            preparedStatement.setInt(6,userId);


            preparedStatement.execute();
            return true;

        }catch (Exception e){
            //System.out.println(e.getMessage());
            return false;
        }

    }

    public static boolean deleteUser(Connection c, String card_number){
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String query1 = "delete from users where card_number = ?";

        try{
            preparedStatement = c.prepareStatement(query1);

            preparedStatement.setString(1, card_number);

            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static boolean deleteItem(Connection c, String itemID){
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String query1 = "delete from books where item_id = ?";

        try{
            preparedStatement = c.prepareStatement(query1);

            preparedStatement.setString(1, itemID);

            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static boolean deleteCheckout(Connection c, String card_number, String itemID){
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String query1 = "delete from checkout where user_id = ? and item_id = ?";

        try{
            preparedStatement = c.prepareStatement(query1);

            preparedStatement.setString(1, card_number);
            preparedStatement.setString(2, itemID);

            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static boolean addCheckOut(Connection c, String cardNumber, String itemID, String releaseDate, String deadline){
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "insert into checkout (user_id, item_id, release_date, deadline)" + " values (?,?,?,?)";

        try{
            preparedStatement = c.prepareStatement(query);

            preparedStatement.setString(1, cardNumber);
            //System.out.println(author);
            preparedStatement.setString(2, itemID);
            //System.out.println(title);
            preparedStatement.setString(3, releaseDate);
            //System.out.println(numberOfCopies);
            preparedStatement.setString(4, deadline);
            //System.out.println(price);


            preparedStatement.execute();
            return true;

        }catch (Exception e){
            //System.out.println(e.getMessage());
            return false;
        }

    }

    public static User getUser(Connection c, String card_number) {

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM users where card_number = ?";
        try {
            preparedStatement = c.prepareStatement(query);

            preparedStatement.setString(1, card_number);


            resultSet = preparedStatement.executeQuery();

            User u = new User("","","","","", "", "",0, 0);
            while(resultSet.next()){
                u.cardNumber_ = resultSet.getString("card_number");
                u.username_ = resultSet.getString("username");
                u.name_ = resultSet.getString("name");
                u.address_ = resultSet.getString("address");
                u.phoneNumber_= resultSet.getString("phone_number");
                u.password_ = resultSet.getString("password");
                u.type_ = resultSet.getString("type");
                u.borrow_ = resultSet.getInt("borrow");
                u.fine_ = resultSet.getDouble("fine");

            }

            return u;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    public static Checkout getCheckout(Connection c, String card_number, String itemID) {

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM checkout where user_id = ? and item_id = ?";
        try {
            preparedStatement = c.prepareStatement(query);

            preparedStatement.setString(1, card_number);
            preparedStatement.setString(2, itemID);


            resultSet = preparedStatement.executeQuery();

            Checkout ch = new Checkout("","","","");
            while(resultSet.next()){
                ch.userID = resultSet.getString("user_id");
                ch.itemID = resultSet.getString("item_id");
                ch.releaseDate = resultSet.getString("release_date");
                ch.deadline = resultSet.getString("deadline");
            }

            return ch;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    public static ArrayList<String> getCheckoutList(Connection c, String card_number) {

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM checkout where user_id = ?";
        try {
            preparedStatement = c.prepareStatement(query);

            preparedStatement.setString(1, card_number);

            resultSet = preparedStatement.executeQuery();
            ArrayList<String> itemIDs = new ArrayList<>();

            while(resultSet.next()) {
                itemIDs.add(resultSet.getString("item_id"));
            }
            return itemIDs;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    public static Item_Storer getItem(Connection c, String itemID) {

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM books where itemID = ?";
        try {
            preparedStatement = c.prepareStatement(query);

            preparedStatement.setString(1, itemID);


            resultSet = preparedStatement.executeQuery();

            Item_Storer it = new Item_Storer("","","","",0, 0, false, 0, 0, 0, "");
            while(resultSet.next()){
                it.itemID = resultSet.getString("item_id");
                it.author = resultSet.getString("author");
                it.title = resultSet.getString("title");
                it.publisher = resultSet.getString("publisher");
                it.edition= resultSet.getInt("edition");
                it.year = resultSet.getInt("year");
                it.isBestSeller = resultSet.getBoolean("is_best_seller");
                it.price = resultSet.getDouble("price");
                it.numberOfCopies = resultSet.getInt("number_of_copies");
                it.copiesAvailable = resultSet.getInt("copies_available");
                it.type = resultSet.getString("type");

            }

            return it;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

//    public static boolean addPatron(Connection c, String username, String name, String address, String number, String password, Boolean faculty){
//        PreparedStatement preparedStatement;
//        ResultSet resultSet;
//        String query = "insert into users (username, name, address, phone_number, is_best_seller) values (?,?,?,?,?)";
//
//        try{
//            preparedStatement = c.prepareStatement(query);
//
//            preparedStatement.setString(1, author);
//            System.out.println(author);
//            preparedStatement.setString(2, title);
//            System.out.println(title);
//            preparedStatement.setInt(3, numberOfCopies);
//            System.out.println(numberOfCopies);
//            preparedStatement.setInt(4, price);
//            System.out.println(price);
//            preparedStatement.setInt(5, bestSeller);
//            System.out.println(bestSeller);
////            preparedStatement.setInt(6,userId);
//
//
//            preparedStatement.execute();
//            return true;
//
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//            return false;
//        }

//    }

}
