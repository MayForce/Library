import java.util.ArrayList;

public class Journal extends Item {

    private String publisher;
    private ArrayList<Item> issues;

    public Journal(String author, String title, String keywords, String publisher) {
        super("", title, keywords, 0);
        this.keywords.add(publisher);
        this.publisher = publisher;
        issues = new ArrayList<>();
    }

    public void newIssue(double price, String editor, int date){
        issues.add(new Issue(this, price, editor, date));
    }
}
