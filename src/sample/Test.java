package sample;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Test {

    public static void reset(){
        String[] cards = {"1010", "1011", "1100", "1101", "1110"};
        String[] IDs = {"d1", "d2", "d3"};
        ArrayList<String> list;
        for (int j = 0; j < cards.length; j++) {
            String card  = cards[j];
            list = Controller.getCheckoutList(card);
            for (int i = 0; i < list.size(); i++) {
                DatabaseFunctions.deleteCheckout(Controller.c, card, list.get(i));
            }
        }
        PriorityQueue<Queue_Storer> queue;
        for (int j = 0; j < IDs.length; j++) {
            String ID  = IDs[j];
            queue = Controller.getPriorityQueue(ID);
            for (int i = 0; !queue.isEmpty(); i++) {
                DatabaseFunctions.deleteQueue(Controller.c, queue.remove().cardNumber, ID);
            }
        }
        for (int i = 0; i < cards.length; i++){
            Controller.deleteUser(cards[i]);
        }
        for (int i = 0; i < IDs.length; i++){
            Controller.deleteItem(IDs[i]);
        }

        Controller.addUser("p1", "Sergey Afonso", "Via Margutta, 3", "30001", "1010", "p1", "FACULTY");
        Controller.addUser("p2", "Nadia Teixeira", "Via Sacra, 13", "30002", "1011", "p2", "FACULTY");
        Controller.addUser("p3", "Elvira Espindola", "Via del Corso, 22", "30003", "1100", "p3", "FACULTY");
        Controller.addUser("s", "Andrey Velo", "Avenida Mazatlan 250", "30004", "1101", "s", "STUDENT");
        Controller.addUser("v", "Veronika Rama", "Stret Atocha, 27", "30005", "1110", "v", "VISITING");
        Controller.addItem("d1", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest and Clifford Stein", "Introduction to Algorithms", "MIT Press", 3, 2009, false, 5000, 3, "BOOK", 0);
        Controller.addItem("d2", " Erich Gamma,Ralph Johnson,John Vlissides,Richard Helm", " Design Patterns: Elements of Reusable Object-Oriented Software", "Addison-Wesley Professional", 1, 2003, true, 1700, 3, "BOOK", 0);
        Controller.addItem("d3", "Tony Hoare", "Null References: The Billion Dollar Mistake", "unknown", 0, 0, false, 700, 2, "BOOK", 0);

    }

    public static void T1(){
        reset();

        Controller.checkOut("1010", "d1", 0, LocalDate.parse("2018-03-05"));
        Controller.checkOut("1010", "d2", 0, LocalDate.parse("2018-03-05"));

        Controller.returnItem("1010", "d2");

        ArrayList<String> ch = Controller.getCheckoutList("1010");
        System.out.print("Overdue: ");
        for (int i = 0; i<ch.size(); i++){
            System.out.print("[(" + ch.get(i) + "," + DatabaseFunctions.getCheckout(Controller.c, "1010", ch.get(i)).overdue + ")]");
        }
        System.out.println("\nFine: ");
        for (int i = 0; i<ch.size(); i++){
            System.out.print("[(" + ch.get(i) + "," + Controller.individualFine("1010", ch.get(i)) + ")]");
        }
    }

    public static void T2(){
        reset();

        Controller.checkOut("1010", "d1", 0, LocalDate.parse("2018-03-05"));
        Controller.checkOut("1010", "d2", 0, LocalDate.parse("2018-03-05"));
        Controller.checkOut("1101", "d1", 0, LocalDate.parse("2018-03-05"));
        Controller.checkOut("1101", "d2", 0, LocalDate.parse("2018-03-05"));
        Controller.checkOut("1110", "d1", 0, LocalDate.parse("2018-03-05"));
        Controller.checkOut("1110", "d2", 0, LocalDate.parse("2018-03-05"));

        System.out.println("p1");
        ArrayList<String> ch = Controller.getCheckoutList("1010");
        System.out.print("Overdue: ");
        for (int i = 0; i<ch.size(); i++){
            System.out.print("[(" + ch.get(i) + "," + DatabaseFunctions.getCheckout(Controller.c, "1010", ch.get(i)).overdue + ")] ");
        }
        System.out.print("\nFine: ");
        for (int i = 0; i<ch.size(); i++){
            System.out.print("[(" + ch.get(i) + "," + Controller.individualFine("1010", ch.get(i)) + ")] ");
        }

        System.out.println("\ns");
        ch = Controller.getCheckoutList("1101");
        System.out.print("Overdue: ");
        for (int i = 0; i<ch.size(); i++){
            System.out.print("[(" + ch.get(i) + "," + DatabaseFunctions.getCheckout(Controller.c, "1101", ch.get(i)).overdue + ")] ");
        }
        System.out.print("\nFine: ");
        for (int i = 0; i<ch.size(); i++){
            System.out.print("[(" + ch.get(i) + "," + Controller.individualFine("1101", ch.get(i)) + ")] ");
        }

        System.out.println("\nv");
        ch = Controller.getCheckoutList("1110");
        System.out.print("Overdue: ");
        for (int i = 0; i<ch.size(); i++){
            System.out.print("[(" + ch.get(i) + "," + DatabaseFunctions.getCheckout(Controller.c, "1110", ch.get(i)).overdue + ")] ");
        }
        System.out.print("\nFine: ");
        for (int i = 0; i<ch.size(); i++){
            System.out.print("[(" + ch.get(i) + "," + Controller.individualFine("1110", ch.get(i)) + ")] ");
        }
    }

    public static void T3(){
        reset();

        Controller.checkOut("1010", "d1", 0, LocalDate.parse("2018-03-29"));
        Controller.checkOut("1101", "d2", 0, LocalDate.parse("2018-03-29"));
        Controller.checkOut("1110", "d2", 0, LocalDate.parse("2018-03-29"));
        Controller.renew("1010", "d1", LocalDate.parse("2018-04-02"));
        Controller.renew("1101", "d2", LocalDate.parse("2018-04-02"));
        Controller.renew("1110", "d2", LocalDate.parse("2018-04-02"));

        System.out.println("p1");
        ArrayList<String> ch = Controller.getCheckoutList("1010");
        for (int i = 0; i<ch.size(); i++){
            System.out.print("[(" + ch.get(i) + "," + DatabaseFunctions.getCheckout(Controller.c, "1010", ch.get(i)).deadline + ")] ");
        }

        System.out.println("\ns");
        ch = Controller.getCheckoutList("1101");
        for (int i = 0; i<ch.size(); i++){
            System.out.print("[(" + ch.get(i) + "," + DatabaseFunctions.getCheckout(Controller.c, "1101", ch.get(i)).deadline + ")] ");
        }

        System.out.println("\nv");
        ch = Controller.getCheckoutList("1110");
        for (int i = 0; i<ch.size(); i++){
            System.out.print("[(" + ch.get(i) + "," + DatabaseFunctions.getCheckout(Controller.c, "1110", ch.get(i)).deadline + ")] ");
        }
    }

    public static void T4(){
        reset();

        Controller.checkOut("1010", "d1", 0, LocalDate.parse("2018-03-29"));
        Controller.checkOut("1101", "d2", 0, LocalDate.parse("2018-03-29"));
        Controller.checkOut("1110", "d2", 0, LocalDate.parse("2018-03-29"));

        ArrayList<String> chi = Controller.getCheckoutsForItem("d2");
        for (int i = 0; i<chi.size(); i++){
            Controller.singleOutstandingRequest(chi.get(i), "d2");
        }

        Controller.renew("1010", "d1", LocalDate.parse("2018-04-02"));
        Controller.renew("1101", "d2", LocalDate.parse("2018-04-02"));
        Controller.renew("1110", "d2", LocalDate.parse("2018-04-02"));

        System.out.println("p1");
        ArrayList<String> ch = Controller.getCheckoutList("1010");
        for (int i = 0; i<ch.size(); i++){
            System.out.print("[(" + ch.get(i) + "," + DatabaseFunctions.getCheckout(Controller.c, "1010", ch.get(i)).deadline + ")] ");
        }

        System.out.println("\ns");
        ch = Controller.getCheckoutList("1101");
        for (int i = 0; i<ch.size(); i++){
            System.out.print("[(" + ch.get(i) + "," + DatabaseFunctions.getCheckout(Controller.c, "1101", ch.get(i)).deadline + ")] ");
        }

        System.out.println("\nv");
        ch = Controller.getCheckoutList("1110");
        for (int i = 0; i<ch.size(); i++){
            System.out.print("[(" + ch.get(i) + "," + DatabaseFunctions.getCheckout(Controller.c, "1110", ch.get(i)).deadline + ")] ");
        }
    }

    public static void T5(){
        reset();

        Controller.checkOut("1010", "d3", 0);
        Controller.checkOut("1101", "d3", 0);
        Controller.checkOut("1110", "d3", 4);

        PriorityQueue<Queue_Storer> queue = Controller.getPriorityQueue("d3");
        for (int i = 0; !queue.isEmpty(); i++){
            System.out.print("[" + Controller.searchUser(queue.remove().cardNumber).username_ + "] ");
        }
    }

    public static void T6(){
        reset();

        Controller.checkOut("1010", "d3", 0);
        Controller.checkOut("1011", "d3", 0);
        Controller.checkOut("1101", "d3", 1);
        Controller.checkOut("1110", "d3", 4);
        Controller.checkOut("1100", "d3", 5);

        PriorityQueue<Queue_Storer> queue = Controller.getPriorityQueue("d3");
        for (int i = 0; !queue.isEmpty(); i++){
            System.out.print("[" + Controller.searchUser(queue.remove().cardNumber).username_ + "] ");
        }
    }

    public static void T7(){
        T6();

        Controller.outstandingRequest("d3");

        PriorityQueue<Queue_Storer> queue = Controller.getPriorityQueue("d3");
        if (queue.isEmpty()) System.out.println("Queue is empty.");
    }

    public static void T8(){
        T6();

        Controller.returnItem("1011", "d3");

        System.out.println("\np2");
        ArrayList<String> ch = Controller.getCheckoutList("1011");
        if (ch.isEmpty()) System.out.println("P2 has no checked out items.");

        PriorityQueue<Queue_Storer> queue = Controller.getPriorityQueue("d3");
        for (int i = 0; !queue.isEmpty(); i++){
            System.out.print("[" + Controller.searchUser(queue.remove().cardNumber).username_ + "] ");
        }
    }

    public static void T9(){
        T6();

        Controller.renew("1010", "d3", LocalDate.parse("2018-04-02"));

        System.out.println("p1");
        ArrayList<String> ch = Controller.getCheckoutList("1010");
        for (int i = 0; i<ch.size(); i++){
            System.out.print("[(" + ch.get(i) + "," + DatabaseFunctions.getCheckout(Controller.c, "1010", ch.get(i)).deadline + ")] ");
        }

        PriorityQueue<Queue_Storer> queue = Controller.getPriorityQueue("d3");
        for (int i = 0; !queue.isEmpty(); i++){
            System.out.print("[" + Controller.searchUser(queue.remove().cardNumber).username_ + "] ");
        }
    }

    public static void T10(){
        reset();

        Controller.checkOut("1010", "d1", 0, LocalDate.parse("2018-03-26"));
        Controller.renew("1010", "d1", LocalDate.parse("2018-03-29"));
        Controller.checkOut("1110", "d1", 0, LocalDate.parse("2018-03-26"));
        Controller.renew("1110", "d1", LocalDate.parse("2018-03-29"));

        Controller.renew("1010", "d1", LocalDate.parse("2018-04-02"));
        Controller.renew("1110", "d1", LocalDate.parse("2018-04-02"));

        System.out.println("p1");
        ArrayList<String> ch = Controller.getCheckoutList("1010");
        for (int i = 0; i<ch.size(); i++){
            System.out.print("[(" + ch.get(i) + "," + DatabaseFunctions.getCheckout(Controller.c, "1010", ch.get(i)).deadline + ")] ");
        }

        System.out.println("\nv");
        ch = Controller.getCheckoutList("1110");
        for (int i = 0; i<ch.size(); i++){
            System.out.print("[(" + ch.get(i) + "," + DatabaseFunctions.getCheckout(Controller.c, "1110", ch.get(i)).deadline + ")] ");
        }
    }

}
