package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DatabaseFunctions {
    public static boolean login (Connection c, String user, String pass) {
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
            return false;
        }
        return false;
    }

    public static boolean addUser(Connection c, String username, String name, String address, String phoneNumber, String cardNumber, String password, String type, int borrow, double fine){
        PreparedStatement preparedStatement;
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
            if (u.username_.equals("")) return null;
            return u;
        } catch (Exception e){
            return null;
        }

    }

    public static boolean deleteUser(Connection c, String card_number){
        PreparedStatement preparedStatement;
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

    public static int userCount(Connection c) {
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

    public static void modCardNumber(Connection c, String cardNumber, String newest){}
    public static void modUsername(Connection c, String cardNumber, String newest){}
    public static void modName(Connection c, String cardNumber, String newest){}
    public static void modAddress(Connection c, String cardNumber, String newest){}
    public static void modPhoneNumber(Connection c, String cardNumber, String newest){}
    public static void modPassword(Connection c, String cardNumber, String newest){}
    public static void modType(Connection c, String cardNumber, String newest){}
    public static void modBorrow(Connection c, String cardNumber, int newest){}
    public static void modFine(Connection c, String cardNumber, double newest){}

    public static boolean addItem(Connection c, String itemID, String author, String title, String publisher, int edition, int year, boolean isBestSeller, double price, int numberCopies, int copiesAvailable, String type, int numberReferences){
        PreparedStatement preparedStatement;
        String query = "insert into documents (itemID, author, title, publisher, edition, year, is_best_seller, price, number_of_copies, copies_available, type, number_of_references)" + " values (?, ?, ?, ?, ?, ?, ? , ?, ?, ?, ?, ?)";
        try{
            preparedStatement = c.prepareStatement(query);
            preparedStatement.setString(1, itemID);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, title);
            preparedStatement.setString(4, publisher);
            preparedStatement.setInt(5, edition);
            preparedStatement.setInt(6, year);
            preparedStatement.setBoolean(7, isBestSeller);
            preparedStatement.setDouble(8, price);
            preparedStatement.setInt(9, numberCopies);
            preparedStatement.setInt(10, copiesAvailable);
            preparedStatement.setString(11, type);
            preparedStatement.setInt(12, numberReferences);
            preparedStatement.executeUpdate();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static Item_Storer getItem(Connection c, String itemID) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM documents where itemID = ?";
        try {
            preparedStatement = c.prepareStatement(query);
            preparedStatement.setString(1, itemID);
            resultSet = preparedStatement.executeQuery();
            Item_Storer it = new Item_Storer("","","","",0, 0, false, 0, 0, 0, "", 0);
            while(resultSet.next()){
                it.itemID = resultSet.getString("itemID");
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
                it.numberReferences = resultSet.getInt("number_of_references");
            }
            if (it.itemID.equals("")) return null;
            return it;
        } catch (Exception e){
            return null;
        }
    }

    public static boolean deleteItem(Connection c, String itemID){
        PreparedStatement preparedStatement;
        String query1 = "delete from documents where itemID = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setString(1, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static void modDocItemID(Connection c, String itemID, String newest){}
    public static void modDocAuthor(Connection c, String itemID, String newest){}
    public static void modDocTitle(Connection c, String itemID, String newest){}
    public static void modDocPublisher(Connection c, String itemID, String newest){}
    public static void modDocEdition(Connection c, String itemID, int newest){}
    public static void modDocYear(Connection c, String itemID, int newest){}
    public static void modDocBestSeller(Connection c, String itemID, boolean newest){}
    public static void modDocPrice(Connection c, String itemID, double newest){}
    public static void modDocNumberOfCopies(Connection c, String itemID, int newest){}
    public static void modDocCopiesAvailable(Connection c, String itemID, int newest){}
    public static void modDocType(Connection c, String itemID, String newest){}
    public static void modDocNumberReferences(Connection c, String itemID, int newest){}

    public static boolean addCheckOut(Connection c, String cardNumber, String itemID, String releaseDate, String deadline, int renew){
        PreparedStatement preparedStatement;
        String query = "insert into checkout (user_id, item_id, release_date, deadline, renew)" + " values (?,?,?,?,?)";
        try{
            preparedStatement = c.prepareStatement(query);
            preparedStatement.setString(1, cardNumber);
            preparedStatement.setString(2, itemID);
            preparedStatement.setString(3, releaseDate);
            preparedStatement.setString(4, deadline);
            preparedStatement.setInt(5, renew);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
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
            Checkout ch = new Checkout("","","","", 0);
            while(resultSet.next()){
                ch.userID = resultSet.getString("user_id");
                ch.itemID = resultSet.getString("item_id");
                ch.releaseDate = resultSet.getString("release_date");
                ch.deadline = resultSet.getString("deadline");
                ch.renew = resultSet.getInt("renew");
            }
            if (ch.userID.equals("")) return null;
            return ch;
        } catch (Exception e){
            return null;
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

    public static ArrayList<String> getCheckoutList(Connection c, String card_number) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM checkout where user_id = ?";
        ArrayList<String> itemIDs = new ArrayList<>();
        try {
            preparedStatement = c.prepareStatement(query);
            preparedStatement.setString(1, card_number);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                itemIDs.add(resultSet.getString("item_id"));
            }
            return itemIDs;
        } catch (Exception e){
            return itemIDs;
        }
    }
}
