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
//
//    private void registerLib(String username, String name, String address, String number, String password){
//        StringBuilder cardNum = new StringBuilder("L");
//        String size = ""+Main.libDB.librarians.size();
//        for (int i = 0; i < (8-size.length()); i++){
//            cardNum.append("0");
//        }
//        cardNum.append(Main.libDB.librarians.size());
//
//        Main.libDB.librarians.put(username, new Librarian(name, address, number, cardNum.toString(), password));
//    }

    public void back_to_home(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        Stage stage = (Stage) back_button.getScene().getWindow();
        stage.setTitle("InnoLibrary");
        stage.setScene(scene);
        stage.show();

        ((Controller) fxmlLoader.getController()).c = c;
    }

    public static void addUser(String username, String name, String address, String phoneNumber, String cardNumber, String password, String type) {
        DatabaseFunctions.register(c, username, name, address, phoneNumber, cardNumber, password, type, 0, 0);
    }

    public static void addItem(String itemID, String author, String title, String publisher, int edition, int year, boolean isBestSeller, double price, int numberCopies, int copiesAvailable, String type) {
        DatabaseFunctions.addItem(c,itemID,author,title,publisher,edition,year,isBestSeller,price,numberCopies,copiesAvailable,type);
    }

    public static User searchUser(String cardNumber) {
        try {
            User u = DatabaseFunctions.getUser(c, cardNumber);
            System.out.println(u.username_);
            //CALCULATE FINE
            //UPLOAD IT TO DB
            return u;
        } catch (Exception e) {
            return null;
        }

    }

    public static Item_Storer searchItem(String itemID) {
        try {
            Item_Storer it = DatabaseFunctions.getItem(c, itemID);
            System.out.println(it.title);
            //CALCULATE FINE
            //UPLOAD IT TO DB
            return it;
        } catch (Exception e) {
            return null;
        }

    }

    public static void deleteUser(String cardNumber) {
        DatabaseFunctions.deleteUser(c,cardNumber);
    }

    public static void deleteItem(String itemID) {
        DatabaseFunctions.deleteItem(c,itemID);
    }

    public static void checkOut(String cardNumber, String itemID) {
        if (searchItem(itemID).copiesAvailable>0) {
            LocalDate released = LocalDate.now();
            LocalDate deadline;
            if (searchUser(cardNumber).type_.toUpperCase().equals("FACULTY")) deadline = released.plusWeeks(2);
            else if (!searchItem(itemID).isBestSeller) deadline = released.plusWeeks(3);
            else deadline = released.plusWeeks(2);
            DatabaseFunctions.addCheckOut(c, cardNumber, itemID, released.toString(), deadline.toString());
            User user = searchUser(cardNumber);
            deleteUser(cardNumber);
            DatabaseFunctions.register(c, user.username_, user.name_, user.address_, user.phoneNumber_, user.cardNumber_, user.password_, user.type_, user.borrow_ + 1, user.fine_);
            Item_Storer it = searchItem(itemID);
            deleteItem(itemID);
            addItem(it.itemID, it.author, it.title, it.publisher, it.edition, it.year, it.isBestSeller, it.price, it.numberOfCopies, it.copiesAvailable - 1, it.type);
        } else System.out.println("Woops! No copies available for " + searchItem(itemID).title);
    }

    public static void returnItem(String cardNumber, String itemID) {
        User user = searchUser(cardNumber);
        deleteUser(cardNumber);
        DatabaseFunctions.register(c, user.username_, user.name_, user.address_, user.phoneNumber_, user.cardNumber_, user.password_, user.type_, user.borrow_ - 1, user.fine_);
        Item_Storer it = searchItem(itemID);
        deleteItem(itemID);
        addItem(it.itemID, it.author, it.title, it.publisher, it.edition, it.year, it.isBestSeller, it.price, it.numberOfCopies, it.copiesAvailable + 1, it.type);
        DatabaseFunctions.deleteCheckout(c,cardNumber,itemID);
    }

    public static void addCopy(String itemID){
        Item_Storer it = searchItem(itemID);
        deleteItem(itemID);
        addItem(it.itemID, it.author, it.title, it.publisher, it.edition, it.year, it.isBestSeller, it.price, it.numberOfCopies, it.copiesAvailable + 1, it.type);
    }

    public static void removeCopy(String itemID){
        Item_Storer it = searchItem(itemID);
        deleteItem(itemID);
        addItem(it.itemID, it.author, it.title, it.publisher, it.edition, it.year, it.isBestSeller, it.price, it.numberOfCopies, (it.copiesAvailable > 0)? it.copiesAvailable - 1 : 0, it.type);
    }

    private void totalFine() {

    }
}
