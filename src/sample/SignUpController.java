package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;

public class SignUpController {
    static Connection c;

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
    public TextField cardNumber;
    @FXML
    public MenuButton typeOfUser;

    int privilege;

    public void sign_up(ActionEvent actionEvent) {
        Controller.c = c;
        if (!(reg_login.getText().isEmpty() || reg_name.getText().isEmpty() || reg_address.getText().isEmpty() || reg_number.getText().isEmpty() || cardNumber.getText().isEmpty() || reg_password.getText().isEmpty())) {
            Controller.addUser(reg_login.getText(), reg_name.getText(), reg_address.getText(), reg_number.getText(), cardNumber.getText(), reg_password.getText(), typeOfUser.getText(), privilege);
        } else{
            Controller.error(true,"Error", "something wrong");
        }


    }

    public void ChosenTypeP(ActionEvent actionEvent) {
        typeOfUser.setText("VISITING");
        privilege = 0;
    }

    public void ChosenTypeS(ActionEvent actionEvent) {
        typeOfUser.setText("STUDENT");
        privilege = 0;
    }

    public void ChosenTypeFP(ActionEvent actionEvent) {
        typeOfUser.setText("PROFESSOR");
        privilege = 0;
    }

    public void ChosenTypeL1(ActionEvent actionEvent) {
        typeOfUser.setText("LIBRARIAN");
        privilege = 1;
    }

    public void ChosenTypeFT(ActionEvent actionEvent) {
        typeOfUser.setText("TA");
        privilege = 0;
    }

    public void ChosenTypeFI(ActionEvent actionEvent) {
        typeOfUser.setText("INSTRUCTOR");
        privilege = 0;
    }

    public void ChosenTypeL2(ActionEvent actionEvent) {
        typeOfUser.setText("LIBRARIAN");
        privilege = 2;
    }

    public void ChosenTypeL3(ActionEvent actionEvent) {
        typeOfUser.setText("LIBRARIAN");
        privilege = 3;
    }
}
