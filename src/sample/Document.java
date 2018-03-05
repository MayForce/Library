package sample;
import java.time.LocalDate;
import java.time.Period;

public class Document{

    public Item item;
    private String title;

    public Document(Item item, boolean isReference) {
        this.item = item;
        this.title = item.getTitle() + " c." + item.numberOfCopies();
        this.isReference = isReference;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAvailable;
    public boolean isReference;

    private User keeper;
    private LocalDate checkedOut;
    private LocalDate deadline;

    public double currentFine(){
        double fine;
        if (!isAvailable) {
            Period period = deadline.until(LocalDate.now());
            fine = period.getDays() * 100;
            if (fine < item.getPrice()) return fine;
            return item.getPrice();
        }
        return 0;
    }

    public User getKeeper() {
        return keeper;
    }

    public void setKeeper(User keeper) {
        this.keeper = keeper;
    }

    public boolean checkOut(Patron user){
        if (isReference || !isAvailable) return false;
        isAvailable = false;
        checkedOut = LocalDate.now();
        if (user.isFaculty){
            deadline = checkedOut.plusWeeks(4);
        } else if (item instanceof Book){
            Book temp = (Book) item;
            if (!temp.isBestSeller)
            deadline = checkedOut.plusWeeks(3);
            else deadline = checkedOut.plusWeeks(2);
        } else deadline = checkedOut.plusWeeks(2);
        keeper = user;
        return true;
    }

    public boolean checkIn(User user){
        if (user == keeper){
            isAvailable = true;
            keeper = null;
            return true;
        }
        return false;
    }
}
