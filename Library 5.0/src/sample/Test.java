package sample;

import java.util.ArrayList;

public class Test {

    public static void TC1 (){
        System.out.println("\nTC1-------------------------------------------");
        Controller.addUser("lib1", "lib1", "innopolis", "123456789", "L1", "lib1", "LIBRARIAN");
        Controller.addItem("B1", "unknown", "b1", "unknown", 1, 2018, false, 1000, 3, 3, "BOOK");
        Controller.addItem("B2", "unknown", "b2", "unknown", 1, 2018, false, 1000, 2, 2, "BOOK");
        Controller.addItem("B3", "unknown", "b3", "unknown", 1, 2018, false, 1000, 1, 1, "BOOK");
        Controller.addItem("AV1", "unknown", "av1", "unknown", 1, 2018, false, 1000, 1, 1, "AUDIOVISUAL");
        Controller.addItem("AV2", "unknown", "av2", "unknown", 1, 2018, false, 1000, 1, 1, "AUDIOVISUAL");
        Controller.addUser("P1", "Sergey Afonso", "Via Margutta, 3", "30001", "1010", "p1", "FACULTY");
        Controller.addUser("P2", "Nadia Teixeira", "Via Sacra, 13", "30002", "1011", "p2", "STUDENT");
        Controller.addUser("P3", "Elvira Espindola", "Via del Corso, 22", "30003", "1100", "p3", "STUDENT");
        System.out.println("There are " + Controller.userCount + " users in the system.");
        System.out.println("There are " + Controller.itemCount + " items in the system.");
        System.out.println("-----------------------------------------------");
    }

    public static void TC2 (){
        System.out.println("\nTC2-------------------------------------------");
        Controller.removeCopy("B1", 2);
        Controller.removeCopy("B3", 1);
        Controller.deleteUser("1011");
        System.out.println("There are " + Controller.userCount + " users in the system.");
        System.out.println("There are " + Controller.itemCount + " items in the system.");
        System.out.println("-----------------------------------------------");
    }

    public static void TC3 (){
        System.out.println("\nTC3-------------------------------------------");
        User user = Controller.searchUser("1010");
        System.out.println("Username: " + user.username_);
        System.out.println("Name: " + user.name_);
        System.out.println("Address: " + user.address_);
        System.out.println("Phone Number: " + user.phoneNumber_);
        System.out.println("Lib. card ID: " + user.cardNumber_);
        System.out.println("Type: " + user.type_);
        System.out.println("Borrowed items: " + user.borrow_);

        user = Controller.searchUser("1100");
        System.out.println("Username: " + user.username_);
        System.out.println("Name: " + user.name_);
        System.out.println("Address: " + user.address_);
        System.out.println("Phone Number: " + user.phoneNumber_);
        System.out.println("Lib. card ID: " + user.cardNumber_);
        System.out.println("Type: " + user.type_);
        System.out.println("Borrowed items: " + user.borrow_);
        System.out.println("-----------------------------------------------");
    }

    public static void TC4 (){
        System.out.println("\nTC4-------------------------------------------");
        User user = Controller.searchUser("1011");
        if (user==null) System.out.println("P2: information not available. ");

        user = Controller.searchUser("1100");
        System.out.println("Username: " + user.username_);
        System.out.println("Name: " + user.name_);
        System.out.println("Address: " + user.address_);
        System.out.println("Phone Number: " + user.phoneNumber_);
        System.out.println("Lib. card ID: " + user.cardNumber_);
        System.out.println("Type: " + user.type_);
        System.out.println("Borrowed items: " + user.borrow_);
        System.out.println("-----------------------------------------------");
    }

    public static void TC5 (){
        System.out.println("\nTC5-------------------------------------------");
        Controller.checkOut("1011", "B1");
        System.out.println("-----------------------------------------------");
    }

