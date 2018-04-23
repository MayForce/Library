package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class FirstPageController {
    static Connection c;
    public Text incorrect;
    public TextField login;
    public PasswordField password;


    public void login(ActionEvent actionEvent) throws IOException {
        if (!(login.getText().isEmpty() || password.getText().isEmpty())) {
            incorrect.setVisible(false);

            try {
                if (DatabaseFunctions.login(c, login.getText(), password.getText())) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
                    Stage stage = (Stage) Main.currentStage.getScene().getWindow();
                    stage.setTitle("Account");
                    stage.setScene(scene);
                    stage.show();
                    Main.current = Controller.searchUserByUsername(login.getText());

                    Controller.c = c;

                    AccountController.c = c;
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
}
