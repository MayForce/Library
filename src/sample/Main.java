package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class Main extends Application {
    public static Stage currentStage;
    public static User current;
    public static Stage adding = new Stage();


    @Override
    public void start(Stage primaryStage) throws Exception {
        Connection c = Javaconnect.Connector();
        currentStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("firstPage.fxml"));
        Scene sceneLogin = new Scene(fxmlLoader.load(), 1000, 700);
        primaryStage.setTitle("InnoLibrary");
        primaryStage.setScene(sceneLogin);
        primaryStage.show();

        Controller.c = c;
        FirstPageController.c = c;
    }
}
