import java.util.ArrayList;

public class Patron extends User{

    public boolean isFaculty;
    public ArrayList<Document> inPossession;

    public Patron(String name, String address, String phoneNumber, long cardNumber, int password, boolean isFaculty) {
        super(name, address, phoneNumber, cardNumber, password);
        this.isFaculty  = isFaculty;
        inPossession = new ArrayList<>();
    }

    public boolean checkOut(Document doc){
        boolean check = doc.checkOut(this);
        if (check) {
            inPossession.add(doc);
        }
        return check;
    }

    public boolean returnBook(Document doc){
        boolean check = doc.checkIn(this);
        if (check) {
            inPossession.remove(inPossession.indexOf(doc));
        }
        return check;
    }

    public int docsInPossession(){
        return inPossession.size();
    }

    public double totalFine(){
        double sum = 0;
        for (int i = 0; i < inPossession.size(); i++){
            sum += inPossession.get(i).currentFine();
        }
        return sum;
    }

}
