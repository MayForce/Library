package sample;

import com.sun.org.apache.xpath.internal.SourceTree;
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

    @Override
    public void start(Stage primaryStage) throws Exception{
       Scene sceneLogin = new Scene(FXMLLoader.load(getClass().getResource("sample.fxml")), 1000, 700);
       primaryStage.setTitle("InnoLibrary");
       primaryStage.setScene(sceneLogin);
       primaryStage.show();
    }

    //if button login was pressed then we have login in (login.getText()) and password in (password.getText())
    //The same with login method
}
