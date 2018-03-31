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
import java.time.Period;
import java.util.ArrayList;
import java.util.PriorityQueue;

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

    // Used to obtain User information via their card number and store it in a User object
    public static User searchUser(String cardNumber) {
        try {
            modFine(cardNumber, totalFine(cardNumber));
            User u = DatabaseFunctions.getUser(c, cardNumber);
            System.out.println(u.name_ + " found.");
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

    // Methods used to modify any variable for a user using its card number
    public static boolean modCardNumber(String cardNumber, String newest){
        ArrayList<String> checkout = getCheckoutList(cardNumber);
        for (int i = 0; i < checkout.size(); i++){
            modCheckoutCardNumber(cardNumber,checkout.get(i), newest);
        }
        return DatabaseFunctions.modCardNumber(c,cardNumber,newest);
    }
    public static boolean modUsername(String cardNumber, String newest){
        return DatabaseFunctions.modUsername(c,cardNumber,newest);
    }
    public static boolean modName(String cardNumber, String newest){
        return DatabaseFunctions.modName(c,cardNumber,newest);
    }
    public static boolean modAddress(String cardNumber, String newest){
        return DatabaseFunctions.modAddress(c,cardNumber,newest);
    }
    public static boolean modPhoneNumber(String cardNumber, String newest){
        return DatabaseFunctions.modPhoneNumber(c,cardNumber,newest);
    }
    public static boolean modPassword(String cardNumber, String newest){
        return DatabaseFunctions.modPassword(c,cardNumber,newest);
    }
    public static boolean modType(String cardNumber, String newest){
        return DatabaseFunctions.modType(c,cardNumber,newest);
    }
    public static boolean modBorrow(String cardNumber, int newest){
        return DatabaseFunctions.modBorrow(c,cardNumber,newest);
    }
    public static boolean modFine(String cardNumber, double newest){
        return DatabaseFunctions.modFine(c,cardNumber,newest);
    }

    // Used to add any type of Item
    // Depending on the type, some fields can be hidden in the interface and filled with default values
    public static void addItem(String itemID, String author, String title, String publisher, int edition, int year, boolean isBestSeller, double price, int numberCopies, String type, int numberReferences) {
        DatabaseFunctions.addItem(c,itemID,author,title,publisher,edition,year,isBestSeller,price,numberCopies,type, numberReferences);
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

    // Methods used to modify any variable for an item using its itemID
    public static boolean modDocItemID(String itemID, String newest){
        ArrayList<String> checkout = getCheckoutsForItem(itemID);
        for (int i = 0; i < checkout.size(); i++){
            modCheckoutItemID(checkout.get(i),itemID, newest);
        }
        return DatabaseFunctions.modDocItemID(c,itemID,newest);
    }
    public static boolean modDocAuthor(String itemID, String newest){
        return DatabaseFunctions.modDocAuthor(c,itemID,newest);
    }
    public static boolean modDocTitle(String itemID, String newest){
        return DatabaseFunctions.modDocTitle(c,itemID,newest);
    }
    public static boolean modDocPublisher(String itemID, String newest){
        return DatabaseFunctions.modDocPublisher(c,itemID,newest);
    }
    public static boolean modDocEdition(String itemID, int newest){
        return DatabaseFunctions.modDocEdition(c,itemID,newest);
    }
    public static boolean modDocYear(String itemID, int newest){
        return DatabaseFunctions.modDocYear(c,itemID,newest);
    }
    public static boolean modDocBestSeller(String itemID, boolean newest){
        return DatabaseFunctions.modDocBestSeller(c,itemID,newest);
    }
    public static boolean modDocPrice(String itemID, double newest){
        return DatabaseFunctions.modDocPrice(c,itemID,newest);
    }
    public static boolean modDocNumberOfCopies(String itemID, int newest){
        return DatabaseFunctions.modDocNumberOfCopies(c,itemID,newest);
    }
    public static boolean modDocCopiesAvailable(String itemID, int newest){
        return DatabaseFunctions.modDocCopiesAvailable(c,itemID,newest);
    }
    public static boolean modDocType(String itemID, String newest){
        return DatabaseFunctions.modDocType(c,itemID,newest);
    }
    public static boolean modDocNumberReferences(String itemID, int newest){
        int temp = searchItem(itemID).copiesAvailable - searchItem(itemID).numberReferences;
        if (temp+newest <= searchItem(itemID).numberOfCopies)
        return DatabaseFunctions.modDocNumberReferences(c,itemID,temp+newest);
        else System.out.println("Not enough copies."); return false;
    }
    public static boolean modDocInLine1(String itemID, int newest){
        return DatabaseFunctions.modDocInLine1(c,itemID,newest);
    }
    public static boolean modDocInLine2(String itemID, int newest){
        return DatabaseFunctions.modDocInLine2(c,itemID,newest);
    }
    public static boolean modDocInLine3(String itemID, int newest){
        return DatabaseFunctions.modDocInLine3(c,itemID,newest);
    }
    public static boolean modDocInLine4(String itemID, int newest){
        return DatabaseFunctions.modDocInLine4(c,itemID,newest);
    }
    public static boolean modDocInLine5(String itemID, int newest){
        return DatabaseFunctions.modDocInLine5(c,itemID,newest);
    }
    public static boolean modDocTurn1(String itemID, int newest){
        return DatabaseFunctions.modDocTurn1(c,itemID,newest);
    }
    public static boolean modDocTurn2(String itemID, int newest){
        return DatabaseFunctions.modDocTurn2(c,itemID,newest);
    }
    public static boolean modDocTurn3(String itemID, int newest){
        return DatabaseFunctions.modDocTurn3(c,itemID,newest);
    }
    public static boolean modDocTurn4(String itemID, int newest){
        return DatabaseFunctions.modDocTurn4(c,itemID,newest);
    }
    public static boolean modDocTurn5(String itemID, int newest){
        return DatabaseFunctions.modDocTurn5(c,itemID,newest);
    }

    // Used to register a checkout or add a user to the priority queue for an item
    // Description for GUI is below
    public static boolean checkOut(String cardNumber, String itemID, int code) {
        // When button to checkout is clicked, the code to pass is 0
        switch (code){
            case 0:
                // Code 0 will check if there are any copies available and if the priority queue is empty or if it is the turn for the current user
                if (searchItem(itemID).copiesAvailable > 0 && (getPriorityQueue(itemID).isEmpty() || getPriorityQueue(itemID).peek().cardNumber.equals(cardNumber))) {
                    if(!getPriorityQueue(itemID).isEmpty()) updateTurns(itemID, searchQueue(cardNumber, itemID).code);  // Turn numbers will be updated using the recorded code from the queues table
                    else updateTurns(itemID, 0);
                    LocalDate released = LocalDate.now();   // Save today's date as the released date
                    LocalDate deadline; //Obtain the deadline depending on criteria
                    if (searchUser(cardNumber).type_.toUpperCase().equals("VISITING"))
                        deadline = released.plusWeeks(1);
                    else if (searchUser(cardNumber).type_.toUpperCase().equals("FACULTY"))
                        deadline = released.plusWeeks(4);
                    else if (!searchItem(itemID).isBestSeller) deadline = released.plusWeeks(3);
                    else deadline = released.plusWeeks(2);
                    DatabaseFunctions.addCheckOut(c, cardNumber, itemID, released.toString(), deadline.toString(), 0);  //Add the checkout to the database
                    modBorrow(cardNumber, searchUser(cardNumber).borrow_ + 1);  // Increase borrowed items for the user
                    modDocCopiesAvailable(itemID, searchItem(itemID).copiesAvailable - 1);  // Decrease available copies for the item
                } else {
                    // If there are no available copies, or if it is not the turn of the current user, then the GUI must:
                    // - Display message of unavailability
                    // - Ask if want to enter queue, it the user wants, then there must be:
                    // -- An option menu to check what type of user it is:
                    //      - STUDENT: Call checkOut method again but with code 1
                    //      - INSTRUCTOR: Call checkOut method again but with code 2
                    //      - TA: Call checkOut method again but with code 3
                    //      - VISITING: Call checkOut method again but with code 4
                    //      - PROFESSOR: Call checkOut method again but with code 5
                    return false;
                }
                return true;

                // Now that the method has been called again with the appropriate code, the queue information will be added to the database
            case 1:
                addQueue(cardNumber,itemID,searchItem(itemID).turn1+100,code);
                modDocInLine1(itemID,searchItem(itemID).inLine1+1);
                modDocTurn1(itemID,searchItem(itemID).turn1+1);
                return true;
            case 2:
                addQueue(cardNumber,itemID,searchItem(itemID).turn2+200,code);
                modDocInLine2(itemID,searchItem(itemID).inLine2+1);
                modDocTurn2(itemID,searchItem(itemID).turn2+1);
                return true;
            case 3:
                addQueue(cardNumber,itemID,searchItem(itemID).turn3+300,code);
                modDocInLine3(itemID,searchItem(itemID).inLine3+1);
                modDocTurn3(itemID,searchItem(itemID).turn3+1);
                return true;
            case 4:
                addQueue(cardNumber,itemID,searchItem(itemID).turn4+400,code);
                modDocInLine4(itemID,searchItem(itemID).inLine4+1);
                modDocTurn4(itemID,searchItem(itemID).turn4+1);
                return true;
            case 5:
                addQueue(cardNumber,itemID,searchItem(itemID).turn5+500,code);
                modDocInLine5(itemID,searchItem(itemID).inLine5+1);
                modDocTurn5(itemID,searchItem(itemID).turn5+1);
                return true;
        }
        return true;
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

    // Used to obtain an Arraylist of all User card numbers for every Checkout registered to an Item using its item ID
    public static ArrayList<String> getCheckoutsForItem(String itemID) {
        return DatabaseFunctions.getCheckoutsForItem(c,itemID);
    }

    // Used to renew a checkout
    public static boolean renew(String cardNumber, String itemID) {
        if (searchSingleCheckout(cardNumber,itemID)!= null && searchSingleCheckout(cardNumber,itemID).onRequest < 1) {
            if (LocalDate.parse(searchSingleCheckout(cardNumber, itemID).deadline).isEqual(LocalDate.now())) {
                if (searchUser(cardNumber).type_.equals("VISITING")) {
                    modCheckoutRelease(cardNumber, itemID, LocalDate.now().toString());
                    modCheckoutDeadline(cardNumber, itemID, LocalDate.now().plusWeeks(1).toString());
                    modCheckoutRenew(cardNumber, itemID, searchSingleCheckout(cardNumber, itemID).renew + 1);
                } else if (searchSingleCheckout(cardNumber, itemID).renew < 1){
                    LocalDate released = LocalDate.now(), deadline;
                    if (searchUser(cardNumber).type_.toUpperCase().equals("FACULTY")) deadline = released.plusWeeks(4);
                    else if (!searchItem(itemID).isBestSeller) deadline = released.plusWeeks(3);
                    else deadline = released.plusWeeks(2);
                    modCheckoutRelease(cardNumber, itemID, released.toString());
                    modCheckoutDeadline(cardNumber, itemID, deadline.toString());
                    modCheckoutRenew(cardNumber, itemID, searchSingleCheckout(cardNumber, itemID).renew + 1);
                } else {
                    System.out.println("Cannot renew more than 1 time.");
                    return false;
                }
            }
            return true;
        } else {
            System.out.println("Checkout not found.");
            return false;
        }
    }

    // Methods used to modify any variable for a Checkout using its card number and itemID
    public static boolean modCheckoutCardNumber(String cardNumber, String itemID, String newest){
        return DatabaseFunctions.modCheckoutCardNumber(c,cardNumber,itemID,newest);
    }
    public static boolean modCheckoutItemID(String cardNumber, String itemID, String newest){
        return DatabaseFunctions.modCheckoutItemID(c,cardNumber,itemID,newest);
    }
    public static boolean modCheckoutRelease(String cardNumber, String itemID, String newest){
        return DatabaseFunctions.modCheckoutRelease(c,cardNumber,itemID,newest);
    }
    public static boolean modCheckoutDeadline(String cardNumber, String itemID, String newest){
        return DatabaseFunctions.modCheckoutDeadline(c,cardNumber,itemID,newest);
    }
    public static boolean modCheckoutRenew(String cardNumber, String itemID, int newest){
        return DatabaseFunctions.modCheckoutRenew(c,cardNumber,itemID,newest);
    }
    public static boolean modCheckoutRequest(String cardNumber, String itemID, boolean newest){
        return DatabaseFunctions.modCheckoutRequest(c,cardNumber,itemID,newest);
    }

    // Used to delete a checkout and modify the counters in both the User and the Item
    public static void returnItem(String cardNumber, String itemID) {
        modBorrow(cardNumber,searchUser(cardNumber).borrow_ - 1);
        if(searchSingleCheckout(cardNumber,itemID).onRequest<1)modDocCopiesAvailable(itemID,searchItem(itemID).copiesAvailable + 1);
        DatabaseFunctions.deleteCheckout(c,cardNumber,itemID);
    }

    // Used to add an available copy of a certain Item
    public static void addCopy(String itemID){
        modDocNumberOfCopies(itemID,searchItem(itemID).numberOfCopies + 1);
    }

    // Used to remove an available copy of a certain Item
    public static void removeCopy(String itemID, int number){
        if (searchItem(itemID).copiesAvailable >= number) {
            modDocNumberOfCopies(itemID,searchItem(itemID).numberOfCopies - number);
            System.out.println(number+" copies deleted from "+searchItem(itemID).title);
        } else {
            System.out.println("There are not sufficient copies of this item.");
        }
    }

    // Used to calculate the total fine for a certain User
    public static double totalFine(String cardNumber) {
        double total = 0;
        ArrayList<String> checkouts = getCheckoutList(cardNumber);
        for (int i = 0; i < checkouts.size(); i++){
            total += individualFine(cardNumber, checkouts.get(i));
        }
        return total;
    }

    // Used to calculate the fine for a certain checkout
    public static double individualFine(String cardNumber, String itemID){
        Checkout ch = DatabaseFunctions.getCheckout(c, cardNumber, itemID);
        Period period = Period.between(LocalDate.now(),LocalDate.parse(ch.deadline));
        double fine = period.getDays()*100;
        if (fine > 0){
            if (fine < DatabaseFunctions.getItem(c,itemID).price) return fine;
            else return DatabaseFunctions.getItem(c,itemID).price;
        } else return 0;
    }

    public static void outstandingRequest(String cardNumber, String itemID){
        if (searchSingleCheckout(cardNumber,itemID)!= null) {
            modCheckoutRequest(cardNumber, itemID, true);
            modDocNumberReferences(itemID, searchItem(itemID).numberReferences + 1);
        }
    }

    // Used to add an entry to the Queues database
    public static void addQueue(String cardNumber, String itemID, int turn, int code) {
        DatabaseFunctions.addQueue(c,cardNumber,itemID,turn, code);
        System.out.println(searchUser(cardNumber).name_ + " added to the queue for " + searchItem(itemID).title + " with turn " + turn + "." );
    }

    // Used to obtain Queue information via its User card number and item ID, and store it in a Queue_Storer object
    public static Queue_Storer searchQueue(String cardNumber, String itemID) {
        try {
            Queue_Storer q = DatabaseFunctions.getQueue(c, cardNumber, itemID);
            System.out.println("Queue found.");
            return q;
        } catch (Exception e) {
            System.out.println("Queue not found in the system.");
            return null;
        }

    }

    // Used to delete a Queue entry from the system
    public static void deleteQueue(String cardNumber, String itemID) {
        if (searchQueue(cardNumber, itemID)!= null) {
            DatabaseFunctions.deleteQueue(c,cardNumber, itemID);
            System.out.println("Queue deleted from system.");
        } else System.out.println("Queue was not found.");
    }

    // Methods used to modify any variable for a Queue entry using its card number and itemID
    public static boolean modQueueCardNumber(String cardNumber, String itemID, String newest){
        return DatabaseFunctions.modQueueCardNumber(c,cardNumber,newest);
    }
    public static boolean modQueueItemID(String cardNumber, String itemID, String newest){
        return DatabaseFunctions.modQueueItemID(c,itemID,newest);
    }

    // Get the priority queue for a certain item
    public static PriorityQueue<Queue_Storer> getPriorityQueue(String itemID) {
        return DatabaseFunctions.getPriorityQueue(c,itemID);
    }

    // Update turns for item priority queue
    private static void updateTurns(String itemID, int code){
        switch(code){
            case 0:
                break;
            case 1:
                modDocInLine1(itemID,searchItem(itemID).inLine1-1);
                if (searchItem(itemID).inLine1 == 0) modDocTurn1(itemID, 1);
                break;
            case 2:
                modDocInLine2(itemID,searchItem(itemID).inLine2-1);
                if (searchItem(itemID).inLine2 == 0) modDocTurn2(itemID, 1);
                break;
            case 3:
                modDocInLine3(itemID,searchItem(itemID).inLine3-1);
                if (searchItem(itemID).inLine3 == 0) modDocTurn3(itemID, 1);
                break;
            case 4:
                modDocInLine4(itemID,searchItem(itemID).inLine4-1);
                if (searchItem(itemID).inLine4 == 0) modDocTurn4(itemID, 1);
                break;
            case 5:
                modDocInLine5(itemID,searchItem(itemID).inLine5-1);
                if (searchItem(itemID).inLine5 == 0) modDocTurn5(itemID, 1);
                break;
        }
    }
}
