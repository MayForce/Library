package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.util.ArrayList;

public class MyItemsController {
    static Connection c;

    @FXML
    public TableView <Item_Storer> table;
    @FXML
    public TableColumn <Item_Storer, String> ItemID;
    @FXML
    public TableColumn <Item_Storer, String> title;
    @FXML
    public TableColumn <Item_Storer, String> author;

    @FXML
    void initialize(){
        showTable();
    }

    @FXML
    void showTable(){
        Controller.c = c;
        ItemID.setCellValueFactory(new PropertyValueFactory("itemID"));
        title.setCellValueFactory(new PropertyValueFactory("title"));
        author.setCellValueFactory(new PropertyValueFactory("author"));
        System.out.println(Controller.getCheckoutList(Main.current.cardNumber_));
        ArrayList<String> listOfID = Controller.getCheckoutList(Main.current.cardNumber_);
        ArrayList<Item_Storer> list = new ArrayList<>();
        for (int i = 0; i < listOfID.size(); i++) {
            list.add(Controller.searchItem(listOfID.get(i)));
        }
        table.getItems().setAll(list);
    }

    @FXML
    void renew(ActionEvent actionEvent) {
        Controller.c = c;
        Item_Storer copy = table.getSelectionModel().getSelectedItem();
        Controller.renew(Main.current.cardNumber_, copy.itemID);
    }

    public void return_(ActionEvent actionEvent) {
        Controller.c = c;
        Item_Storer copy = table.getSelectionModel().getSelectedItem();
        Controller.returnItem(Main.current.cardNumber_, copy.itemID);
    }
}