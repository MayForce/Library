package sample;
import java.util.ArrayList;
import java.util.Arrays;

public class Issue extends Item {

    public Journal journal;
    private String editor;
    private int date;
    private ArrayList<Article> articles;

    public Issue(Journal journal, double price, String editor, int date) {
        super("", "", "", price);
        this.journal = journal;
        this.editor = editor;
        this.date = date;
        articles = new ArrayList<>();
    }

    public void newArticle(String author, String title){
        articles.add(new Article(this, author, title));
        keywords.addAll(new ArrayList<>(Arrays.asList((author + " " + title).split("\\s+"))));
    }
}
