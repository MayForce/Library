package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;


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
    private TextField login;
    @FXML
    private PasswordField password;

    Connection c;

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
            DatabaseFunctions.register(c, reg_login.getText(), reg_name.getText(), reg_address.getText(), reg_number.getText(), reg_password.getText(),(isLibrarian.isSelected()) ? 1 : 0);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
            /*Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
            Stage stage;
            stage = (Stage) sign_up.getScene().getWindow();
            stage.setTitle("InnoLibrary");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            ((Controller) fxmlLoader.getController()).c = c;*/
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
}
