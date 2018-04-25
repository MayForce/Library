package sample;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Test {

    public static void reset(String[] cards, String[] IDs) {
        Main.current = Controller.searchUser("2");

        ArrayList<String> list;
        for (int j = 0; j < cards.length; j++) {
            String card = cards[j];
            list = Controller.getCheckoutList(card);
            for (int i = 0; i < list.size(); i++) {
                DatabaseFunctions.deleteCheckout(Controller.c, card, list.get(i));
            }
        }
        try {
            PriorityQueue<Queue_Storer> queue;
            for (int j = 0; j < IDs.length; j++) {
                String ID = IDs[j];
                queue = Controller.getPriorityQueue(ID);
                for (int i = 0; !queue.isEmpty(); i++) {
                    DatabaseFunctions.deleteQueue(Controller.c, queue.remove().cardNumber, ID);
                }
            }
            for (int i = 0; i < IDs.length; i++) {
                Controller.deleteItem(IDs[i]);
            }
        } catch (Exception e) {
            System.out.println("No books in system");
        }
        for (int i = 0; i < cards.length; i++) {
            Controller.deleteUser(cards[i]);
        }

//        for (int i = 0; i < IDs.length; i++){
//            Controller.deleteItem(IDs[i]);
//        }
    }

    public static void T2(boolean delete) {

        Main.current = Controller.searchUser("2");

        Controller.addUser("l1", "Eugenia Rama", null, null, "3", "l1", "LIBRARIAN", 1);
        Controller.addUser("l2", "Luie Ramos", null, null, "4", "l2", "LIBRARIAN", 2);
        Controller.addUser("l3", "Ramon Valdez", null, null, "5", "l3", "LIBRARIAN", 3);

        String[] cards = {"3", "4", "5"};
        if (delete) {
            reset(cards, null);
        }

    }

    public static void T3(boolean delete) {
        T2(false);

        Main.current = Controller.searchUser("3");

        Controller.addItem("1", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest and Clifford Stein", "Introduction to Algorithms", "MIT Press", 3, 2009, false, 5000, 3, "BOOK", 0, "Introduction_to_Algorithms  Algorithms Data Structures Complexity Computational Theory");
        Controller.addItem("2", "Niklaus Wirth", "Algorithms + Data Structures = Programs", "Prentice Hall PTR", 1, 1978, false, 5000, 3, "BOOK", 0, "Algorithms Data Structures Search Pascal");
        Controller.addItem("3", "Donald E. Knuth", "The Art of Computer Programming", "Addison Wesley Longman Publishing Co., Inc.", 3, 1997, false, 5000, 3, "BOOK", 0, "Algorithms Combinatorial Recursion");

        String[] cards = {"3", "4", "5"};
        if (delete) {
            reset(cards, null);
        }
    }

    public static void T4(boolean delete) {
        T2(false);

        Main.current = Controller.searchUser("4");

        Controller.addItem("1", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest and Clifford Stein", "Introduction to Algorithms", "MIT Press", 3, 2009, false, 5000, 3, "BOOK", 0, "Introduction_to_Algorithms Algorithms Data Structures Complexity Computational Theory");
        Controller.addItem("2", "Niklaus Wirth", "Algorithms + Data Structures = Programs", "Prentice Hall PTR", 1, 1978, false, 5000, 3, "BOOK", 0, "Algorithms Data Structures Search Pascal");
        Controller.addItem("3", "Donald E. Knuth", "The Art of Computer Programming", "Addison Wesley Longman Publishing Co., Inc.", 3, 1997, false, 5000, 3, "BOOK", 0, "Algorithms Combinatorial Recursion");

        Controller.addUser("s", "Andrey Velo", "Avenida Mazatlan 250", "30004", "1101", "s", "STUDENT", 0);
        Controller.addUser("p1", "Sergey Afonso", "Via Margutta, 3", "30001", "1010", "p1", "PROFESSOR", 0);
        Controller.addUser("p2", "Nadia Teixeira", "Via Sacra, 13", "30002", "1011", "p2", "PROFESSOR", 0);
        Controller.addUser("p3", "Elvira Espindola", "Via del Corso, 22", "30003", "1100", "p3", "PROFESSOR", 0);
        Controller.addUser("v", "Veronika Rama", "Stret Atocha, 27", "30005", "1110", "v", "VISITING", 0);

        String[] cards = {"3", "4", "5", "1101", "1010", "1011", "1100", "1110"};
        String[] id = {"1", "2", "3"};
        if (delete) {
            reset(cards, id);
        }
    }

    public static void T5(boolean delete) {
        T4(false);

        Main.current = Controller.searchUser("5");

        Controller.modDocNumberOfCopies("1", 2);
        System.out.println(Controller.searchItem("1").numberOfCopies + " copies of d1 book.");

        String[] cards = {"3", "4", "5", "1101", "1010", "1011", "1100", "1110"};
        String[] id = {"1", "2", "3"};
        if (delete) {
            reset(cards, id);
        }
    }

    public static void T6(boolean delete) {
        T4(false);

        Main.current = Controller.searchUser("1010");
        if (!Controller.checkOut(Main.current.cardNumber_, "3", 0)) {
            Controller.error(false, "No more available copies", "User will be added to queue");
            Controller.checkOut(Main.current.cardNumber_, "3", 5);
        }

        Main.current = Controller.searchUser("1011");
        if (!Controller.checkOut(Main.current.cardNumber_, "3", 0)) {
            Controller.error(false, "No more available copies", "User will be added to queue");
            Controller.checkOut(Main.current.cardNumber_, "3", 5);
        }

        Main.current = Controller.searchUser("1101");
        if (!Controller.checkOut(Main.current.cardNumber_, "3", 0)) {
            Controller.error(false, "No more available copies", "User will be added to queue");
            Controller.checkOut(Main.current.cardNumber_, "3", 1);
        }
        Main.current = Controller.searchUser("1110");
        if (!Controller.checkOut(Main.current.cardNumber_, "3", 0)) {
            Controller.error(false, "No more available copies", "User will be added to queue");
            Controller.checkOut(Main.current.cardNumber_, "3", 4);
        }

        Main.current = Controller.searchUser("1100");
        if (!Controller.checkOut(Main.current.cardNumber_, "3", 0)) {
            Controller.error(false, "No more available copies", "User will be added to queue");
            Controller.checkOut(Main.current.cardNumber_, "3", 5);
        }

        Main.current = Controller.searchUser("3");
        Controller.outstandingRequest("3");

        String[] cards = {"3", "4", "5", "1101", "1010", "1011", "1100", "1110"};
        String[] id = {"1", "2", "3"};
        if (delete) {
            reset(cards, id);
        }
    }

    public static void T7(boolean delete) {
        T4(false);

        Main.current = Controller.searchUser("1010");
        if (!Controller.checkOut(Main.current.cardNumber_, "3", 0)) {
            Controller.error(false, "No more available copies", "User will be added to queue");
            Controller.checkOut(Main.current.cardNumber_, "3", 5);
        }

        Main.current = Controller.searchUser("1011");
        if (!Controller.checkOut(Main.current.cardNumber_, "3", 0)) {
            Controller.error(false, "No more available copies", "User will be added to queue");
            Controller.checkOut(Main.current.cardNumber_, "3", 5);
        }

        Main.current = Controller.searchUser("1101");
        if (!Controller.checkOut(Main.current.cardNumber_, "3", 0)) {
            Controller.error(false, "No more available copies", "User will be added to queue");
            Controller.checkOut(Main.current.cardNumber_, "3", 1);
        }
        Main.current = Controller.searchUser("1110");
        if (!Controller.checkOut(Main.current.cardNumber_, "3", 0)) {
            Controller.error(false, "No more available copies", "User will be added to queue");
            Controller.checkOut(Main.current.cardNumber_, "3", 4);
        }

        Main.current = Controller.searchUser("1100");
        if (!Controller.checkOut(Main.current.cardNumber_, "3", 0)) {
            Controller.error(false, "No more available copies", "User will be added to queue");
            Controller.checkOut(Main.current.cardNumber_, "3", 5);
        }

        Main.current = Controller.searchUser("5");
        Controller.outstandingRequest("3");

        String[] cards = {"3", "4", "5", "1101", "1010", "1011", "1100", "1110"};
        String[] id = {"1", "2", "3"};
        if (delete) {
            reset(cards, id);
        }
    }

    public static void T8(boolean delete) {
        T6(false);

        Main.current = Controller.searchUser("2");
        ArrayList<String> records = Controller.getRecord();
        for (String record : records) {
            System.out.println(record);
        }

        String[] cards = {"3", "4", "5", "1101", "1010", "1011", "1100", "1110"};
        String[] id = {"1", "2", "3"};
        if (delete) {
            reset(cards, id);
        }
    }

    public static void T9(boolean delete) {
        T7(false);

        Main.current = Controller.searchUser("2");
        ArrayList<String> records = Controller.getRecord();
        for (String record : records) {
            System.out.println(record);
        }

        String[] cards = {"3", "4", "5", "1101", "1010", "1011", "1100", "1110"};
        String[] id = {"1", "2", "3"};
        if (delete) {
            reset(cards, id);
        }
    }

    public static void T10(boolean delete) {
        T4(false);

        Main.current = Controller.searchUser("1110");
        //Controller.addItem("1", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest and Clifford Stein", "Introduction to Algorithms", "MIT Press", 3, 2009, false, 5000, 3, "BOOK", 0, "Introduction_to_Algorithms Algorithms Data Structures Complexity Computational Theory");

        String search = "Introduction_to_Algorithms";
        ArrayList<String> ids = Controller.getKeywordResults(search.toUpperCase());
        String foundElements = "";
        for (String id : ids) {
            foundElements += Controller.searchItem(id).title + " by " + Controller.searchItem(id).author + " was found.\n" + "\n";
        }
        Controller.error(false, "Item found", foundElements.substring(0));

        String[] cards = {"3", "4", "5", "1101", "1010", "1011", "1100", "1110"};    //foundElements.length() - 2
        String[] id = {"1", "2", "3"};
        if (delete) {
            reset(cards, id);
        }
    }

    public static void T11(boolean delete) {
        //T4(false);

        Main.current = Controller.searchUser("2");
        Controller.addItem("1", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest and Clifford Stein", "Introduction to Algorithms", "MIT Press", 3, 2009, false, 5000, 3, "BOOK", 0, "Algorithms Data Structures Complexity Computational Theory");
        Controller.addItem("2", "Niklaus Wirth", "Algorithms + Data Structures", "MIT Press", 3, 2009, false, 5000, 3, "BOOK", 0, "Algorithms Data Structures Complexity Computational Theory");

        Main.current = Controller.searchUser("1110");
        String search = "Algorithms";
        ArrayList<String> ids = Controller.getKeywordResults(search.toUpperCase());
        String foundElements = "";
        for (String id : ids) {
            foundElements += Controller.searchItem(id).title + " by " + Controller.searchItem(id).author + " was found.\n" + "\n";
        }
        Controller.error(false, "Item found", foundElements.substring(0, foundElements.length() - 2));

        //String[] cards = {"3", "4", "5", "1101", "1010", "1011", "1100", "1110"};
        String[] cards = {};
        String[] id = {"1", "2"};
        if (delete) {
            reset(cards, id);
        }
    }

    public static void T12(boolean delete) {
        //T4(false);

        Main.current = Controller.searchUser("2");
        Controller.addItem("1", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest and Clifford Stein", "Introduction to Algorithms", "MIT Press", 3, 2009, false, 5000, 3, "BOOK", 0, "Algorithms Data Structures Complexity Computational Theory");
        Controller.addItem("2", "Niklaus Wirth", "Algorithms + Data Structures", "MIT Press", 3, 2009, false, 5000, 3, "BOOK", 0, "Algorithms Data Structures Complexity Computational Theory");
        Controller.addItem("3", "Donald E. Knuth", "The Art of Computer Programming", "MIT Press", 3, 2009, false, 5000, 3, "BOOK", 0, "Algorithms Data Structures Complexity Computational Theory");

        Main.current = Controller.searchUser("1110");
        String search = "Algorithms";
        ArrayList<String> ids = Controller.getKeywordResults(search.toUpperCase());
        String foundElements = "";
        for (String id : ids) {
            foundElements += Controller.searchItem(id).title + " by " + Controller.searchItem(id).author + " was found.\n" + "\n";
        }
        Controller.error(false, "Item found", foundElements.substring(0, foundElements.length() - 2));

        //String[] cards = {"3", "4", "5", "1101", "1010", "1011", "1100", "1110"};
        String[] cards = {};
        String[] id = {"1", "2", "3"};
        if (delete) {
            reset(cards, id);
        }
    }
}
