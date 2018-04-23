package sample;

import javafx.event.ActionEvent;
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

    public void renew(ActionEvent actionEvent) {

    }

    public void return_(ActionEvent actionEvent) {
    }
}
/*
try {
            ArrayList<String> listOfID = Controller.getCheckoutList(Main.current.cardNumber_);
            ArrayList<Item_Storer> list = new ArrayList<>();
            for (int i = 0; i < listOfID.size(); i++) {
                list.add(Controller.searchItem(listOfID.get(i)));
            }
            //list.add(new Item_Storer("12","12","12","12",12,12,true,12,12,12, "book", 1,"",12 ,12,12,12,12,12,12,12,12,12));
            //list.add(new Item_Storer("12","12","12","12",12,12,true,12,12,12, "book", 1, "",12 ,12,12,12,12,12,12,12,12,12));
            System.out.println(list.get(0).itemID);
            table.getItems().setAll(list);
        }
        catch (NullPointerException e){
            System.out.println("sorry");

        }
 */