package sample;

import java.util.Hashtable;

public class PatronDB {

    public Hashtable<String, Patron> patrons;

    public PatronDB(){
        patrons = new Hashtable<>();
    }
}