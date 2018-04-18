package sample;

public class User {
    public String username_;
    public String name_;
    public String address_;
    public String phoneNumber_;
    public String cardNumber_;
    public String password_;
    public String type_;
    public int borrow_;
    public double fine_;
    public int privilege_;

    public User(String username_, String name_, String address_, String phoneNumber_, String cardNumber_, String password_, String type_, int borrow_, double fine_, int privilege_) {
        this.username_ = username_;
        this.name_ = name_;
        this.address_ = address_;
        this.phoneNumber_ = phoneNumber_;
        this.cardNumber_ = cardNumber_;
        this.password_ = password_;
        this.type_ = type_;
        this.borrow_ = borrow_;
        this.fine_ = fine_;
        this.privilege_ = privilege_;
    }
}
