package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


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
    private TextField login;
    @FXML
    private PasswordField password;

    public void login(ActionEvent actionEvent) {
        if(!(login.getText().isEmpty() || password.getText().isEmpty()))
        {   incorrect.setVisible(false);
            System.out.println(login.getText());
            System.out.println(password.getText());}
        else
        {incorrect.setVisible(true); }
    }

    public void change_scene(ActionEvent actionEvent) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("sign_up.fxml")),1000,700);
        Stage stage = new Stage();
        stage = (Stage) change_scene.getScene().getWindow();
        stage.setTitle("Sign Up");
        stage.setScene(scene);
        stage.show();
    }

    public void sign_up(ActionEvent actionEvent) throws IOException {
        if(!(reg_login.getText().isEmpty() || reg_name.getText().isEmpty() || reg_address.getText().isEmpty() || reg_number.getText().isEmpty() || reg_password.getText().isEmpty()))
        {   all_fields.setVisible(false);
            System.out.println(reg_login.getText());
            System.out.println(reg_name.getText());
            System.out.println(reg_address.getText());
            System.out.println(reg_number.getText());
            System.out.println(reg_password.getText());
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("sample.fxml")), 1000, 700);
            Stage stage = new Stage();
            stage = (Stage) sign_up.getScene().getWindow();
            stage.setTitle("InnoLibrary");
            stage.setScene(scene);
            stage.show();
        }
        else
        {
            all_fields.setVisible(true);
        }
    }

    public void back_to_home(ActionEvent actionEvent) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("sample.fxml")), 1000, 700);
        Stage stage = new Stage();
        stage = (Stage) back_button.getScene().getWindow();
        stage.setTitle("InnoLibrary");
        stage.setScene(scene);
        stage.show();
    }
}
