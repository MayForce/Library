package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    Shelf shelf = new Shelf();
    LibrarianDB libDB = new LibrarianDB();
    PatronDB patDB = new PatronDB();

    Stage mainstage = new Stage();

    @FXML
    public TextField reg_login;
    @FXML
    public TextField reg_name;
    @FXML
    public TextField reg_address;
    @FXML
    public TextField reg_number;
    @FXML
    public PasswordField reg_password;
    @FXML
    public Button change_scene;
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;

    @Override
    public void start(Stage primaryStage) throws Exception{
       mainstage = primaryStage;
       Scene sceneLogin = new Scene(FXMLLoader.load(getClass().getResource("sample.fxml")), 1000, 700);
       mainstage.setTitle("InnoLibrary");
       mainstage.setScene(sceneLogin);
       mainstage.show();
    }

    //if button login was pressed then we have login in (login.getText()) and password in (password.getText())
    public void login(ActionEvent actionEvent) {
        if(!(login.getText().isEmpty() || password.getText().isEmpty()))
        {System.out.println(login.getText());
        System.out.println(password.getText());}
        else
            System.out.println("Login or Password is Empty");
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public void change_scene(ActionEvent actionEvent) throws IOException {
        Scene sceneSign_up = new Scene(FXMLLoader.load(getClass().getResource("sign_up.fxml")), 1000, 700);
        mainstage = (Stage) change_scene.getScene().getWindow();
        mainstage.setTitle("Sign Up");
        mainstage.setScene(sceneSign_up);
        mainstage.show();
    }

    //The same with login method
    public void sign_up(ActionEvent actionEvent) {
        if(!(reg_login.getText().isEmpty() || reg_name.getText().isEmpty() || reg_address.getText().isEmpty() || reg_number.getText().isEmpty() || reg_password.getText().isEmpty()))
        {
            registerLib(reg_name.getText(), reg_address.getText(), reg_number.getText(), reg_password.getText());
        }
        else
            System.out.println("Incorrect   ");
    }

    public void registerLib(String name, String address, String number, String password){
        StringBuilder cardNum = new StringBuilder("L");
        String size = ""+libDB.librarians.size();
        for (int i = 0; i < (8-size.length()); i++){
            cardNum.append("0");
        }
        cardNum.append(libDB.librarians.size());

        libDB.librarians.put(cardNum.toString(), new Librarian(name, address, number, cardNum.toString(), password));
        System.out.println(libDB.librarians.get(cardNum.toString()).getName());
    }
}
