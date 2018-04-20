package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;

public class Main extends Application {
    public static Stage current;
    public static User curr;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Connection c = new Javaconnect().Connector();
        current = primaryStage;
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Scene sceneLogin = new Scene(fxmlLoader.load(), 1000, 700);
        primaryStage.setTitle("InnoLibrary");
        primaryStage.setScene(sceneLogin);
        primaryStage.show();

        Controller.c = c;
        ((FirstPageController) fxmlLoader.getController()).c = c;
        /*Test.TC1();
        Test.TC2();
        Test.TC3();
        Test.TC4();
        Test.TC5();
        Test.TC6();
        Test.TC7();*/
    }
}
