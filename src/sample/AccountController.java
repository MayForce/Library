package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class AccountController {
    static Connection c;


    public void addUser(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sign_up.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1000,700);
        Stage stage = Main.adding;
        stage.setTitle("Sign Up");
        stage.setScene(scene);
        stage.show();

        SignUpController.c = c;
    }

    public void addDocument(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addDocument.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        Stage stage = Main.adding;
        stage.setTitle("Add Book");
        stage.setScene(scene);
        stage.show();

        ((AddDocumentController) fxmlLoader.getController()).c = c;
    }

    public void back_to_home(ActionEvent actionEvent) throws IOException {
        FirstPageController.c = c;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FirstPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        Stage stage = Main.currentStage;
        stage.setTitle("InnoLibrary");
        stage.setScene(scene);
        stage.show();


    }

    public void myItems(ActionEvent actionEvent) throws IOException {
        MyItemsController.c = c;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myItems.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1000,700);
        Stage stage = Main.adding;
        stage.setTitle("My Items");
        stage.setScene(scene);
        stage.show();


    }
}
