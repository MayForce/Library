package sample;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Item {

    private final StringProperty titleFX;
    private final StringProperty authorFX;
    //private final IntegerProperty copiesFX;

    protected String author;
    protected String title;
    public ArrayList<String> keywords;
    private double price;
    protected ArrayList<Document> copies;

    public Item(String author, String title, String keywords, double price){
        this.author = author;
        this.authorFX = new SimpleStringProperty(author);
        this.title = title;
        this.titleFX = new SimpleStringProperty(title);
        this.price = price;
        this.keywords = new ArrayList<>(Arrays.asList((keywords + " " + author + " " + title).split("\\s+")));
        copies = new ArrayList<>();
        this.addCopy(true);
        //copiesFX = new SimpleIntegerProperty(copies.size());
    }

    public void addCopy(boolean isReference){
        copies.add(new Document(this, isReference));
        //copiesFX.setValue(copies.size());

    }

    public int numberOfCopies(){
        return copies.size();
    }

    public boolean anyAvailable(){
        for (int i = 0; i < copies.size(); i++){
            if (copies.get(i).isAvailable) return true;
        }
        return false;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public StringProperty authorProperty() {
        return authorFX;
    }

    public String getTitle() {
        return title;
    }

    public StringProperty titleProperty() {
        return titleFX;
    }

    public void setTitle(String title) {
        this.title = title;
        for (int i = 0; i < copies.size(); i++){
            copies.get(i).setTitle(title + " c." + numberOfCopies());
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
