package sample;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Item {

    protected String author;
    protected String title;
    public ArrayList<String> keywords;
    private double price;
    protected ArrayList<Document> copies;

    public Item(String author, String title, String keywords, double price){
        this.author = author;
        this.title = title;
        this.price = price;
        this.keywords = new ArrayList<>(Arrays.asList((keywords + " " + author + " " + title).split("\\s+")));
        copies = new ArrayList<>();
    }

    public void addCopy(boolean isReference){
        copies.add(new Document(this, isReference));
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

    public String getTitle() {
        return title;
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