    public static void TC6 (){

        Controller.deleteUser("L1");
        Controller.deleteUser("1010");
        Controller.deleteUser("1100");
        Controller.deleteItem("B1");
        Controller.deleteItem("B2");
        Controller.deleteItem("B3");
        Controller.deleteItem("AV1");
        Controller.deleteItem("AV2");
        ArrayList<String> deletion = DatabaseFunctions.getCheckoutList(Controller.c, "1010");
        for (int i = 0; i < deletion.size(); i++) {
            DatabaseFunctions.deleteCheckout(Controller.c,"1010",deletion.get(i));
        }
        deletion = DatabaseFunctions.getCheckoutList(Controller.c, "1100");
        for (int i = 0; i < deletion.size(); i++) {
            DatabaseFunctions.deleteCheckout(Controller.c,"1100",deletion.get(i));
        }
        deletion = DatabaseFunctions.getCheckoutList(Controller.c, "1011");
        for (int i = 0; i < deletion.size(); i++) {
            DatabaseFunctions.deleteCheckout(Controller.c,"1011",deletion.get(i));
        }

        System.out.println("\nTC6-------------------------------------------");
        TC1();
        TC2();
        Controller.checkOut("1010", "B1");
        Controller.checkOut("1100", "B1");
        Controller.checkOut("1010", "B2");

        User user = Controller.searchUser("1010");
        System.out.println("Username: " + user.username_);
        System.out.println("Name: " + user.name_);
        System.out.println("Address: " + user.address_);
        System.out.println("Phone Number: " + user.phoneNumber_);
        System.out.println("Lib. card ID: " + user.cardNumber_);
        System.out.println("Type: " + user.type_);
        System.out.println("Borrowed items: " + user.borrow_);
        ArrayList<String> borrowed = DatabaseFunctions.getCheckoutList(Controller.c, "1010");
        for (int i = 0; i < borrowed.size(); i++) {
            System.out.print(borrowed.get(i) + " , ");
            System.out.println(DatabaseFunctions.getCheckout(Controller.c,"1010", borrowed.get(i)).deadline.toUpperCase());
        }

        user = Controller.searchUser("1100");
        System.out.println("Username: " + user.username_);
        System.out.println("Name: " + user.name_);
        System.out.println("Address: " + user.address_);
        System.out.println("Phone Number: " + user.phoneNumber_);
        System.out.println("Lib. card ID: " + user.cardNumber_);
        System.out.println("Type: " + user.type_);
        System.out.println("Borrowed items: " + user.borrow_);
        borrowed = DatabaseFunctions.getCheckoutList(Controller.c, "1100");
        for (int i = 0; i < borrowed.size(); i++) {
            System.out.print(borrowed.get(i) + " , ");
            System.out.println(DatabaseFunctions.getCheckout(Controller.c,"1100", borrowed.get(i)).deadline.toUpperCase());
        }
        System.out.println("-----------------------------------------------");
    }

    public static void TC7 (){
        Controller.deleteUser("L1");
        Controller.deleteUser("1010");
        Controller.deleteUser("1011");
        Controller.deleteUser("1100");
        Controller.deleteItem("B1");
        Controller.deleteItem("B2");
        Controller.deleteItem("B3");
        Controller.deleteItem("AV1");
        Controller.deleteItem("AV2");
        System.out.println("\nTC7-------------------------------------------");
        TC1();
        Controller.checkOut("1010", "B1");
        Controller.checkOut("1010", "B2");
        Controller.checkOut("1010", "B3");
        Controller.checkOut("1010", "AV1");
        Controller.checkOut("1011", "B1");
        Controller.checkOut("1011", "B2");
        Controller.checkOut("1011", "AV2");

        User user = Controller.searchUser("1010");
        System.out.println("Username: " + user.username_);
        System.out.println("Name: " + user.name_);
        System.out.println("Address: " + user.address_);
        System.out.println("Phone Number: " + user.phoneNumber_);
        System.out.println("Lib. card ID: " + user.cardNumber_);
        System.out.println("Type: " + user.type_);
        System.out.println("Borrowed items: " + user.borrow_);
        ArrayList<String> borrowed = DatabaseFunctions.getCheckoutList(Controller.c, "1010");
        for (int i = 0; i < borrowed.size(); i++) {
            System.out.print(borrowed.get(i) + " , ");
            System.out.println(DatabaseFunctions.getCheckout(Controller.c,"1010", borrowed.get(i)).deadline);
        }

        user = Controller.searchUser("1011");
        System.out.println("Username: " + user.username_);
        System.out.println("Name: " + user.name_);
        System.out.println("Address: " + user.address_);
        System.out.println("Phone Number: " + user.phoneNumber_);
        System.out.println("Lib. card ID: " + user.cardNumber_);
        System.out.println("Type: " + user.type_);
        System.out.println("Borrowed items: " + user.borrow_);
        borrowed = DatabaseFunctions.getCheckoutList(Controller.c, "1011");
        for (int i = 0; i < borrowed.size(); i++) {
            System.out.print(borrowed.get(i) + " , ");
            System.out.println(DatabaseFunctions.getCheckout(Controller.c,"1011", borrowed.get(i)).deadline);
        }
        System.out.println("-----------------------------------------------");
    }
}
