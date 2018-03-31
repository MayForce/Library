package sample;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Checkout {
    public String userID;
    public String itemID;
    public String releaseDate;
    public String deadline;
    public int renew;
    public int onRequest;

    public Checkout(String userID, String itemID, String releaseDate, String deadline, int renew, int onRequest) {
        this.userID = userID;
        this.itemID = itemID;
        this.releaseDate = releaseDate;
        this.deadline = deadline;
        this.renew = renew;
        this.onRequest = onRequest;
    }
}
