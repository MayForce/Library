package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.PriorityQueue;

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

    public static User getUserByUsername(Connection c, String username) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM users where username = ?";
        try {
            preparedStatement = c.prepareStatement(query);
            preparedStatement.setString(1, username);
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

    public static boolean modCardNumber(Connection c, String cardNumber, String newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE users SET card_number = ? WHERE card_number = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setString(1, newest);
            preparedStatement.setString(2, cardNumber);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modUsername(Connection c, String cardNumber, String newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE users SET username = ? WHERE card_number = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setString(1, newest);
            preparedStatement.setString(2, cardNumber);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modName(Connection c, String cardNumber, String newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE users SET name = ? WHERE card_number = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setString(1, newest);
            preparedStatement.setString(2, cardNumber);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modAddress(Connection c, String cardNumber, String newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE users SET address = ? WHERE card_number = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setString(1, newest);
            preparedStatement.setString(2, cardNumber);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modPhoneNumber(Connection c, String cardNumber, String newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE users SET phone_number = ? WHERE card_number = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setString(1, newest);
            preparedStatement.setString(2, cardNumber);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modPassword(Connection c, String cardNumber, String newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE users SET password = ? WHERE card_number = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setString(1, newest);
            preparedStatement.setString(2, cardNumber);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modType(Connection c, String cardNumber, String newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE users SET type = ? WHERE card_number = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setString(1, newest);
            preparedStatement.setString(2, cardNumber);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modBorrow(Connection c, String cardNumber, int newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE users SET borrow = ? WHERE card_number = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setInt(1, newest);
            preparedStatement.setString(2, cardNumber);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modFine(Connection c, String cardNumber, double newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE users SET fine = ? WHERE card_number = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setDouble(1, newest);
            preparedStatement.setString(2, cardNumber);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static boolean addItem(Connection c, String itemID, String author, String title, String publisher, int edition, int year, boolean isBestSeller, double price, int numberCopies, String type, int numberReferences){
        PreparedStatement preparedStatement;
        String query = "insert into documents (itemID, author, title, publisher, edition, year, is_best_seller, price, number_of_copies, copies_available, type, number_of_references, in_line1, in_line2, in_line3, in_line4, in_line5, turn1, turn2, turn3, turn4, turn5)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            preparedStatement.setInt(10, numberCopies-numberReferences);
            preparedStatement.setString(11, type);
            preparedStatement.setInt(12, numberReferences);
            preparedStatement.setInt(13, 0);
            preparedStatement.setInt(14, 0);
            preparedStatement.setInt(15, 0);
            preparedStatement.setInt(16, 0);
            preparedStatement.setInt(17, 0);
            preparedStatement.setInt(18, 1);
            preparedStatement.setInt(19, 1);
            preparedStatement.setInt(20, 1);
            preparedStatement.setInt(21, 1);
            preparedStatement.setInt(22, 1);
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
            Item_Storer it = new Item_Storer("","","","",0, 0, false, 0, 0, 0, "", 0, 0,0,0,0,0,0,0,0,0,0);
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
                it.inLine1 = resultSet.getInt("in_line1");
                it.inLine2 = resultSet.getInt("in_line2");
                it.inLine3 = resultSet.getInt("in_line3");
                it.inLine4 = resultSet.getInt("in_line4");
                it.inLine5 = resultSet.getInt("in_line5");
                it.turn1 = resultSet.getInt("turn1");
                it.turn2 = resultSet.getInt("turn2");
                it.turn3 = resultSet.getInt("turn3");
                it.turn4 = resultSet.getInt("turn4");
                it.turn5 = resultSet.getInt("turn5");
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

    public static boolean modDocItemID(Connection c, String itemID, String newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE documents SET itemID = ? WHERE itemID = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setString(1, newest);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modDocAuthor(Connection c, String itemID, String newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE documents SET author = ? WHERE itemID = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setString(1, newest);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modDocTitle(Connection c, String itemID, String newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE documents SET title = ? WHERE itemID = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setString(1, newest);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modDocPublisher(Connection c, String itemID, String newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE documents SET publisher = ? WHERE itemID = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setString(1, newest);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modDocEdition(Connection c, String itemID, int newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE documents SET edition = ? WHERE itemID = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setInt(1, newest);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modDocYear(Connection c, String itemID, int newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE documents SET year = ? WHERE itemID = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setInt(1, newest);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modDocBestSeller(Connection c, String itemID, boolean newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE documents SET is_best_seller = ? WHERE itemID = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setBoolean(1, newest);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modDocPrice(Connection c, String itemID, double newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE documents SET price = ? WHERE itemID = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setDouble(1, newest);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modDocNumberOfCopies(Connection c, String itemID, int newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE documents SET number_of_copies = ? WHERE itemID = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setInt(1, newest);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modDocCopiesAvailable(Connection c, String itemID, int newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE documents SET copies_available = ? WHERE itemID = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setInt(1, newest);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modDocType(Connection c, String itemID, String newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE documents SET type = ? WHERE itemID = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setString(1, newest);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modDocNumberReferences(Connection c, String itemID, int newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE documents SET number_of_references = ? WHERE itemID = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setInt(1, newest);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modDocInLine1(Connection c, String itemID, int newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE documents SET in_line1 = ? WHERE itemID = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setInt(1, newest);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modDocInLine2(Connection c, String itemID, int newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE documents SET in_line2 = ? WHERE itemID = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setInt(1, newest);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modDocInLine3(Connection c, String itemID, int newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE documents SET in_line3 = ? WHERE itemID = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setInt(1, newest);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modDocInLine4(Connection c, String itemID, int newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE documents SET in_line4 = ? WHERE itemID = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setInt(1, newest);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modDocInLine5(Connection c, String itemID, int newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE documents SET in_line5 = ? WHERE itemID = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setInt(1, newest);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modDocTurn1(Connection c, String itemID, int newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE documents SET turn1 = ? WHERE itemID = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setInt(1, newest);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modDocTurn2(Connection c, String itemID, int newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE documents SET turn2 = ? WHERE itemID = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setInt(1, newest);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modDocTurn3(Connection c, String itemID, int newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE documents SET turn3 = ? WHERE itemID = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setInt(1, newest);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modDocTurn4(Connection c, String itemID, int newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE documents SET turn4 = ? WHERE itemID = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setInt(1, newest);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modDocTurn5(Connection c, String itemID, int newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE documents SET turn5 = ? WHERE itemID = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setInt(1, newest);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static boolean addCheckOut(Connection c, String cardNumber, String itemID, String releaseDate, String deadline, int renew){
        PreparedStatement preparedStatement;
        String query = "insert into checkout (user_id, item_id, release_date, deadline, renew, on_request)" + " values (?,?,?,?,?,?)";
        try{
            preparedStatement = c.prepareStatement(query);
            preparedStatement.setString(1, cardNumber);
            preparedStatement.setString(2, itemID);
            preparedStatement.setString(3, releaseDate);
            preparedStatement.setString(4, deadline);
            preparedStatement.setInt(5, renew);
            preparedStatement.setInt(6, 0);
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
            Checkout ch = new Checkout("","","","", 0, 0);
            while(resultSet.next()){
                ch.userID = resultSet.getString("user_id");
                ch.itemID = resultSet.getString("item_id");
                ch.releaseDate = resultSet.getString("release_date");
                ch.deadline = resultSet.getString("deadline");
                ch.renew = resultSet.getInt("renew");
                ch.onRequest = resultSet.getInt("on_request");
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

    public static ArrayList<String> getCheckoutsForItem(Connection c, String itemID) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM checkout where item_id = ?";
        ArrayList<String> cardnumbers = new ArrayList<>();
        try {
            preparedStatement = c.prepareStatement(query);
            preparedStatement.setString(1, itemID);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                cardnumbers.add(resultSet.getString("user_id"));
            }
            return cardnumbers;
        } catch (Exception e){
            return cardnumbers;
        }
    }

    public static boolean modCheckoutCardNumber(Connection c, String cardNumber, String itemID, String newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE checkout SET user_id = ? WHERE user_id = ? and item_id = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setString(1, newest);
            preparedStatement.setString(2, cardNumber);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modCheckoutItemID(Connection c, String cardNumber, String itemID, String newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE checkout SET item_id = ? WHERE user_id = ? and item_id = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setString(1, newest);
            preparedStatement.setString(2, cardNumber);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modCheckoutRelease(Connection c, String cardNumber, String itemID, String newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE checkout SET release_date = ? WHERE user_id = ? and item_id = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setString(1, newest);
            preparedStatement.setString(2, cardNumber);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modCheckoutDeadline(Connection c, String cardNumber, String itemID, String newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE checkout SET deadline = ? WHERE user_id = ? and item_id = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setString(1, newest);
            preparedStatement.setString(2, cardNumber);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modCheckoutRenew(Connection c, String cardNumber, String itemID, int newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE checkout SET renew = ? WHERE user_id = ? and item_id = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setInt(1, newest);
            preparedStatement.setString(2, cardNumber);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean modCheckoutRequest(Connection c, String cardNumber, String itemID, boolean newest){
        PreparedStatement preparedStatement;
        String query1 = "UPDATE checkout SET on_request = ? WHERE user_id = ? and item_id = ?";
        try{
            preparedStatement = c.prepareStatement(query1);
            preparedStatement.setBoolean(1, newest);
            preparedStatement.setString(2, cardNumber);
            preparedStatement.setString(2, itemID);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static boolean addQueue(Connection c, String cardNumber, String itemID, int turn, int code){
        PreparedStatement preparedStatement;
        String query = "insert into queues (user_id, item_id, turn, code)" + " values (?,?,?,?)";
        try{
            preparedStatement = c.prepareStatement(query);
            preparedStatement.setString(1, cardNumber);
            preparedStatement.setString(2, itemID);
            preparedStatement.setInt(3, turn);
            preparedStatement.setInt(4, code);
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static Queue_Storer getQueue(Connection c, String card_number, String itemID) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM queues where user_id = ? and item_id = ?";
        try {
            preparedStatement = c.prepareStatement(query);
            preparedStatement.setString(1, card_number);
            preparedStatement.setString(2, itemID);
            resultSet = preparedStatement.executeQuery();
            Queue_Storer q = new Queue_Storer("","",0, 0);
            while(resultSet.next()){
                q.cardNumber = resultSet.getString("user_id");
                q.itemID = resultSet.getString("item_id");
                q.turn = resultSet.getInt("turn");
                q.code = resultSet.getInt("code");
            }
            if (q.cardNumber.equals("")) return null;
            return q;
        } catch (Exception e){
            return null;
        }
    }

    public static boolean deleteQueue(Connection c, String card_number, String itemID){
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String query1 = "delete from queues where user_id = ? and item_id = ?";

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

    public static boolean modQueueCardNumber(Connection c, String cardNumber, String newest){
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM queues where user_id = ?";
        try {
            preparedStatement = c.prepareStatement(query);
            preparedStatement.setString(1, cardNumber);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                PreparedStatement pst;
                String query1 = "UPDATE queues SET user_id = ? WHERE user_id = ?";
                try{
                    pst = c.prepareStatement(query1);
                    pst.setString(1, newest);
                    pst.setString(2, cardNumber);
                    pst.execute();
                    continue;
                }catch (Exception e){
                    return false;
                }
            }
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public static boolean modQueueItemID(Connection c, String itemID, String newest){
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM queues where item_id = ?";
        try {
            preparedStatement = c.prepareStatement(query);
            preparedStatement.setString(1, itemID);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                PreparedStatement pst;
                String query1 = "UPDATE queues SET item_id = ? WHERE item_id = ?";
                try{
                    pst = c.prepareStatement(query1);
                    pst.setString(1, newest);
                    pst.setString(2, itemID);
                    pst.execute();
                    continue;
                }catch (Exception e){
                    return false;
                }
            }
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static PriorityQueue<Queue_Storer> getPriorityQueue(Connection c, String itemID){
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM queues where item_id = ?";

        PriorityQueue<Queue_Storer> queue = new PriorityQueue<>();
        try {
            preparedStatement = c.prepareStatement(query);
            preparedStatement.setString(1, itemID);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                queue.add(new Queue_Storer(resultSet.getString("user_id"), itemID, resultSet.getInt("turn"), resultSet.getInt("code")));
            }
            return queue;
        } catch (Exception e){
            return queue;
        }
    }
}
