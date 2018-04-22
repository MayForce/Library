package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.sql.Connection;

public class AddDocumentController {
    static Connection c;

    public TextField type;
    public TextField year;
    public CheckBox bestseller;
    public TextField title;
    public TextField ID;
    public TextField numberOfCopies;
    public TextField price;
    public TextField author;
    public TextField edition;
    public TextField publisher;

    public void addBookToBase (ActionEvent actionEvent) {
        String keyWords = new String();
        keyWords = title + " " + author + " " + publisher;
        DatabaseFunctions.addItem(c, ID.getText(), author.getText() ,title.getText() ,publisher.getText() ,Integer.parseInt(edition.getText()) , Integer.parseInt(year.getText()), bestseller.isSelected(), Double.parseDouble(price.getText()), Integer.parseInt(numberOfCopies.getText()),type.getText(), 1, keyWords);
        System.out.println(type.getText() + " " + Controller.searchItem(ID.getText()).title + " added to system.");
        System.out.println("Price is: " + Double.parseDouble(price.getText()));
    }

    public void chousenTypeB(ActionEvent actionEvent) {
    }

    public void chousenTypeJ(ActionEvent actionEvent) {
    }

    public void chousenTypeA(ActionEvent actionEvent) {
    }
}
