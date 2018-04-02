package sample;

public class Queue_Storer implements Comparable<Queue_Storer>{
    public String cardNumber;
    public String itemID;
    public int turn;
    public int code;

    public Queue_Storer(String cardNumber, String itemID, int turn, int code) {
        this.cardNumber = cardNumber;
        this.itemID = itemID;
        this.turn = turn;
        this.code = code;
    }

    public int compareTo(Queue_Storer o){
        return turn > o.turn ? 1 : turn == o.turn ? 0 : -1;
    }
}
