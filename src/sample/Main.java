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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;


public class Main extends Application {

    public static Shelf shelf = new Shelf();
    public static LibrarianDB libDB = new LibrarianDB();
    public static PatronDB patDB = new PatronDB();
    public static User currentUser;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Connection c = new Javaconnect().Connector();

        primaryStage.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Scene sceneLogin = new Scene(fxmlLoader.load(), 1000, 700);
        primaryStage.setTitle("InnoLibrary");
        primaryStage.setScene(sceneLogin);
        primaryStage.show();

        ((Controller) fxmlLoader.getController()).c = c;
    }
}
