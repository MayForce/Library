package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    @FXML
    public Button change_scene;
    @FXML
    public TextField reg_login;
    @FXML
    public Button sign_up;
    @FXML
    public TextField reg_name;
    @FXML
    public TextField reg_address;
    @FXML
    public TextField reg_number;
    @FXML
    public PasswordField reg_password;
    @FXML
    public Button back_button;
    @FXML
    public Text incorrect;
    @FXML
    public Text all_fields;
    @FXML
    public CheckBox isLibrarian;
    @FXML
    public MenuButton typeOfUser;
    @FXML
    public TextField cardNumber;
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;

    static Connection c;

    public void login(ActionEvent actionEvent) throws IOException {
        if (!(login.getText().isEmpty() || password.getText().isEmpty())) {
            incorrect.setVisible(false);

            try {
                if (DatabaseFunctions.login(c, login.getText(), password.getText())) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
                    Stage stage = (Stage) change_scene.getScene().getWindow();
                    stage.setTitle("Account");
                    stage.setScene(scene);
                    stage.show();

                    ((Controller2) fxmlLoader.getController()).c = c;
                } else {
                    incorrect.setText("Wrong user information");
                    incorrect.setVisible(true);
                }
            } catch (Exception e) {
                incorrect.setText("Wrong user information");
                incorrect.setVisible(true);
            }
            } else {
            incorrect.setVisible(true);
        }
    }

    public void change_scene(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sign_up.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1000,700);
        Stage stage = (Stage) change_scene.getScene().getWindow();
        stage.setTitle("Sign Up");
        stage.setScene(scene);
        stage.show();

        ((Controller) fxmlLoader.getController()).c = c;
    }

    public void sign_up(ActionEvent actionEvent) throws IOException {
        if(!(reg_login.getText().isEmpty() || reg_name.getText().isEmpty() || reg_address.getText().isEmpty() || reg_number.getText().isEmpty() || reg_password.getText().isEmpty()))
        {
            String type;
            addUser(reg_login.getText(), reg_name.getText(), reg_address.getText(), reg_number.getText(), cardNumber.getText(), reg_password.getText(), typeOfUser.getText());

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
            Stage stage;
            stage = (Stage) sign_up.getScene().getWindow();
            stage.setTitle("InnoLibrary");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            ((Controller) fxmlLoader.getController()).c = c;
        }
        else
        {
            all_fields.setVisible(true);
        }
    }

    public void back_to_home(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        Stage stage = (Stage) back_button.getScene().getWindow();
        stage.setTitle("InnoLibrary");
        stage.setScene(scene);
        stage.show();

        ((Controller) fxmlLoader.getController()).c = c;
    }



    // LIBRARY SYSTEM INNER-WORKING METHODS ----------------------------------------------------------------------------------------------------------------------------------------------------

    // INCOMPLETE =================================
    public static int numUsers(){return 0;}
    // INCOMPLETE =================================
    public static int numDocuments(){return 0;}

    // Used to add any type of User
    public static void addUser(String username, String name, String address, String phoneNumber, String cardNumber, String password, String type) {
        DatabaseFunctions.addUser(c, username, name, address, phoneNumber, cardNumber, password, type, 0, 0);
        System.out.println(type + " " + Controller.searchUser(cardNumber).username_+ " added to system.");
    }

    // INCOMPLETE =================================
    // Used to obtain User information via their card number and store it in a User object
    public static User searchUser(String cardNumber) {
        try {
            User u = DatabaseFunctions.getUser(c, cardNumber);
            System.out.println(u.name_ + " found.");
            //CALCULATE FINE
            return u;
        } catch (Exception e) {
            System.out.println("User not found in the system.");
            return null;
        }

    }

    // Used to delete a User from the system using their card number
    public static void deleteUser(String cardNumber) {
        if (searchUser(cardNumber)!= null) {
            DatabaseFunctions.deleteUser(c, cardNumber);
            System.out.println("User " + cardNumber + " deleted from system.");
        } else System.out.println("User was not found.");
    }

    // Used to add any type of Item
    // Depending on the type, some fields can be hidden in the interface and filled with default values
    public static void addItem(String itemID, String author, String title, String publisher, int edition, int year, boolean isBestSeller, double price, int numberCopies, int copiesAvailable, String type, int numberReferences) {
        DatabaseFunctions.addItem(c,itemID,author,title,publisher,edition,year,isBestSeller,price,numberCopies,copiesAvailable,type, numberReferences);
        System.out.println(type + " " + Controller.searchItem(itemID).title + " added to system.");
    }

    // Used to obtain Item information via its ID and store it in an Item_Storer object
    public static Item_Storer searchItem(String itemID) {
        try {
            Item_Storer it = DatabaseFunctions.getItem(c, itemID);
            System.out.println(it.title + " found.");
            return it;
        } catch (Exception e) {
            System.out.println("Item not found in the system.");
            return null;
        }

    }

    // Used to delete an Item from the system using its ID
    public static void deleteItem(String itemID) {
        if (searchItem(itemID)!= null) {
            DatabaseFunctions.deleteItem(c, itemID);
            System.out.println("Item " + itemID + " deleted from system.");
        } else System.out.println("Item was not found.");
    }

    // INCOMPLETE ==================================
    // Used to register a checkout using the User's card number and the Item ID
    // This checks the priority queue and displays a message if no copies available
    public static void checkOut(String cardNumber, String itemID) {
        if (searchUser(cardNumber)!= null) {
            if (searchItem(itemID).copiesAvailable > 0) {
                // Check priority queue
                LocalDate released = LocalDate.now();
                LocalDate deadline;
                if (searchUser(cardNumber).type_.toUpperCase().equals("VISITING")) deadline = released.plusWeeks(1);
                else if (searchUser(cardNumber).type_.toUpperCase().equals("FACULTY")) deadline = released.plusWeeks(4);
                else if (!searchItem(itemID).isBestSeller) deadline = released.plusWeeks(3);
                else deadline = released.plusWeeks(2);
                DatabaseFunctions.addCheckOut(c, cardNumber, itemID, released.toString(), deadline.toString(),0);
                User user = searchUser(cardNumber);
                deleteUser(cardNumber);
                DatabaseFunctions.addUser(c, user.username_, user.name_, user.address_, user.phoneNumber_, user.cardNumber_, user.password_, user.type_, user.borrow_ + 1, user.fine_);
                Item_Storer it = searchItem(itemID);
                deleteItem(itemID);
                addItem(it.itemID, it.author, it.title, it.publisher, it.edition, it.year, it.isBestSeller, it.price, it.numberOfCopies, it.copiesAvailable - 1, it.type, 0);
            } else {
                // Display message of unavailability:
                // Ask if want to enter queue
                // Display option menu in case of faculty
                // Display number of people in line in front
            }
        }
    }

    // Used to obtain  a single Checkout information via its keeper card number and Item ID and store it in a Checkout object
    public static Checkout searchSingleCheckout(String cardNumber, String itemID) {
        try {
            Checkout ch = DatabaseFunctions.getCheckout(c, cardNumber, itemID);
            System.out.println("Checkout found.");
            return ch;
        } catch (Exception e) {
            System.out.println("Checkout not found in the system.");
            return null;
        }

    }

    // Used to obtain an Arraylist of all Item IDs for every Checkout registered by a User using their card number
    public static ArrayList<String> getCheckoutList(String card_number) {
        return DatabaseFunctions.getCheckoutList(c,card_number);
    }

    // INCOMPLETE ==================================
    public static void renew(String cardNumber, String itemID) {
        // Add new column in checkout table
    }

    // INCOMPLETE ==================================
    // Used to delete a checkout and modify the counters in both the User and the Item
    public static void returnItem(String cardNumber, String itemID) {
        User user = searchUser(cardNumber);
        deleteUser(cardNumber);
        DatabaseFunctions.addUser(c, user.username_, user.name_, user.address_, user.phoneNumber_, user.cardNumber_, user.password_, user.type_, user.borrow_ - 1, user.fine_);
        Item_Storer it = searchItem(itemID);
        deleteItem(itemID);
        //addItem(it.itemID, it.author, it.title, it.publisher, it.edition, it.year, it.isBestSeller, it.price, it.numberOfCopies, it.copiesAvailable + 1, it.type);
        DatabaseFunctions.deleteCheckout(c,cardNumber,itemID);
    }

    // INCOMPLETE ==================================
    // Used to add an available copy of a certain Item
    public static void addCopy(String itemID){
        Item_Storer it = searchItem(itemID);
        deleteItem(itemID);
        //addItem(it.itemID, it.author, it.title, it.publisher, it.edition, it.year, it.isBestSeller, it.price, it.numberOfCopies, it.copiesAvailable + 1, it.type);
    }

    // INCOMPLETE ==================================
    // Used to remove an available copy of a certain Item
    public static void removeCopy(String itemID, int number){
        if (searchItem(itemID).copiesAvailable >= number) {
            Item_Storer it = searchItem(itemID);
            deleteItem(itemID);
            //addItem(it.itemID, it.author, it.title, it.publisher, it.edition, it.year, it.isBestSeller, it.price, it.numberOfCopies, it.copiesAvailable - number, it.type);
            System.out.println(number+" copies deleted from "+it.title);
        } else {
            System.out.println("There are not sufficient copies of this item.");
        }
    }

    // INCOMPLETE ==================================
    // Used to calculate the total fine for a certain User
    private void totalFine() {

    }

    // INCOMPLETE ==================================
    public static void outstandingRequest(){}
}
