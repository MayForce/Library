package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    public TableColumn <Item_Storer, String>action;
    @FXML
    public TableColumn <Item_Storer, String> author;

    @FXML
    void initialize(){
        showTable();
    }

    @FXML
    void showTable(){
        ItemID.setCellValueFactory(new PropertyValueFactory("itemID"));
        title.setCellValueFactory(new PropertyValueFactory("title"));
        author.setCellValueFactory(new PropertyValueFactory("author"));
        ArrayList<Item_Storer> list = new ArrayList<>();
        list.add(new Item_Storer("12","12","12","12",12,12,true,12,12,12, "book", 1,12 ,12,12,12,12,12,12,12,12,12));
        list.add(new Item_Storer("12","12","12","12",12,12,true,12,12,12, "book", 1,12 ,12,12,12,12,12,12,12,12,12));
        System.out.println(list.get(0).itemID);
        table.getItems().setAll(list);
    }
}
