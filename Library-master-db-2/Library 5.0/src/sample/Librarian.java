package sample;
public class Librarian extends User{

    public Librarian(String name, String address, String phoneNumber, String cardNumber, String password) {
        super(name, address, phoneNumber, cardNumber, password);
    }

    private void addPatron(PatronDB db, String name, String address, String phoneNumber, String cardNumber, String password, boolean isFaculty){
        db.patrons.put(cardNumber,new Patron(name, address, phoneNumber, cardNumber, password, isFaculty));
    }

    private User searchUser(PatronDB db, String toSearch, int key){
        switch (key){
            case 1: return searchUserByCard(db, toSearch);
//            case 2: return searchUserByName(db, toSearch);
            default: return null;
        }
    }

    private User searchUserByCard(PatronDB db, String card){
        return db.patrons.get(card); //Method search - to be added to database
    }

//    private User searchUserByName(PatronDB db, String name){
//        return db.search(name); //Method search - to be added to database
//    }

    private boolean deleteUser(PatronDB db, User user){
        db.patrons.remove(user.getCardNumber());    //Method remove - to be added to database
        return !db.patrons.contains(user);  //Method contains - to be added to database
    }

    //----------------------------------------------------------------------------------

    private void addBook(Shelf shelf, String author, String title, String keywords, double price, String publisher, int edition, int year, boolean isBestSeller){
        shelf.items.add(new Book(author, title, keywords, price, publisher, edition, year, isBestSeller));     //Method add - to be added to shelf database
    }

    private void addAudiovisual(Shelf shelf, String author, String title, String keywords, double price){
        shelf.items.add(new Audiovisual(author, title, keywords, price));  //Method add - to be added to shelf database
    }

    private void addJournal(Shelf shelf, String author, String title, String keywords, String publisher){
        shelf.items.add(new Journal(author, title, keywords, publisher));  //Method add - to be added to shelf database
    }

//    private Item searchItem(Shelf shelf, String keyword){
//        return shelf.search(keyword);
//    }

    private void deleteDocument(Document doc){
        doc.item.copies.remove(doc.item.copies.indexOf(doc));
    }

    private boolean deleteItem(Shelf shelf, Item item){
        shelf.items.remove(shelf.items.indexOf(item));    //Method remove - to be added to shelf database
        return !shelf.items.contains(item);  //Method contains - to be added to shelf database
    }

}
