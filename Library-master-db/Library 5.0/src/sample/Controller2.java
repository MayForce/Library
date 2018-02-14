package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Controller2 {

    public Button back_button;
    public TextField searchField;
    public TextField nameOfBook;
    public TextField author;
    public TextField numberOfCopies;
    public CheckBox bestseller;
    public Button addBook;
    public Button addBookToBase;
    public Text price;
    public TextField Username;
    public TextField Name;
    public TextField Adress;
    public TextField PhoneNumber;
    public PasswordField Password;
    public Button addPatronToBase;
    public CheckBox faculty;
    public Button addPatron;
    public Text numberAvailableCopies;
    public Button search;
    public MenuItem back;

    public void back_to_home(ActionEvent actionEvent) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("sample.fxml")), 1000, 700);
        Stage stage = (Stage) back_button.getScene().getWindow();
        stage.setTitle("InnoLibrary");
        stage.setScene(scene);
        stage.show();
    }

    public void search(ActionEvent actionEvent) throws IOException {
        String keyword = new String();
        String text = searchField.getText();
        while (text.length() != 0){
            if (text.charAt(0) != ' '){
                keyword += text.charAt(0);
            } else {
                if (keyword.length() != 0){

                   /* Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Book.fxml")), 400, 600);
                    Stage stage = new Stage();
                    stage.setTitle("Book");
                    stage.setScene(scene);
                    stage.show();*/
                }
            }
        }
    }

    /*public void setNumberOfCopies(InputMethodEvent inputMethodEvent) {

    }*/

    public void addBookToBase(ActionEvent actionEvent) {
        if (!(nameOfBook.getText().isEmpty() || author.getText().isEmpty() || numberOfCopies.getText().isEmpty() || price.getText().isEmpty())) {
            Book newest  = new Book(author.getText(), nameOfBook.getText(), "", 0, "", 1, 2000, bestseller.isSelected() );
            for (int i = 0; i < Integer.parseInt(numberOfCopies.getText()); i++) {
               newest.addCopy(false);
            }
            Main.shelf.addItem(newest);

        }
    }

    public void addPatronToBase(ActionEvent actionEvent) {
        if(!(Username.getText().isEmpty() || Name.getText().isEmpty() || Adress.getText().isEmpty() || PhoneNumber.getText().isEmpty() || Password.getText().isEmpty()))
        {
            registerPat(Username.getText(), Name.getText(), Adress.getText(), PhoneNumber.getText(), Password.getText(), faculty.isSelected());


        }
    }

    private void registerPat(String username, String name, String address, String number, String password, Boolean faculty){
       StringBuilder cardNum = new StringBuilder("P");
        String size = ""+Main.patDB.patrons.size();
        for (int i = 0; i < (8-size.length()); i++){
            cardNum.append("0");
        }
        cardNum.append(Main.patDB.patrons.size());

        Main.patDB.patrons.put(username, new Patron(name, address, number, cardNum.toString(), password, faculty));

    }

    public void addPatron(ActionEvent actionEvent) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("addPatron.fxml")), 400, 600);
        Stage stage = new Stage();
        stage.setTitle("Add Patron");
        stage.setScene(scene);
        stage.show();
    }


    public void addbook(ActionEvent actionEvent) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("addBook.fxml")), 400, 600);
        Stage stage = new Stage();
        stage.setTitle("Add Book");
        stage.setScene(scene);
        stage.show();
    }
}
