package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.sql.Connection;

public class AddDocumentController {
    static Connection c;

    @FXML
    public TextField titleAV;
    @FXML
    public TextField authorAV;
    @FXML
    public TextField keywordsAV;
    @FXML
    public TextField priceAV;
    @FXML
    public TextField numberOfCopiesAV;
    @FXML
    public TextField referenceNumberAV;
    @FXML
    public TextField titleA;
    @FXML
    public TextField authorsA;
    @FXML
    public TextField journalA;
    @FXML
    public TextField publicationDateA;
    @FXML
    public TextField keywordsA;
    @FXML
    public TextField priceA;
    @FXML
    public TextField numberOfCopiesA;
    @FXML
    public TextField referenceNumberA;
    @FXML
    public TextField IDAV;
    @FXML
    public TextField IDA;
    @FXML
    public TextField priceB;
    @FXML
    public TextField keywordsB;
    @FXML
    public TextField yearB;
    @FXML
    public TextField editionB;
    @FXML
    public TextField publisherB;
    @FXML
    public TextField authorB;
    @FXML
    public TextField titleB;
    @FXML
    public CheckBox bestsellerB;
    @FXML
    public TextField numberOfCopiesB;
    @FXML
    public TextField referenceNumberB;
    @FXML
    public TextField IDB;


    public void addAV(ActionEvent actionEvent) {
        Controller.c = c;
        Controller.addItem(IDAV.getText(), authorAV.getText(), titleAV.getText(), null, 0, 0, false, Double.parseDouble(priceAV.getText()), Integer.parseInt(numberOfCopiesAV.getText()), "AV", Integer.parseInt(referenceNumberAV.getText()), keywordsAV.getText());
        Main.adding.close();
    }

    public void addA(ActionEvent actionEvent) {
        Controller.c = c;
        Controller.addItem(IDA.getText(), authorsA.getText(), titleA.getText(), journalA.getText(), 0, Integer.parseInt(publicationDateA.getText()), false, Double.parseDouble(priceA.getText()), Integer.parseInt(numberOfCopiesA.getText()), "ARTICLE", Integer.parseInt(referenceNumberA.getText()), keywordsA.getText());
        Main.adding.close();
    }

    public void addB(ActionEvent actionEvent) {
        Controller.c = c;
        Controller.addItem(IDB.getText(), authorB.getText(), titleB.getText(), publisherB.getText(), Integer.parseInt(editionB.getText()), Integer.parseInt(yearB.getText()), bestsellerB.isSelected(), Double.parseDouble(priceB.getText()), Integer.parseInt(numberOfCopiesB.getText()), "BOOK", Integer.parseInt(referenceNumberB.getText()), keywordsB.getText());
        Main.adding.close();
    }
}
