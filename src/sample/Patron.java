package sample;
import java.util.ArrayList;

public class Patron extends User{

    public boolean isFaculty;
    public ArrayList<Document> documents;

    public Patron(String name, String address, String phoneNumber, String cardNumber, String password, boolean isFaculty) {
        super(name, address, phoneNumber, cardNumber, password);
        this.isFaculty  = isFaculty;
        documents = new ArrayList<>();
    }



    public boolean checkOut(Document doc){
        boolean check = doc.checkOut(this);
        if (check) {
            documents.add(doc);
        }
        return check;
    }

    public boolean returnBook(Document doc){
        boolean check = doc.checkIn(this);
        if (check) {
            documents.remove(documents.indexOf(doc));
        }
        return check;
    }

    public int docsInPossession(){
        return documents.size();
    }

    public double totalFine(){
        double sum = 0;
        for (int i = 0; i < documents.size(); i++){
            sum += documents.get(i).currentFine();
        }
        return sum;
    }

}
