package sample.images;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Checkout {
    public String userID;
    public String itemID;
    public String releaseDate;
    public String deadline;
    public int overdue;

    public Checkout(String userID, String itemID, String releaseDate, String deadline) {
        this.userID = userID;
        this.itemID = itemID;
        this.releaseDate = releaseDate;
        this.deadline = deadline;

        LocalDate dl = LocalDate.parse(deadline);
        Period period = dl.until(LocalDate.now());
        overdue = period.getDays();

    }
}
