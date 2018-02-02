public class Librarian extends User{

    public Librarian(String name, String address, String phoneNumber, long cardNumber, int password) {
        super(name, address, phoneNumber, cardNumber, password);
    }

    private void addPatron(Database db, String name, String address, String phoneNumber, long cardNumber, int password, boolean isFaculty){
        db.add(new Patron(name, address, phoneNumber, cardNumber, password, isFaculty)); //Method add - to be added to database
    }

    private void addLibrarian(Database db, String name, String address, String phoneNumber, long cardNumber, int password){
        db.add(new Librarian(name, address, phoneNumber, cardNumber, password)); //Method add - to be added to database
    }

    private User searchUser(Database db, String toSearch, int key){
        switch (key){
            case 1: return searchUserByCard(db, toSearch);
            case 2: return searchUserByName(db, toSearch);
            default: return null;
        }
    }

    private User searchUserByCard(Database db, String card){
        return db.search(card); //Method search - to be added to database
    }

    private User searchUserByName(Database db, String name){
        return db.search(name); //Method search - to be added to database
    }

    private boolean deleteUser(Database db, User user){
        db.remove(user);    //Method remove - to be added to database
        return !db.contains(user);  //Method contains - to be added to database
    }

    //----------------------------------------------------------------------------------

    private void addBook(Shelf shelf, String author, String title, String keywords, double price, String publisher, int edition, int year, boolean isBestSeller){
        shelf.add(new Book(author, title, keywords, price, publisher, edition, year, isBestSeller));     //Method add - to be added to shelf database
    }

    private void addAudiovisual(Shelf shelf, String author, String title, String keywords, double price){
        shelf.add(new Audiovisual(author, title, keywords, price));  //Method add - to be added to shelf database
    }

    private void addJournal(Shelf shelf, String author, String title, String keywords, String publisher){
        shelf.add(new Journal(author, title, keywords, publisher));  //Method add - to be added to shelf database
    }

    private Item searchItem(Shelf shelf, String keyword){
        return shelf.search(keyword); //Method search - to be added to shelf database
    }

    private void deleteDocument(Document doc){
        doc.item.copies.remove(doc.item.copies.indexOf(doc));
    }

    private boolean deleteItem(Shelf shelf, Item item){
        shelf.remove(item);    //Method remove - to be added to shelf database
        return !shelf.contains(item);  //Method contains - to be added to shelf database
    }

}
