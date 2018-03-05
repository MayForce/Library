package sample;

public class Article {

    public Issue issue;
    private String author;
    private String title;

    public Article(Issue issue, String author, String title) {
        this.issue = issue;
        this.author = author;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }
}
