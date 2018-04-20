package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;

public class SignUpController {
    static Connection c;

    public TextField reg_login;
    public TextField reg_name;
    public TextField reg_address;
    public TextField reg_number;
    public PasswordField reg_password;
    public TextField cardNumber;
    public MenuButton typeOfUser;
    public MenuItem professor;
    public MenuItem student;
    public MenuItem faculty;
    public MenuItem librarian;

    public void sign_up(ActionEvent actionEvent) {
        if (!(reg_login.getText().isEmpty() || reg_name.getText().isEmpty() || reg_address.getText().isEmpty() || reg_number.getText().isEmpty() || cardNumber.getText().isEmpty() || reg_password.getText().isEmpty())) {
            Controller.addUser(reg_login.getText(), reg_name.getText(), reg_address.getText(), reg_number.getText(), cardNumber.getText(), reg_password.getText(), typeOfUser.getText());
            //System.out.println(typeOfUser.getText() + " " + Controller.searchUser(cardNumber.getText()).username_ + " added to system.");
            System.out.println(typeOfUser.getText());
        } else{
            System.out.println("smth wrong");
        }

    }

    public void ChosenTypeP(ActionEvent actionEvent) {
        typeOfUser.setText("VISITING");
    }

    public void ChosenTypeS(ActionEvent actionEvent) {
        typeOfUser.setText(student.getText());
    }

    public void ChosenTypeF(ActionEvent actionEvent) {
        typeOfUser.setText(faculty.getText());
    }

    public void ChosenTypeL(ActionEvent actionEvent) {
        typeOfUser.setText(librarian.getText());
    }
}
