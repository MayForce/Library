package sample;

public class Item_Storer {

    public String itemID;
    public String author;
    public String title;
    public String publisher;
    public int edition;
    public int year;
    public boolean isBestSeller;
    public double price;
    public int numberOfCopies;
    public int copiesAvailable;
    public String type;

    public Item_Storer(String itemID, String author, String title, String publisher, int edition, int year, boolean isBestSeller, double price, int numberOfCopies, int copiesAvailable, String type) {
        this.itemID = itemID;
        this.author = author;
        this.title = title;
        this.publisher = publisher;
        this.edition = edition;
        this.year = year;
        this.isBestSeller = isBestSeller;
        this.price = price;
        this.numberOfCopies = numberOfCopies;
        this.copiesAvailable = copiesAvailable;
        this.type = type;
    }
}
