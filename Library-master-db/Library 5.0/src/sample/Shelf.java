package sample;

import java.util.ArrayList;
import java.util.Hashtable;

public class Shelf {
    public Shelf() {
        items = new ArrayList<>();
        keywords = new Hashtable<>();
    }

    public ArrayList<Item> items;

    public Hashtable<String, ArrayList<Item>> keywords;

    public void addItem(Item newest){
        items.add(newest);
        for (int i = 0; i < newest.keywords.size(); i++){
            if (!newest.keywords.get(i).equals("")) {
                if (!this.keywords.containsKey(newest.keywords.get(i).toLowerCase())) {
                    this.keywords.put(newest.keywords.get(i).toLowerCase(), new ArrayList<>());
                }
                if (!this.keywords.get(newest.keywords.get(i).toLowerCase()).contains(newest))
                this.keywords.get(newest.keywords.get(i).toLowerCase()).add(newest);
            }
        }
    }

    public ArrayList<Item> getItems(String keyword) {
        return this.keywords.get(keyword);
    }
}