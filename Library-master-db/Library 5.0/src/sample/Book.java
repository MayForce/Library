package sample;

public class Book extends Item{

    public Book(String author, String title, String keywords, double price, String publisher, int edition, int year, boolean isBestSeller) {
        super(author, title, keywords, price);
        this.publisher = publisher;
        this.edition = edition;
        this.year = year;
        this.isBestSeller = isBestSeller;
    }

    private String publisher;
    private int edition;
    private int year;
    public boolean isBestSeller;

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
