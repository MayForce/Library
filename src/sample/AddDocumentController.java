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

    public void addBookToBase(ActionEvent actionEvent) {
        DatabaseFunctions.addItem(c, ID.getText(), author.getText() ,title.getText() ,publisher.getText() ,Integer.parseInt(edition.getText()) , Integer.parseInt(year.getText()), bestseller.isSelected(), Integer.parseInt(price.getText()), Integer.parseInt(numberOfCopies.getText()),type.getText(), 1);
        System.out.println(type.getText() + " " + Controller.searchItem(ID.getText()).title + " added to system.");
    }
}
